package Query.Process;

import Query.GlobalConfig;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Insert {

    GlobalConfig globalConfig = new GlobalConfig();
    String basePath = globalConfig.getBasePath();
    String filePathSeparator = globalConfig.getPathSeparator();
    String delimeter = globalConfig.getDelimeter();

    public boolean check(String queryString) {
        boolean isValidQuery = false;

        String currentDatabase;

        String insertParseRegex = "(?:INSERT INTO)\\s+(?<table>\\w+)\\s+(?:VALUES)\\s+(?:[(](?<values>.+)[)]);";

        String insertValueRegex = "(?:\\\"(?<value>\\w+)\\\"(?:,|$))";

        Pattern insertParsePattern = Pattern.compile(insertParseRegex, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
        Pattern insertValuePattern = Pattern.compile(insertParseRegex, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);

        Matcher insertParseMatcher = insertParsePattern.matcher(queryString);

        if (queryString.toLowerCase().contains("insert")) {
            System.out.println("insert table detected");
            if (insertParseMatcher.find()) {
                if (insertParseMatcher.group("table").isEmpty() || insertParseMatcher.group("table").isBlank()) {
                    System.out.println("Invalid query no table name found");
                    isValidQuery = false;
                } else {
                    System.out.println("Valid insert query");

                }
            } else {
                System.out.println("Insert invalid query");
                isValidQuery = false;
            }

        }

        return isValidQuery;
    }

}
