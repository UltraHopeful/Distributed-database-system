package Query.Parser;

import Query.QueryCheck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateTest {

    QueryCheck queryParse = new QueryCheck();

    String createValidDB1 = "CREATE DATABASE IF NOT EXISTS Trial1;";
    String createValidDB2 = "CREATE DATABASE trial1;";
    String createInvalidDB1 = "CREATE DATABASE IF NOT EXISTS Trial1 trial2;";
    String createInvalidDB2 = "CREATE DATABASE trial1 trial2;";
    String createInvalidDB3 = "CREATE DATABASE";



    String createValidTable1 = "CREATE TABLE todo  ( todo_id INT, todo VARCHAR, is_completed BOOLEAN, PRIMARY KEY (todo_id), FOREIGN KEY (task_id) REFERENCES tasks (task_id) ) ;";
    String createValidTable2 = "CREATE TABLE todo  ( todo_id INT, todo VARCHAR, is_completed BOOLEAN, PRIMARY KEY (todo_id), FOREIGN KEY (task_id) REFERENCES tasks (task_id) ) ;";
    String createInvalidTable1 = "CREATE TABLE todo  (( todo_id INT, todo VARCHAR, is_completed BOOLEAN, PRIMARY KEY todo_id, FOREIGN KEY (task_id) REFERENCES tasks (task_id) ) ;";
    String createInvalidTable2 = "CREATE TABLE tod  (( todo_id INT, todo VARCHAR, is_completed BOOLEAN, PRIMARY KEY todo_id, FOREIGN KEY (task_id) REFERENCES tasks (task_id) ) ;";

    @Test
    public void createDBTest(){
        assertEquals(true, queryParse.queryCheck(createValidDB1));
        System.out.println();
        assertEquals(true, queryParse.queryCheck(createValidDB2));
        System.out.println();
        assertEquals(false, queryParse.queryCheck(createInvalidDB1));
        System.out.println();
        assertEquals(false, queryParse.queryCheck(createInvalidDB2));
        System.out.println();
        assertEquals(false, queryParse.queryCheck(createInvalidDB3));
    }

    @Test
    public void createTableTest(){
        assertEquals(true, queryParse.queryCheck(createValidTable1));
        System.out.println();
        assertEquals(true, queryParse.queryCheck(createValidTable2));
        System.out.println();
        assertEquals(false, queryParse.queryCheck(createInvalidTable1));
        System.out.println();
        assertEquals(false, queryParse.queryCheck(createInvalidTable2));
        System.out.println();
//        assertEquals(false, queryParse.queryCheck(createInvalidDB3));
    }

}