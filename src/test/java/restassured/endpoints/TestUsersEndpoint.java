package restassured.endpoints;

import entity.User;
import entity.UserListResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.core.IsEqual.equalTo;


public class TestUsersEndpoint {

    private UserListResponse userListResponse;

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://localhost:4040";
    }

    @Test
    public void testGetCorrectUserId() {
        Response getUsers2Response = get("/api/users/2").then().extract().response();
        getUsers2Response.then().statusCode(200).assertThat().body("data.first_name", equalTo("Janet"))
                .and().body("data.last_name", equalTo("Weaver"));
    }

    @Test
    public void testGetIncorrectUserId() {
        Response getUsers2Response = get("/api/users/23").then().extract().response();
        getUsers2Response.then().statusCode(404);
    }

    @Test
    public void testPostCreateUser() {
        Map<String, String> newUser = new HashMap<>();
        newUser.put("name", "John");
        newUser.put("job", "Boss");

        given().accept(ContentType.JSON).contentType(ContentType.JSON).body(newUser).post("/api/users")
                .then()
                .statusCode(201)
                .body("name", CoreMatchers.equalTo("John"))
                .body("job", CoreMatchers.equalTo("Boss"))
                .body("createdAt", CoreMatchers.notNullValue());
    }

    @Test
    public void testUpdateUser() {
        Map<String, String> existingUser = new HashMap<>();
        existingUser.put("name", "Bob");
        existingUser.put("job", "Gardener");

        given().accept(ContentType.JSON).contentType(ContentType.JSON).body(existingUser)
                .when().put("/api/users" + "/123")
                .then().statusCode(200).assertThat().body("$", hasKey("updatedAt"));
    }

    @Test
    public void testDeleteUser() {
        given().when().delete("/api/users" + "/123")
                .then().statusCode(204);
        when().get("/api/users" + "/123").then().statusCode(404);
    }

    @DataProvider(name = "expectedDataForUsers")
    public Object[][] expectedDataForResources() {
        List<User> users = new ArrayList<>();
        try {
            List<String> inputLines = Files.readAllLines(Paths.get("src/test/resources/expected-data/users.csv"));
            inputLines.remove(0);
            inputLines.forEach(l -> users.add(new User(Arrays.asList(l.split(",")))));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return users.stream().map(r -> new Object[]{r})
                .toArray(Object[][]::new);
    }


    @Test
    public void testGetUserCollection() {
        this.userListResponse =
                get("/api/users?page=2").then().extract().response().getBody().as(UserListResponse.class);
    }

    @Test(dependsOnMethods = "testGetUserCollection", dataProvider = "expectedDataForUsers")
    public void testUserCorrectness(User user) {
        long match = userListResponse.getData().stream().filter(r -> r.equals(user)).count();
        assertThat("User - " + user.toString() + " is found",
                match, is(1l));
    }

}
