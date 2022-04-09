package Query.Process;

import Query.GlobalConfig;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Delete {

    private Common common = new Common();
    private GlobalConfig globalConfig = new GlobalConfig();
    private String basePath = globalConfig.getBasePath();
    private String filePathSeparator = globalConfig.getPathSeparator();
    private String delimiter = globalConfig.getDelimiter();
    private String rowDelimiter = globalConfig.getRowDelimiter();

    public boolean check(String queryString){

        boolean isValidQuery = false;

        queryString = queryString.toLowerCase().trim();

        String deleteParseRegex = "(?:delete)\\s+(?:from)\\s+(?<table>\\w+)\\s+(?:where)\\s+(?<conditionColumn>\\w+)\\s*(?<condition>==|<|>|<=|>=|!=){1}\\s*(?:\\\"(?<conditionValue>.+)\\\");";

        Pattern deleteParsePattern = Pattern.compile(deleteParseRegex);

        Matcher deleteParseMatcher = deleteParsePattern.matcher(queryString);

        String currentDatabase;

        currentDatabase = globalConfig.getGlobalDatabase();
        if(currentDatabase != null){
            if(deleteParseMatcher.find()){
                String tableName = deleteParseMatcher.group("table").trim();
                System.out.println("tableName = " + tableName);
                if(common.tableCheck(tableName)){

                }
                else{
                    System.out.println(tableName+" table not found");
                    isValidQuery = false;
                }
            }
        }
        else{
            System.out.println("Please set default schema/database first");
            System.out.println("enter command : \n use <database_name>;");
            isValidQuery = false;
        }

        return isValidQuery;

    }
}
