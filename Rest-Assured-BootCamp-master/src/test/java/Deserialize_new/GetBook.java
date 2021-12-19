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

public class GetBook {

    @Test
    public void getBook() {
        Book response= RestAssured.given().when().get("https://bookstore.toolsqa.com/BookStore/v1/Book?ISBN=9781449325862")
                .as(Book.class);
        //now passing to book
        RestAssured.given().queryParam("ISBN",response.isbn, ObjectMapperType.JAXB).when()
                .get("https://bookstore.toolsqa.com/BookStore/v1/Book").then().log().all();
        Response resp = get("https://bookstore.toolsqa.com/BookStore/v1/Book");
        int statusCode = resp.getStatusCode();
        Assert.assertEquals(statusCode,200);
        if(statusCode == 200){
           RegistrationSuccessResponse successResponse =  new RegistrationSuccessResponse() ;
           Assert.assertEquals( successResponse.SuccessCode,"200");
           Assert.assertEquals(statusCode,200);
        }
        else{
                RegistrationFailureResponse FailResponse =  new RegistrationFailureResponse() ;
                Assert.assertEquals(statusCode,400);
        }


        String BookPublisher = response.publisher;
        int BookPages = response.pages;
        Assert.assertEquals(BookPublisher,"O'Reilly Media");
        Assert.assertEquals(BookPages,234);

//        Assert.assertTrue(Status.SUCCESS.matches(200));
//        Assert.assertTrue(Status.FAILURE.matches(503));

    }
//    @Test
//    public void getBookList() {
//
//        BookList response= RestAssured.given().when()
//                .get("https://bookstore.toolsqa.com/BookStore/v1/Book?ISBN=9781449325862")
//                .as(BookList.class);
//        System.out.println(response.books.get(0).pages);
//    }
//    @Test
//    public void getBookWithSerialization() {
//        Request request=new Request();
//
//       RestAssured.given().queryParam("ISBN",request.ISBN, ObjectMapperType.JAXB).when()
//                .get("https://bookstore.toolsqa.com/BookStore/v1/Book").then().log().all();
//    }
}

