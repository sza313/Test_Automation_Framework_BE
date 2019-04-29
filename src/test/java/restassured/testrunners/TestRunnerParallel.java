package restassured.testrunners;

import cucumber.api.cli.Main;
import org.junit.Test;

@Deprecated
public class TestRunnerParallel {

    @Test
    public void testRest() {
        Main.run(new String[]{"--threads", "1",
                "-p", "json:target/test_result/test-report.json",
                "-p", "html:target/test_result/html-result",
                "-t","@API",
                "-g", "restassured/stepdefs",
                "src/test/resources/features/"},
                Thread.currentThread().getContextClassLoader());
    }

}
