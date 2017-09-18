package itx.backupng.server.cli;

import java.util.HashMap;
import java.util.Map;

public class Commands {

    public static final String[] COMMANDS = new String[] {
            "help",
            "exit",
            "diskinfo"
    };

    public static final String HELP_CMD = COMMANDS[0];
    public static final String EXIT_CMD = COMMANDS[1];
    public static final String DISK_INFO_CMD = COMMANDS[2];

    public static final String[] HELPS = new String[] {
            "shows this help",
            "exit the application",
            "show available disks an partitions"
    };


}
