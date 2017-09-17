package itx.backupng.server.cmd.tasks;

import itx.backupng.server.cmd.CmdExecResult;
import itx.backupng.server.controller.services.CmdExecutor;
import itx.backupng.server.model.disks.DiskInfo;
import itx.backupng.server.model.disks.PartitionInfo;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class GetDiskInfoTask implements CmdTask<List<DiskInfo>> {

    private static final String DISKS_BY_UUID = "disks-by-uuid:";
    private static final String DISKS_BY_ID   = "disks-by-id:";
    private static final String DISKS_BY_PATH = "disks-by-path:";

    private static final String COMMAND = "system-list-disks.sh";

    private CmdExecutor cmdExecutor;

    public GetDiskInfoTask(CmdExecutor cmdExecutor) {
        this.cmdExecutor = cmdExecutor;
    }

    @Override
    public List<DiskInfo> exec() throws TaskExecutionException {
        try {
            CmdExecResult execResult = cmdExecutor.exec(COMMAND);
            if (execResult.isSuccess()) {
                Collection<String> disksAndPartitions = filterDisksAndPartitions(execResult.getStdOut());
                Collection<String> disks = filterDisks(disksAndPartitions);
                return createDiskInfo(execResult, disks);
            }
            throw new TaskExecutionException("Command exec \"" + COMMAND + "\" failed with exit code: " + execResult.getExitCode());
        } catch (IOException e) {
            throw new TaskExecutionException("IOException: ", e);
        } catch (InterruptedException e) {
            throw new TaskExecutionException("InterruptedException: ", e);
        } catch (Exception e) {
            throw new TaskExecutionException("Exception: ", e);
        }
    }

    private Collection<String> filterDisksAndPartitions(Collection<String> stdOut) {
        List<String> result = new ArrayList<>();
        for (String line : stdOut) {
            String[] split = line.split(" ");
            if (split.length >= 11) {
                String primaryId = split[split.length - 1];
                String primaryPath = Paths.get("/dev/disk/by-", primaryId).normalize().toString();
                if (!result.contains(primaryPath)) {
                    result.add(primaryPath);
                }
            }
        }
        result.sort(new StringLengthComparator());
        return Collections.unmodifiableCollection(result);
    }

    private Collection<String> filterDisks(Collection<String> disksAndPartitionsList) {
        List<String> result = new ArrayList<>();
        for (String diskOrPartition : disksAndPartitionsList) {
            checkForPresence(diskOrPartition, result);
        }
        return Collections.unmodifiableCollection(result);
    }

    private void checkForPresence(String diskOrPartition, List<String> disks) {
        boolean alreadyIn = false;
        for (String disk : disks) {
            if (diskOrPartition.startsWith(disk)) {
                alreadyIn = true;
                break;
            }
        }
        if (!alreadyIn) {
            disks.add(diskOrPartition);
        }
    }

    private static class StringLengthComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            if (o1.length() > o2.length()) {
                return 1;
            }
            if (o1.length() < o2.length()) {
                return -1;
            }
            return 0;
        }
    }

    private List<DiskInfo> createDiskInfo(CmdExecResult execResult, Collection<String> disks) {
        List<DiskInfo> result = new ArrayList<>();
        for (String diskId: disks) {
            List<PartitionInfo> partitionInfos = createPartitionInfo(diskId, execResult);
            DiskInfo diskInfo = new DiskInfo(diskId, partitionInfos);
            result.add(diskInfo);
        }
        return result;
    }

    private List<PartitionInfo> createPartitionInfo(String diskId, CmdExecResult execResult) {
        List<PartitionInfo> result = new ArrayList<>();
        int status = 0;
        for (String line: execResult.getStdOut()) {
            if (DISKS_BY_UUID.equals(line)) {
                status = 1;
                continue;
            }
            if (DISKS_BY_ID.equals(line)) {
                status = 2;
                continue;
            }
            if (DISKS_BY_PATH.equals(line)) {
                status = 3;
                continue;
            }
            if (status == 1) {
                String[] split = line.split(" ");
                if (split.length >= 11) {
                    String primaryId = split[split.length - 1];
                    String uuid = split[split.length - 3];
                    String primaryPath = Paths.get("/dev/disk/by-", primaryId).normalize().toString();
                    if (primaryPath.startsWith(diskId)) {
                        PartitionInfo partitionInfo = new PartitionInfo(primaryPath, uuid);
                        result.add(partitionInfo);
                    }
                }
            }
        }
        return result;
    }

}