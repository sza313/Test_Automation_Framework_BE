package restassured.testrunners;

import cucumber.api.cli.Main;
import org.junit.Test;

public class TestRunnerParallel {

    @Test
    public void testRest() {
        Main.main(new String[]{"--threads", "4",
                "-p", "json:target/test_result/test-report.json",
                "-p", "html:target/test_result/html-result",
                "-t","@API",
                "-g", "restassured/stepdefs",
                "src/test/resources/features/"});
    }
}
