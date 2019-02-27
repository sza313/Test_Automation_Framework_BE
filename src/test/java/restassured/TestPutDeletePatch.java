package restassured;

import cucumber.api.java.en.Given;
import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.hasKey;


/**
 * API PUT/DELETE/PATCH endpoints tested with Rest Assured
 * API endpoint information: https://reqres.in/
 * @author tamas.a.kiss
 */
public class TestPutDeletePatch {

    @Given("Delete user using request: '(.*)'")
    public void deleteUser(String request) {
        given().when().delete(request + TestPost.getUserId())
                .then().statusCode(204);
    }

    @Given("Verify that user is deleted using request: '(.*)'")
    public void verifyDelete(String request) {
       when().get(request + TestPost.getUserId()).then().statusCode(404);
    }

    @Given("Update existing user using request: '(.*)'")
    public void updateUser(String request) {
        Map<String, String> existingUser = new HashMap<>();
        existingUser.put("name", TestPost.getName());
        existingUser.put("job", TestPost.getJob());

        given().accept(ContentType.JSON).contentType(ContentType.JSON).body(existingUser)
                .when().put(request + TestPost.getUserId())
                .then().statusCode(200).assertThat().body("$",hasKey("updatedAt"));
    }

}