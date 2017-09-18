package itx.backupng.server.cli;

import io.grpc.stub.StreamObserver;
import itx.backupng.server.grpc.DataException;
import itx.backupng.server.grpc.WrapperResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * StreamObserver which receives data from server.
 * @param <V>
 */
public class ClientDataChannelStreamObserver<V extends WrapperResponse> implements StreamObserver<V> {

    final private static Logger LOG = LoggerFactory.getLogger(ClientDataChannelStreamObserver.class);

    private Map<Long, CountDownLatch> commands;

    public ClientDataChannelStreamObserver(Map<Long, CountDownLatch> commands) {
        this.commands = commands;
    }

    @Override
    public void onNext(V value) {
        Long contextId = value.getContextId();
        CountDownLatch countDownLatch = commands.remove(contextId);
        switch (value.getDataCase().getNumber()) {
            case WrapperResponse.DISKINFORESPONSE_FIELD_NUMBER:
                MessageUtils.printDiskInfoResponse(value.getDiskInfoResponse());
                break;
            default:
                DataException dataException = value.getDataException();
                if (dataException != null) {
                    MessageUtils.printlnOnStdErr("ERROR: " + dataException.getMessage());
                } else {
                    MessageUtils.printlnOnStdErr("ERROR: ");
                }
        }
        if (countDownLatch != null) {
            countDownLatch.countDown();
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
