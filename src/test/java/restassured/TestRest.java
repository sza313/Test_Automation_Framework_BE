package restassured;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.RestConfig;
import cucumber.api.java.en.Given;
import entity.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import static io.restassured.RestAssured.get;

public class TestRest {

    private RequestSpecification httpRequest;
    private User user;
    private RestConfig config;
    private List<User> users;
    private static final Logger LOGGER = Logger.getLogger(TestRest.class.getName());


//    @Given("Config URL: '(.*)'")
//    public void setupUrl(String url) {
//        // Specify the base URL to RESTful service
//        RestAssured.baseURI = url;
//    }

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
        Response response = httpRequest.accept(ContentType.JSON).request(requestParameters);
        return null;
    }

    // RAW TEST will be re-factored
    @Given("Testing create users parameters: (.*)")
    public void testingGet(String getParameters) {

        ObjectMapper objectMapper = new ObjectMapper();
        List<User> users = new ArrayList<>();
     //   RestAssured.baseURI = url;
//        config.configRestApiEndpoint(getParameters,"json","GET");

        // Make a request, set content type, set the method: GET/POST/DELETE/PUT ect., give the url parameter
       // Response response = httpRequest.accept(ContentType.JSON).request(Method.GET, getParameters);

      //  httpRequest = config.configRestApiEndpoint(url,"json","GET");
     //   Response response = httpRequest.request(getParameters);
        // Save the response(JSON) in a string


        String responseBody = get(getParameters).then().extract().response().getBody().asString();
        JSONObject jsonObject = new JSONObject(responseBody);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        //Use object mapper
        try {
            users = objectMapper.readValue(jsonArray.toString(), new TypeReference<List<User>>(){});
        } catch (IOException e) {
            LOGGER.warning("Cannot parse User entity from response");
            LOGGER.log(Level.SEVERE,e.toString(), e);
        }

        // Create a JSON object from the response

        // Create JSON Array from the response


        // Create a User from the response



//        IntStream.range(0, jsonArray.toList().size())
//                .forEach(i -> users.add(new User(jsonArray.getJSONObject(i).getInt("id")
//                        ,jsonArray.getJSONObject(i).getString("first_name")
//                        ,jsonArray.getJSONObject(i).getString("last_name")
//                        ,jsonArray.getJSONObject(i).getString("avatar"))));
        System.out.println(users);
    }

    @Given("Test Rest")
    public void testRest() {
        httpRequest.accept(ContentType.JSON);
        httpRequest.request(Method.GET);

    }

    //    @Given("Init request with content type: '(.*)'")
//    public void initRequest(String contentType) {
//        requestSpecification = new RequestSpecBuilder()
//                .setContentType(getContentType(contentType))
//                .setAccept(getContentType(contentType)).build();
//    }

    private ContentType getContentType(String contentType) {
        ContentType content = ContentType.JSON;
        for (ContentType c : ContentType.values()) {
            if (c.matches("(.*)" + contentType + "(.*)")) {
                content = c;
            }
        }
        return content;
    }

    private Method getMethod(String requestMethod) {
        Method method = Method.GET;
        for (Method m : Method.values()) {
            if (m.toString().equalsIgnoreCase(requestMethod)) {
                method = m;
            }
        }
        return method;
    }
}
