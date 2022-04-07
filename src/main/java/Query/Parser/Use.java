package Query.Parser;

import Query.GlobalConfig;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Use {

    GlobalConfig globalConfig = new GlobalConfig();

    String basePath = globalConfig.getBasePath();

    public boolean check(String queryString){
        boolean isValidQuery = false;
        String currentDataBase = globalConfig.getGlobalDatabase();

        String useParseRegex = "(?:USE)\\s*(\\w+)(?:;)";

        Pattern useParsePattern = Pattern.compile(useParseRegex, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);

        Matcher useParseMatcher = useParsePattern.matcher(queryString);

        if(useParseMatcher.find()){
            String dbName = useParseMatcher.group(1);
            System.out.println("basePath = " + basePath);
            File folder = new File(basePath+dbName);
            if(folder.exists()){
                System.out.println("Valid Query");
//                System.out.println("dbName = " + dbName);
                globalConfig.setGlobalDatabase(dbName);
                System.out.println("dbName = " + globalConfig.getGlobalDatabase());
                isValidQuery = true;
            }
            else{
                System.out.println("invalid query");
                isValidQuery = false;
            }
        }
        else{
            System.out.println("Invalid query");
            isValidQuery = false;
        }

        return isValidQuery;
    }



}
