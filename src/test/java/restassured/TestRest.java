package restassured;

import cucumber.api.java.en.Given;
import entity.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.IntStream;

public class TestRest {

    private RequestSpecification httpRequest;
    private User user;

    @Given("Config URL: '(.*)'")
    public void setupUrl(String url) {
        // Specify the base URL to RESTful service
        RestAssured.baseURI = url;
    }

    @Given("Set request specification to given")
    public void setRequestSpecificationToGiven() {
        // Config the request specification we would like to sent
        this.httpRequest = RestAssured.given();
    }

    /**
     *
     * @param requestParameters
     * @return
     */
    @Given("Get a single user: '(.*)'")
    public User getOneUser(String requestParameters) {
        Response response = httpRequest.accept(ContentType.JSON).request(Method.GET,requestParameters);
        return null;
    }

    // RAW TEST will be re-factored
    @Given("Testing GET, parameters: '(.*)'")
    public void testingGet(String getParameters) {
        // Make a request, set content type, set the method: GET/POST/DELETE/PUT ect., give the url parameter
        Response response = httpRequest.accept(ContentType.JSON).request(Method.GET, getParameters);
        // Save the response(JSON) in a string
        String responseBody = response.getBody().asString();
        // Create a JSON object from the response
        JSONObject jsonObject = new JSONObject(responseBody);
        // Create JSON Array from the response
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        // Create a User from the response
        List<User> users = new ArrayList<>();


        IntStream.range(0, jsonArray.toList().size())
                .forEach(i -> users.add(new User(jsonArray.getJSONObject(i).getInt("id")
                        ,jsonArray.getJSONObject(i).getString("first_name")
                        ,jsonArray.getJSONObject(i).getString("last_name")
                        ,jsonArray.getJSONObject(i).getString("avatar"))));
        System.out.println(users);


    }
}
