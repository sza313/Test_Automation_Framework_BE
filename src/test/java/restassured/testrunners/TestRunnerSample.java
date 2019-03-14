package restassured.testrunners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "restassured",
        plugin = { "pretty", "html:target/test_result/html-result","json:target/test_result/test-report.json","junit:target/test_result/result.xml" },
        tags = {"@API"},
        monochrome = true)
public class TestRunnerSample {
}
