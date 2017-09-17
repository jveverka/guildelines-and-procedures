package itx.backupng.server.cli;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import itx.backupng.server.grpc.BackupServiceGrpc;
import itx.backupng.server.grpc.EmptyMessage;
import itx.backupng.server.grpc.VersionReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Main {

    final private static Logger LOG = LoggerFactory.getLogger(Main.class);

    final private static String EXIT_CMD = "EXIT";
    final private static String DEFAULT_TARGET = "127.0.0.1:50051";

    public static void main(String[] args) {
        LOG.info("BackupSpaceNG CLI client starting ...");
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
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("$: ");
            String command = scanner.nextLine();
            if (EXIT_CMD.equals(command.toUpperCase())) {
                break;
            }
        }
        LOG.info("Client stopped.");
    }

}
