package restassured.endpoints;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

public class TestLoginEndpoint {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://localhost:4040";
    }

    @Test
    public void testLoginUser() {
        Map<String, String> newUser = new HashMap<>();
        newUser.put("email", "test@gmail.com");
        newUser.put("password", "password");

        given().accept(ContentType.JSON).contentType(ContentType.JSON).body(newUser)
                .post("/api/login")
                .then()
                .statusCode(200)
                .body("$", hasKey("token"))
                .body("token", CoreMatchers.notNullValue());
    }

    @Test
    public void testLoginUserError() {
        Map<String, String> newUser = new HashMap<>();
        newUser.put("email", "bademail@gmail.com");

        given().accept(ContentType.JSON).contentType(ContentType.JSON).body(newUser)
                .post("/api/login")
                .then()
                .statusCode(400)
                .body("error", CoreMatchers.is("Missing password"));
    }
}
