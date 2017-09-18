package itx.backupng.server.cli;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import itx.backupng.server.grpc.BackupServiceGrpc;
import itx.backupng.server.grpc.EmptyMessage;
import itx.backupng.server.grpc.VersionReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Main {

    final private static Logger LOG = LoggerFactory.getLogger(Main.class);

    final private static String DEFAULT_TARGET = "127.0.0.1:50051";

    public static void main(String[] args) {
        LOG.info("BackupSpaceNG CLI client starting ...");
        Map<Long, CountDownLatch> commands = new ConcurrentHashMap<>();

        String target = System.getProperty("target");
        if (target == null) {
            LOG.info("To override default connection parameters, use -Dtarget=host:port JVM option.");
            target = DEFAULT_TARGET;
        }
        LOG.info("Connecting to server: {}", target);

        ManagedChannel channel = ManagedChannelBuilder.forTarget(target)
                .usePlaintext(true)
                .build();
        BackupServiceGrpc.BackupServiceBlockingStub backupServiceBlockingStub = BackupServiceGrpc.newBlockingStub(channel);
        EmptyMessage emptyMessage = EmptyMessage.newBuilder().build();
        VersionReply version = backupServiceBlockingStub.getVersion(emptyMessage);
        LOG.info("Connected: Server version = {}", version.getVersion());

        BackupServiceGrpc.BackupServiceStub backupServiceStub = BackupServiceGrpc.newStub(channel);
        ClientDataChannelStreamObserver clientDataChannelStreamObserver = new ClientDataChannelStreamObserver(commands);
        StreamObserver streamObserver = backupServiceStub.dataChannel(clientDataChannelStreamObserver);
        CommandHandler commandHandler = new CommandHandler(streamObserver, commands);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            MessageUtils.printlnOnStdOut("$: ");
            String command = scanner.nextLine();
            if (Commands.EXIT_CMD.equals(command.toLowerCase())) {
                break;
            }
            if (Commands.HELP_CMD.equals(command.toLowerCase())) {
                MessageUtils.printHelp();
                continue;
            }
            long startTime = System.nanoTime();
            CountDownLatch countDownLatch = commandHandler.execCommand(command);
            try {
                countDownLatch.await(6, TimeUnit.SECONDS);
                float duration = (System.nanoTime() - startTime)/1_000_000f;
                MessageUtils.printlnOnStdOut("exec. time: " + duration + "ms");
            } catch (InterruptedException e) {
                MessageUtils.printlnOnStdErr("ERROR: command timeout !");
            }
        }
        streamObserver.onCompleted();
        LOG.info("Client stopped.");
    }

}
