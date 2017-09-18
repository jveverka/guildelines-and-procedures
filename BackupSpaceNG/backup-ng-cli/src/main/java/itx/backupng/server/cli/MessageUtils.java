package itx.backupng.server.cli;

import itx.backupng.server.grpc.DiskInfo;
import itx.backupng.server.grpc.DiskInfoResponse;
import itx.backupng.server.grpc.PartitionInfo;

public final class MessageUtils {

    private MessageUtils() {
    }

    public static void printDiskInfoResponse(DiskInfoResponse diskInfoResponse) {
        for (DiskInfo diskInfo: diskInfoResponse.getDiskInfoList()) {
            printlnOnStdOut("Disk: " + diskInfo.getId() + ", partitions[" + diskInfo.getPartitionInfoList().size() + "]");
            for (PartitionInfo partitionInfo: diskInfo.getPartitionInfoList()) {
                printlnOnStdOut("  Partition: " + partitionInfo.getId() + " uuid=" + partitionInfo.getUuid());
            }
        }
    }

    public static void printHelp() {
        printlnOnStdOut("available commands:");
        for (int i=0; i<Commands.COMMANDS.length; i++) {
            printlnOnStdOut(Commands.COMMANDS[i]);
            printlnOnStdOut("   " + Commands.HELPS[i]);
        }
        printlnOnStdOut();
    }

    public static void printlnOnStdErr(String message) {
        System.err.println(message);
    }

    public static void printlnOnStdOut(String message) {
        System.out.println(message);
    }

    public static void printlnOnStdOut() {
        System.out.println("");
    }

}
