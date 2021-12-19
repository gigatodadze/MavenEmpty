package Meeting3;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Map;

public class JsonPathExamples {
    @Test
    public void testGetSingleUser() {
        Response res = get("https://demoqa.com/BookStore/v1/Books");
        assertEquals(200, res.getStatusCode());
      /*  String json = res.asString();
        JsonPath jp =new JsonPath(json);*/
        assertEquals("9781449325862", res.jsonPath().get("books.isbn"));
//        System.out.println();
    }

}
