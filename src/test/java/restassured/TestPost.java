package restassured;

import cucumber.api.java.en.Given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.notNullValue;

/**
 * API POST endpoints tested with Rest Assured
 * API endpoint information: https://reqres.in/
 * @author tamas.a.kiss
 */
public class TestPost {

    private static int userId;
    private static String name;
    private static String job;
    private String token;


    /**
     * Test user creation
     * @param name Test data name of the user
     * @param job Test data job of the user
     * @param request The API endpoint
     */
    @Given("Create a user with name: (.*) job: (.*) using request: '(.*)'")
    public void createUser(String name, String job, String request) {
        Map<String, String> newUser = new HashMap<>();
        newUser.put("name", name);
        newUser.put("job", job);

        Response response =
        given().accept(ContentType.JSON).contentType(ContentType.JSON).body(newUser)
                .when().post(request)
                .then().statusCode(201).body("name", equalTo(name)).body("job", equalTo(job))
                .extract().response();
        userId = Integer.parseInt(response.path("id"));
        TestPost.name = name;
        TestPost.job = job;
    }

    /**
     *  Test user registration
     * @param email Test data email of the user
     * @param password Test data password of the user
     * @param request The API endpoint
     */
    @Given("Register user with email: (.*) password: (.*) using request: '(.*)'")
    public void registerUser(String email, String password, String request) {
        Map<String, String> newUser = new HashMap<>();
        newUser.put("email", email);
        newUser.put("password", password);

        Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(newUser)
                .when().post(request)
                .then().statusCode(201).body("$", hasKey("token")) //token is present in the response
                .body("any { it.key == 'token' }", is(notNullValue())) //token value is not null - has a value
                .extract().response();

        this.token = response.path("token").toString(); // extract the token from the response
    }

    /**
     * Test unsuccessful user registration
     * @param email Test data email of the user
     * @param request The API endpoint
     */
    @Given("Unsuccessful user registration with email: (.*) using request: '(.*)'")
    public void unsuccessfulUserRegistration(String email, String request) {
        Map<String, String> newUser = new HashMap<>();
        newUser.put("email", email);

        given().accept(ContentType.JSON).contentType(ContentType.JSON).body(newUser)
                .when().post(request)
                .then().statusCode(400).body("error", equalTo("Missing password"));
    }

    /**
     * Test user login successful
     * @param email Test data email of the user
     * @param password Test data password of the user
     * @param request The API endpoint
     */
    @Given("Login successful with email: (.*) password: (.*) using request '(.*)'")
    public void loginSuccessful(String email, String password, String request) {
        Map<String, String> existingUser = new HashMap<>();
        existingUser.put("email", email);
        existingUser.put("password", password);

        given().accept(ContentType.JSON).contentType(ContentType.JSON).body(existingUser)
                .when().post(request)
                .then().statusCode(200).body("token", equalTo(token));
    }

    /**
     * Test user login unsuccessful
     * @param email Test data email of the user
     * @param request The API endpoint
     */
    @Given("Login unsuccessful with email: (.*) using request: '(.*)'")
    public void loginUnsuccessful(String email, String request) {
        Map<String, String> existingUser = new HashMap<>();
        existingUser.put("email", email);

        given().accept(ContentType.JSON).contentType(ContentType.JSON).body(existingUser)
                .when().post(request)
                .then().statusCode(400).body("error", equalTo("Missing password"));
    }

    /**
     * Getter for user ID
     * @return the user ID
     */
    static int getUserId() {
        return userId;
    }

    /**
     * Getter for user name
     * @return the user name
     */
    static String getName() {
        return name;
    }

    /**
     * Getter for user job
     * @return the user job
     */
    static String getJob() {
        return job;
    }
}