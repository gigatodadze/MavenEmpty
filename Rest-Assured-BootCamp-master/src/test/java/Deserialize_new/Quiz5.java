package Deserialize_new;

import Deserialize.RegistrationFailureResponse;
import Deserialize.RegistrationSuccessResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.internal.http.Status;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.get;

public class Quiz5 {

    @Test
    public void getBook() {
        Book response= RestAssured.given().when().get("https://bookstore.toolsqa.com/BookStore/v1/Book?ISBN=9781449325862")
                .as(Book.class);
        //now passing to book
        RestAssured.given().queryParam("ISBN",response.isbn, ObjectMapperType.JAXB).when()
                .get("https://bookstore.toolsqa.com/BookStore/v1/Book").then().log().all();
        Response resp = get("https://bookstore.toolsqa.com/BookStore/v1/Book");
        if(resp.statusCode() == 200){
            RegistrationSuccessResponse successResponse =  new RegistrationSuccessResponse() ;
            Assert.assertEquals( successResponse.SuccessCode,"200");
            Assert.assertEquals( resp.statusCode(),200);
        }
        else{
            if(resp.statusCode() == 400){
                RegistrationFailureResponse FailResponse =  new RegistrationFailureResponse() ;
                Assert.assertEquals(resp.statusCode(),400);
            }
            else{
                Assert.fail();
            }
        }


//        Assert.assertTrue(Status.SUCCESS.matches(200));
//        Assert.assertTrue(Status.FAILURE.matches(503));

    }

}

