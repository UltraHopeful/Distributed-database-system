package Query.Process;

import Query.GlobalConfig;

import java.io.File;

public class Common {

    GlobalConfig globalConfig = new GlobalConfig();
    String basePath = globalConfig.getBasePath();
    String filePathSeparator = globalConfig.getPathSeparator();
    String delimeter = globalConfig.getDelimeter();

    public boolean tableCheck(String tableName){
        boolean isTableExists = false;

        String currentDataBase = globalConfig.getGlobalDatabase();
        File tableFile = new File(basePath + currentDataBase+filePathSeparator+tableName+".txt");
        if(tableFile.exists()){
            isTableExists = true;
        }
        return isTableExists;
    }

}
