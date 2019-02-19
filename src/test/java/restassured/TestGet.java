package restassured;

import cucumber.api.java.en.Given;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.when;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;

/**
 * API GET endpoints tested with Rest Assured
 * API endpoint information: https://reqres.in/
 * @author tamas.a.kiss
 */
public class TestGet {

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
     * @param firstName Test data first name of the user
     * @param lastName Test data last name of the user
     * @param request The API endpoint
     */
    @Given("Test a single user by first name: (.*) and last name: (.*) with request: '(.*)'")
    public void testSingleUserByName(String firstName, String lastName, String request) {
        when().get(request).then().statusCode(200).assertThat().body("data.first_name", equalTo(firstName))
                .and().body("data.last_name", equalTo(lastName));
    }

    /**
     * Test a single resource response
     * @param name Test data name
     * @param request The API endpoint
     */
    @Given("Test a single resource by name: (.*) with request: '(.*)'")
    public void testSingleResource(String name, String request) {
        when().get(request).then().statusCode(200).assertThat().body("data.name", equalTo(name));
    }

    /**
     * Test multiple user response
     * @param idOne Test data ID of the first user
     * @param idTwo Test data ID of the second user
     * @param idThree Test data ID of the third user
     * @param request The API endpoint
     */
    @Given("Test a list of users by ID's: (.*), (.*), (.*) with request: '(.*)'")
    public void testListOfUsersById(String idOne, String idTwo, String idThree, String request) {
        when().get(request).then().statusCode(200)
                .body("data.id", hasItems(Integer.parseInt(idOne), Integer.parseInt(idTwo), Integer.parseInt(idThree)));
    }

    /**
     * Test multiple resource response
     * @param nameOne Test data name of the first resource
     * @param nameTwo Test data name of the second resource
     * @param nameThree Test data name of the third resource
     * @param request The API endpoint
     */
    @Given("Test a list of resources by name: (.*), (.*), (.*) with request: '(.*)'")
    public void testListOfResources(String nameOne, String nameTwo, String nameThree, String request) {
        when().get(request).then().statusCode(200)
                .body("data.name", hasItems(nameOne, nameTwo, nameThree));
    }

    /**
     * Test user not found
     * @param id Test data ID of the user
     * @param request The API endpoint
     */
    @Given("Test user not found by ID: (.*) with request: '(.*)'")
    public void testUserNotFound(String id, String request) {
        when().get(request + id).then().statusCode(404).and();
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
    @Given("Test delayed response with delay time: (.*) and request: '(.*)'")
    public void testDelayedResponse(String delay, String request) {
        long time = when().get(request + delay).then().statusCode(200).and().extract().response().time();
        assertThat(time, greaterThanOrEqualTo((long) Integer.parseInt(delay)));
    }
}
