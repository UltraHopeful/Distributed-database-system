package Query;
import Query.Parser.Create;
import Query.Parser.Use;

public class QueryCheck {

    Create parseCreate = new Create();
    Use parserUse = new Use();

    public void queryFormatPrint() {
        System.out.println("create database <database_name>;");
        System.out.println("create database if not exists <database_name>;");
    }

    public boolean queryCheck(String queryString){
        boolean isQueryValid = false;

        if(queryString.toLowerCase().contains("create")){
            if(parseCreate.check(queryString))
            {
                System.out.println("Parse success create query");
                isQueryValid = true;
            }
            else{
                System.out.println("Parse error in create query");
            }
        }
        else if(queryString.toLowerCase().contains("use")){
            if(parserUse.check(queryString)){
                System.out.println("Parse success use query");
                isQueryValid = true;
            }
            else{
                System.out.println("Parse error in use query");
            }
        }


        return isQueryValid;
    }
}
