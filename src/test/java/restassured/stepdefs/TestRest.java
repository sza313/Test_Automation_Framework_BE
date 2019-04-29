package restassured.stepdefs;

import cucumber.api.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@Deprecated
public class TestRest {

    @Given("Set up URL: '(.*)'")
    public void setupUrl(String url) {
        // Specify the base URL to base RESTful service
        RestAssured.baseURI = url;
    }

    // RAW TEST will be re-factored
    @Given("Testing GET, parameters: '(.*)'")
    public void testingGet(String getParameters) {
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.accept(ContentType.JSON).request(Method.GET, "/users?page=2");
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);
    }
}
