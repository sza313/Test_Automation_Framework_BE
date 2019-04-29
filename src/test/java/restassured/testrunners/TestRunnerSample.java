package restassured.testrunners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@Deprecated
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "restassured/stepdefs",
        plugin = { "pretty", "html:target/test_result/html-result","json:target/test_result/test-report.json","junit:target/test_result/result.xml" },
        tags = {"@Sample"},
        monochrome = true)
public class TestRunnerSample {
}
