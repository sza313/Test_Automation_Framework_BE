package restassured;

import cucumber.api.java.en.Given;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;


import io.restassured.http.Method;


public class TestGet {

    @Given("Config URL: '(.*)'")
    public void setupUrl(String url) {
        // Specify the base URL to RESTful service
        RestAssured.baseURI = url;
    }

//    @Given("Init request with content type: '(.*)'")
//    public void initRequest(String contentType) {
//        requestSpecification = new RequestSpecBuilder()
//                .setContentType(getContentType(contentType))
//                .setAccept(getContentType(contentType)).build();
//    }

    @Given("Test a single user name with request: '(.*)' and first name: (.*) last name: '(.*)'")
    public void testSingleUser(String request, String firstName, String lastName) {
         when().get(request).then().statusCode(200).assertThat().body("data.first_name", equalTo(firstName))
                 .and().body("data.last_name", equalTo(lastName));
    }

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
