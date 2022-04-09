package Query.Process;

import Query.QueryCheck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateTest {

    QueryCheck queryParse = new QueryCheck();

    private String updateValid1 = "UPDATE todo SET todo_id = \"6\" WHERE todo == \"have some sleep\";";
    private String updateValid2 = "UPDATE todo SET todo = \"some good sleep\" WHERE todo_id == \"6\";";
    private String updateValid3 = "UPDATE todo SET is_completed = \"true\" WHERE todo_id == \"6\";";

    private String updateInvalid1 = "UPDATE todo SET is_completed = \"true\" WHERE todo_id == \"6\";";
    private String updateInvalid2 = "UPDATE todo SET todo_id = \"6\" WHERE todo == \"homework\";";
    private String updateInvalid3 = "UPDATE todo SET todo_id = \"3\" WHERE todo == \"homework\";";
    private String updateInvalid4 = "UPDATE todo SET is_completed = \"true\" WHERE todo_id == \"6\";";

    @Test
    public void UpdateValidTest1(){

        queryParse.queryCheck("USE TRIAL1;");

        System.out.println("Valid Update query---------------");
        assertEquals(true,queryParse.queryCheck(updateValid1));

    }

    @Test
    public void UpdateValidTest2(){

        queryParse.queryCheck("USE TRIAL1;");

        System.out.println("Valid Update query---------------");
        assertEquals(true,queryParse.queryCheck(updateValid2));


    }

    @Test
    public void UpdateValidTest3(){

        queryParse.queryCheck("USE TRIAL1;");

        System.out.println("Valid Update query---------------");
        assertEquals(true,queryParse.queryCheck(updateValid3));

    }

    @Test
    public void UpdateInvalidTests(){

        queryParse.queryCheck("USE TRIAL1;");

        System.out.println("Invalid update query -----------------");
        assertEquals(true,queryParse.queryCheck(updateInvalid1));
        assertEquals(false,queryParse.queryCheck(updateInvalid2));
        assertEquals(false,queryParse.queryCheck(updateInvalid3));
        assertEquals(true,queryParse.queryCheck(updateInvalid4));

    }
}
