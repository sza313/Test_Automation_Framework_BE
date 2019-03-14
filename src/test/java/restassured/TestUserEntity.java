package restassured;

import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import entity.User;
import entity.UserListResponse;
import org.junit.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.restassured.RestAssured.get;

public class TestUserEntity {

    private static final Logger LOGGER = Logger.getLogger(TestUserEntity.class.getName());
    private UserListResponse userListResponse;
    private List<User> users = new ArrayList<>();


    @When("Create user entity's using request '(.*)'")
    public void getUserList(String request) {
        ObjectMapper objectMapper = new ObjectMapper();
        String responseBody = get(request).then().extract().response().getBody().asString();
        try {
            this.userListResponse = objectMapper.readValue(responseBody, UserListResponse.class);
        } catch (IOException e) {
            LOGGER.warning("Cannot parse User entity from response");
            LOGGER.log(Level.SEVERE,e.toString(), e);
        }
        this.users = userListResponse.getData();
    }

    @Then("User is present by first name: '(.*)'")
    public void validateUsers(String firstName) {
        Assert.assertTrue(users.stream().anyMatch(u -> u.getFirstName().equals(firstName)));
    }
}
