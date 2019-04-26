package restassured.get;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.core.IsEqual.equalTo;


public class TestGetUsers {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://localhost:4040";
    }

    @Test
    public void testCorrectUserId() {
        Response getUsers2Response = get("/api/users/2").then().extract().response();
        getUsers2Response.then().statusCode(200).assertThat().body("data.first_name", equalTo("Janet"))
                .and().body("data.last_name", equalTo("Weaver"));
    }

    @Test
    public void testIncorrectUserId() {
        Response getUsers2Response = get("/api/users/23").then().extract().response();
        getUsers2Response.then().statusCode(404);
    }
}
