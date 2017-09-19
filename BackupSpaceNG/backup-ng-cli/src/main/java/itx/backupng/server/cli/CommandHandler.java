package itx.backupng.server.cli;

import io.grpc.stub.StreamObserver;
import itx.backupng.server.grpc.DiskInfoRequest;
import itx.backupng.server.grpc.WrapperRequest;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

public class CommandHandler {

    private StreamObserver streamObserver;
    private AtomicLong contextId;
    private Map<Long, CompletableFuture<Boolean>> commands;

    public CommandHandler(StreamObserver streamObserver, Map<Long, CompletableFuture<Boolean>> commands) {
        this.streamObserver = streamObserver;
        this.commands = commands;
        this.contextId = new AtomicLong(0);
    }

    public Future<Boolean> execCommand(String command) {
        Long ctxId = contextId.getAndIncrement();
        CompletableFuture<Boolean> taskStatus = new CompletableFuture();
        if (Commands.DISK_INFO_CMD.equals(command.toLowerCase())) {
            WrapperRequest wrapperMessage = WrapperRequest.newBuilder()
                    .setContextId(ctxId)
                    .setDiskInfoRequest(DiskInfoRequest.newBuilder().build())
                    .build();
            commands.put(ctxId, taskStatus);
            streamObserver.onNext(wrapperMessage);
            return taskStatus;
        } else {
            MessageUtils.printlnOnStdErr("ERROR: unsupported command '" + command + "'");
            taskStatus.complete(false);
            return taskStatus;
        }
    }

}
