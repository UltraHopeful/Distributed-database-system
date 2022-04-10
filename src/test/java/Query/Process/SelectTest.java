package Query.Process;

import Query.QueryCheck;
import org.junit.jupiter.api.Test;

public class SelectTest {

    private QueryCheck queryParse = new QueryCheck();

    private String selectValid1 = "SELECT * FROM todo;";
    private String selectValid2 = "SELECT todo_id FROM todo;";
    private String selectValid3 = "SELECT todo_id,todo FROM todo;";
    private String selectValid4 = "SELECT * FROM todo WHERE todo_id == \"6\";";
    private String selectValid5 = "SELECT todo FROM todo WHERE todo_id == \"6\";";
    private String selectValid6 = "SELECT todo_id,todo,is_completed FROM todo WHERE todo_id <= \"6\";";
    private String selectValid7 = "SELECT todo_id,todo,is_completed FROM todo WHERE todo_id > \"6\";";

    private String selectInvalid1 = "";

//    @Test
//    public void setSelectValid1(){
//        queryParse.queryCheck("USE trial1;");
//
//        queryParse.queryCheck(selectValid1);
//        queryParse.queryCheck(selectValid2);
//        queryParse.queryCheck(selectValid3);
//        queryParse.queryCheck(selectValid4);
//        queryParse.queryCheck(selectValid5);
//        queryParse.queryCheck(selectValid6);
//        queryParse.queryCheck(selectValid7);
//
//        queryParse.queryCheck(selectInvalid1);
//    }

}
