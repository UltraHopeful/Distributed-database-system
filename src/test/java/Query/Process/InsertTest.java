package Query.Process;

import Query.QueryCheck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InsertTest {

    QueryCheck queryParse = new QueryCheck();

    String insertValid1 = "INSERT INTO  todo  VALUES  (\"16\",\"homework\",\"true\");";
    String insertValid2 = "INSERT INTO  todo  VALUES  (\"2\",\"make dinner\",\"false\");";
    String insertValid3 = "INSERT INTO  todo  VALUES  (\"3\",\"have some sleep\",\"false\");";
    String insertValid4 = "INSERT INTO  todo  VALUES  (\"4\",\"just lunch\",\"true\");";
    String insertInvalid1 = "INSERT INTO  VALUES (\"2\",\"make dinner\",\"false\");";
    String insertInvalid2 = "INSERT INTO  todo  VALUES  (\"2\",\"make dinner\",\"false\")";
    String insertInvalid3 = "INSERT INTO  todo  VALUES (\"2\",\"make dinner\");";
    String insertInvalid4 = "INSERT INTO  todo  VALUES  (\"2\",\"make dinner\",\"falsetrue\");";
    String insertInvalid5 = "INSERT INTO  todo  VALUES  (\"2\",\"make dinner\",\"false\"),(\"2\",\"make dinner\",\"false\");";

    @Test
    public void insertTests(){
        queryParse.queryCheck("USE trial1;");
        // TODO first delete all the files todo2.txt todo2@key.txt todo2@structure.txt
        System.out.println("insert valid query-----------------------------");
        System.out.println("insertValid1 = " + insertValid1);
        assertEquals(true, queryParse.queryCheck(insertValid1));
        // TODO
        System.out.println("insert valid query------------------------------");
        assertEquals(true, queryParse.queryCheck(insertValid2));
        System.out.println("insert valid query--------------------------");
        assertEquals(true, queryParse.queryCheck(insertValid3));
        System.out.println("insert valid query---------------------------");
        assertEquals(true, queryParse.queryCheck(insertValid4));
        System.out.println("insert Invalid query-------------------------");
        assertEquals(false, queryParse.queryCheck(insertInvalid1));
        System.out.println("insert Invalid query--------------------");
        assertEquals(false, queryParse.queryCheck(insertInvalid2));
        System.out.println("insert Invalid query-----------------------");
        assertEquals(false, queryParse.queryCheck(insertInvalid3));
        System.out.println();
        assertEquals(false, queryParse.queryCheck(insertInvalid4));
        System.out.println();

        assertEquals(false, queryParse.queryCheck(insertInvalid5));
        System.out.println();
    }

}
