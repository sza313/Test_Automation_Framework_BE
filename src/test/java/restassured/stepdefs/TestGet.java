package restassured.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.get;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;

/**
 * API GET endpoints tested with Rest Assured
 * API endpoint information: https://reqres.in/
 * @author tamas.a.kiss
 */
@Deprecated
public class TestGet {

    private Response singleUserResponse;
    private Response singleResourceResponse;
    private Response userCollectionResponse;
    private Response resourceCollectionResponse;

    /**
     * Config the base URL for Rest assured
     * @param url the base URL
     */
    @Given("Config URL: '(.*)'")
    public void setupUrl(String url) {
        RestAssured.baseURI = url;
    }

    /**
     * Test a single user response
     * @param request The API endpoint
     */
    @When("Get a single user with request: '(.*)'")
    public void testSingleUserByName(String request) {
        this.singleUserResponse = get(request).then().extract().response();
    }

    /**
     * Verify user with name
     * @param firstName Test data first name
     * @param lastName Test data last name
     */
    @Then("Verify a single user by first name: (.*) and last name: (.*)")
    public void verifySingleUserByName(String firstName, String lastName) {
        singleUserResponse.then().statusCode(200).assertThat().body("data.first_name", equalTo(firstName))
                .and().body("data.last_name", equalTo(lastName));
    }

    /**
     * Test a single resource response
     * @param request The API endpoint
     */
    @When("Get a single resource with request: '(.*)'")
    public void testSingleResource(String request) {
        this.singleResourceResponse = get(request).then().extract().response();
    }

    /**
     * Verify a single resource with name
     * @param name Test data name
     */
    @Then("Verify a single resource by first name: (.*)")
    public void verifySingleResourceByName(String name) {
        this.singleResourceResponse.then().statusCode(200).assertThat().body("data.name", equalTo(name));
    }

    /**
     * Test multiple user response
     * @param request The API endpoint
     */
    @When("Test a collection of users with request: '(.*)'")
    public void testListOfUsersById(String request) {
        this.userCollectionResponse = get(request).then().extract().response();
    }

    /**
     * Check if the collection contains the user ID's
     * @param userId Test data user ID
     */
    @Then("The collection contain the user ID: '(.*)'")
    public void checkUserId(String userId) {
        this.userCollectionResponse.then().statusCode(200).body("data.id", hasItems(Integer.parseInt(userId)));
    }

    /**
     * Test multiple resource response
     * @param request The API endpoint
     */
    @When("Test a collection of resources with request: '(.*)'")
    public void testListOfResources(String request) {
        this.resourceCollectionResponse = get(request).then().extract().response();
    }

    /**
     * Check if the collection contains the resource names
     * @param name Test data resource name
     */
    @Then("The collection contain the resource name: '(.*)'")
    public void checkResourceName(String name) {
        this.resourceCollectionResponse.then().statusCode(200).body("data.name", hasItems(name));
    }

    /**
     * Test user not found
     * @param id Test data ID of the user
     * @param request The API endpoint
     */
    @Given("Test user not found by ID: (.*) with request: '(.*)'")
    public void testUserNotFound(String id, String request) {
        when().get(request + id).then().statusCode(404);
    }

    /**
     * Test resource not found
     * @param id Test data ID of the resource
     * @param request The API endpoint
     */
    @Given("Test a single resource not found by ID: (.*) with request: '(.*)'")
    public void testResourceNotFound(String id, String request) {
        when().get(request + id).then().statusCode(404);
    }

    /**
     * Test API response time
     * @param delay Test data the delay time
     * @param request The API endpoint
     */
    @Given("Test delayed response with delay time: (.*) with request: '(.*)'")
    public void testDelayedResponse(String delay, String request) {
        long time = when().get(request + delay).then().statusCode(200).and().extract().response().time();
        assertThat(time, greaterThanOrEqualTo((long) Integer.parseInt(delay)));
    }
}
