package Query.Process;

import Query.GlobalConfig;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Update {

    Common common = new Common();
    GlobalConfig globalConfig = new GlobalConfig();
    String basePath = globalConfig.getBasePath();
    String filePathSeparator = globalConfig.getPathSeparator();
    String delimiter = globalConfig.getDelimiter();

    public boolean check(String queryString){

        boolean isValidQuery = false;

        String currentDatabase;

        queryString = queryString.toLowerCase();

        String updateParseRegex = "(?:UPDATE)\\s+(?<table>\\w+)\\s+(?:SET)\\s+(?<updateColumn>\\w+)\\s+(?:=)\\s+(?:\"(?<updateValue>.+)\")\\s+(?:WHERE)\\s+(?<conditionColumn>\\w+)\\s+(?<condition>==|<|>|<=|>=|!=){1}\\s+(?:\"(?<conditionValue>.+)\");";

        Pattern updateParsePattern = Pattern.compile(updateParseRegex);

        Matcher updateParseMatcher = updateParsePattern.matcher(queryString);


        System.out.println("Update detected");
        currentDatabase = globalConfig.getGlobalDatabase();

        if(currentDatabase != null) {
            if (updateParseMatcher.find()) {
                String tableName = updateParseMatcher.group("table").trim();
                if(common.tableCheck(tableName)){
                    String updateColumnName = updateParseMatcher.group("updateColumn").trim();
                    String updateColumnValue = updateParseMatcher.group("updateValue").trim();
                    String conditionColumnName = updateParseMatcher.group("conditionColumn");
                    String condition = updateParseMatcher.group("condition");
                    String conditionValue = updateParseMatcher.group("conditionValue");

                    List<String> columnNamesList = common.getColumnNames(tableName);

                    if(columnNamesList.contains(updateColumnName) && columnNamesList.contains(conditionColumnName)){
                        List<String[]> rowData = common.getData(tableName);
                        int updateColumnIndex = columnNamesList.indexOf(updateColumnName);
                        int conditionColumnIndex = columnNamesList.indexOf(conditionColumnName);



                    }
                    else{
                        System.out.println("Invalid column names");
                        isValidQuery = false;
                    }


                }
                else{
                    System.out.println(tableName+" table not found");
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
