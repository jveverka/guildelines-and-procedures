package itx.backupng.server.grpc;

import io.grpc.stub.StreamObserver;
import itx.backupng.server.cmd.tasks.TaskExecutionException;
import itx.backupng.server.controller.Services;
import itx.backupng.server.model.disks.DiskInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ServerDataChannelStreamObserver<V extends WrapperRequest> implements StreamObserver<V> {

    final private static Logger LOG = LoggerFactory.getLogger(ServerDataChannelStreamObserver.class);

    private StreamObserver<WrapperResponse> responseObserver;
    private Services services;

    public ServerDataChannelStreamObserver(StreamObserver<WrapperResponse> responseObserver, Services services) {
        this.responseObserver = responseObserver;
        this.services = services;
    }

    @Override
    public void onNext(V value) {
        long contextId = value.getContextId();
        switch (value.getDataCase().getNumber()) {
            case WrapperRequest.DISKINFOREQUEST_FIELD_NUMBER:
                try {
                    LOG.info("DiskInfoRequest {}", contextId );
                    List<DiskInfo> diskInfo = services.getSystem().getDiskInfo();
                    WrapperResponse response = WrapperResponse.newBuilder()
                            .setContextId(contextId)
                            .setDiskInfoResponse(MessageUtils.createResponse(diskInfo))
                            .build();
                    responseObserver.onNext(response);
                } catch (TaskExecutionException e) {
                    responseObserver.onNext(MessageUtils.createExceptionReply(contextId, e));
                }
                break;
            default:
                LOG.warn("onNext: unsupported message type.");
        }
    }

    @Override
    public void onError(Throwable t) {
        LOG.error("onError: {}", t.getMessage());
    }

    @Override
    public void onCompleted() {
        LOG.info("onCompleted: ");
    }

}
