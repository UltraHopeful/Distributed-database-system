package Query;

import UserManagement.UserLogin;

import java.io.File;

public class GlobalConfig {

    private static String globalDatabase;

    private static String pathSeparator = File.separator;

    private static String delimeter = "\u2088";

    private static String rowDelimeter = "\u2087";
    private static String basePath ="system"+pathSeparator+"";

    public GlobalConfig() {
    }

    public GlobalConfig(String globalDatabase, String basePath) {
        this.globalDatabase = globalDatabase;
        this.basePath = basePath;
    }

    public static String getGlobalDatabase() {
        return globalDatabase;
    }

    public void setGlobalDatabase(String globalDatabase) {
        this.globalDatabase = globalDatabase;
    }

    public static String getBasePath() {
        return basePath;
    }

    public static String getPathSeparator() {
        return pathSeparator;
    }

    public String getDelimiter() {
        return delimeter;
    }

    public static String getRowDelimiter() {
        return rowDelimeter;
    }
}
