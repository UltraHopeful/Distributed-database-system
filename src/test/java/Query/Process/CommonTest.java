package Query.Process;

import Query.QueryCheck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommonTest {

    Common common = new Common();

    QueryCheck queryParse = new QueryCheck();
    
    @Test
    public void metadataTests(){

        queryParse.queryCheck("USE trial1;");
        
        String tableName = "todo";
        
        System.out.println("common.getPrimaryKey(tableName) = " + common.getPrimaryKey(tableName).toString());
        System.out.println("common.getStructure(tableName) = " + common.getStructure(tableName).toString());
        System.out.println("common.getForeignKeys(tableName) = " + common.getForeignKeys(tableName).toString());
        System.out.println("common.getPrimaryKeyList(tableName).toString() = " + common.getPrimaryKeyList(tableName).toString());
    }
    

}
