package itx.backupng.server.cli;

import io.grpc.stub.StreamObserver;
import itx.backupng.server.grpc.DiskInfoRequest;
import itx.backupng.server.grpc.WrapperRequest;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

public class CommandHandler {

    private StreamObserver streamObserver;
    private AtomicLong contextId;
    private Map<Long, CountDownLatch> commands;

    public CommandHandler(StreamObserver streamObserver, Map<Long, CountDownLatch> commands) {
        this.streamObserver = streamObserver;
        this.commands = commands;
        this.contextId = new AtomicLong(0);
    }

    public CountDownLatch execCommand(String command) {
        Long ctxid = contextId.getAndIncrement();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        if (Commands.DISK_INFO_CMD.equals(command.toLowerCase())) {
            WrapperRequest wrapperMessage = WrapperRequest.newBuilder()
                    .setContextId(ctxid)
                    .setDiskInfoRequest(DiskInfoRequest.newBuilder().build())
                    .build();
            commands.put(ctxid, countDownLatch);
            streamObserver.onNext(wrapperMessage);
            return countDownLatch;
        } else {
            MessageUtils.printlnOnStdErr("ERROR: unsupported command '" + command + "'");
            countDownLatch.countDown();
            return countDownLatch;
        }
    }

}
