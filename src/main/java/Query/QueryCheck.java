package Query;
import Query.Process.Create;
import Query.Process.Insert;
import Query.Process.Use;

public class QueryCheck {

    Create parseCreate = new Create();
    Use parseUse = new Use();
    Insert parseInsert = new Insert();

    public void queryFormatPrint() {
        System.out.println("create database <database_name>;");
        System.out.println("create database if not exists <database_name>;");
    }

    public boolean queryCheck(String queryString){
        boolean isQueryValid = false;

        queryString = queryString.trim();

        if(queryString.toLowerCase().startsWith("create")){
            if(parseCreate.check(queryString))
            {
                System.out.println("Parse success create query");
                isQueryValid = true;
            }
            else{
                System.out.println("Parse error in create query");
            }
        }
        else if(queryString.toLowerCase().startsWith("use")){
            if(parseUse.check(queryString)){
                System.out.println("Parse success use query");
                isQueryValid = true;
            }
            else{
                System.out.println("Parse error in use query");
            }
        }
        else if(queryString.toLowerCase().startsWith("insert")){
            if(parseInsert.check(queryString)){
                System.out.println("Parse success insert query");
                isQueryValid = true;
            }
            else{
                System.out.println("Parse error in insert query");
            }
        }
        else if(queryString.toLowerCase().startsWith("select")){
            System.out.println("Select query");
//            if(parserUse.check(queryString)){
//                System.out.println("Parse success select query");
//                isQueryValid = true;
//            }
//            else{
//                System.out.println("Parse error in select query");
//            }
        }
        else if(queryString.toLowerCase().startsWith("update")){
            System.out.println("update query");
//            if(parserUse.check(queryString)){
//                System.out.println("Parse success update query");
//                isQueryValid = true;
//            }
//            else{
//                System.out.println("Parse error in update query");
//            }
        }
        else if(queryString.toLowerCase().startsWith("delete")){
            System.out.println("delete query");
//            if(parserUse.check(queryString)){
//                System.out.println("Parse success delete query");
//                isQueryValid = true;
//            }
//            else{
//                System.out.println("Parse error in delete query");
//            }
        }
        else{
            System.out.println("Invalid query type");
        }


        return isQueryValid;
    }
}
