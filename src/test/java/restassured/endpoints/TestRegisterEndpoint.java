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

public class TestRegisterEndpoint {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://localhost:4040";
    }

    @Test
    public void testRegisterUser() {
        Map<String, String> newUser = new HashMap<>();
        newUser.put("email", "testForLife");
        newUser.put("password", "itsASecretToEveryone");

        given().accept(ContentType.JSON).contentType(ContentType.JSON).body(newUser)
                .post("/api/register")
                .then()
                .statusCode(201)
                .body("$", hasKey("token"))
                .body("token", CoreMatchers.notNullValue());
    }

    @Test
    public void testRegisterUserError() {
        Map<String, String> newUser = new HashMap<>();
        newUser.put("email", "notgood@stuff.com");

        given().accept(ContentType.JSON).contentType(ContentType.JSON).body(newUser)
                .post("/api/register")
                .then()
                .statusCode(400)
                .body("error", CoreMatchers.is("Missing password"));
    }
}
