package itx.backupng.server.grpc;

import itx.backupng.server.model.disks.DiskInfo;
import itx.backupng.server.model.disks.PartitionInfo;

import java.util.List;

public final class MessageUtils {

    private MessageUtils() {
    }

    public static DiskInfoResponse createResponse(List<DiskInfo> diskInfos) {
        DiskInfoResponse.Builder builder = DiskInfoResponse.newBuilder();
        for (DiskInfo di: diskInfos) {
            itx.backupng.server.grpc.DiskInfo.Builder diskInfoBuilder = itx.backupng.server.grpc.DiskInfo.newBuilder()
                    .setId(di.getId());
            for (PartitionInfo pi: di.getPartitions()) {
                itx.backupng.server.grpc.PartitionInfo partitionInfo = itx.backupng.server.grpc.PartitionInfo.newBuilder()
                        .setId(pi.getId())
                        .setUuid(pi.getUuid())
                        .build();
                diskInfoBuilder.addPartitionInfo(partitionInfo);
            }
            builder.addDiskInfo(diskInfoBuilder.build());
        }
        return builder.build();
    }

    public static WrapperResponse createExceptionReply(long contextId, Exception e) {
        DataException dataException = DataException.newBuilder().setMessage(e.getMessage()).build();
        WrapperResponse response = WrapperResponse.newBuilder()
                .setContextId(contextId)
                .setDataException(dataException)
                .build();
        return response;
    }

}
