package restassured.endpoints;

import entity.Resource;
import entity.ResourceListResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


public class TestResourcesEndpoint {

    private ResourceListResponse resourceListResponse;

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://localhost:4040";
    }

    @Test
    public void testCorrectUserId() {
        Response getUsers2Response = get("/api/unknown/2").then().extract().response();
        getUsers2Response.then().statusCode(200).assertThat().body("data.name", equalTo("fuchsia rose"));
    }

    @Test
    public void testIncorrectUserId() {
        Response getUsers2Response = get("/api/unknown/23").then().extract().response();
        getUsers2Response.then().statusCode(404);
    }

    @DataProvider(name = "expectedDataForResources")
    public Object[][] expectedDataForResources() {
        List<Resource> resources = new ArrayList<>();
        try {
            List<String> inputLines = Files.readAllLines(Paths.get("src/test/resources/expected-data/resources.csv"));
            inputLines.remove(0);
            inputLines.forEach(l -> resources.add(new Resource(Arrays.asList(l.split(",")))));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return resources.stream().map(r -> new Object[]{r})
                .toArray(Object[][]::new);
    }

    @Test
    public void getResourceCollection() {
        this.resourceListResponse =
                get("/api/unknown").then().extract().response().getBody().as(ResourceListResponse.class);
    }

    @Test(dependsOnMethods = "getResourceCollection", dataProvider = "expectedDataForResources")
    public void checkUserCorrectness(Resource resource) {
        long match = resourceListResponse.getResourceList().stream().filter(r -> r.equals(resource)).count();
        assertThat("Resource - " + resource.toString() + " is found",
                match, is(1l));
    }

}