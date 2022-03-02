import com.aventstack.extentreports.ExtentTest;
/*
import com.intuit.karate.Results;
import com.intuit.karate.RuntimeHook;
import com.intuit.karate.core.Scenario;
import com.intuit.karate.core.ScenarioResult;
import com.intuit.karate.core.ScenarioRuntime;
*/
import com.intuit.karate.Results;
import com.intuit.karate.RuntimeHook;
import com.intuit.karate.core.ScenarioResult;
import com.intuit.karate.core.ScenarioRuntime;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class ExtentReportHook implements RuntimeHook {
    private static ExtentTest test;
    String Status, Error, Tags;


    @Before
    public boolean beforeScenario(Scenario scenario, ScenarioRuntime context) {
        return true;
    }

    @After
    public void afterScenario(ScenarioResult result, ScenarioRuntime context) {

        System.out.println("******" + "afterScenario"+ "******");
        if (result.isFailed()) {
            Status = "Failed";

        } else {
            Status = "Passed";
        }

        if (result.getError() == null) {
            Error = "No Error";
        } else {
            Error = result.getError().toString();
        }

        Tags = "";
        if (result.getScenario().getTags() == null) {
            Tags = "No Tags";
        } else {
            for (int z = 0; z < result.getScenario().getTags().size(); z++) {

                Tags = Tags + result.getScenario().getTags().get(z) + ",";
            }
            Tags = Tags.substring(0, Tags.length() - 1);
        }

        test = ExtentManager.getInstance().createTest("<b>Scenario: </b>" + result.getScenario().getName());
        //test.info("<b>Url: </b>" + context.getRequestBuilder().getUrlAndPath());
        test.info("<b>Feature: </b>" + result.getScenario().getFeature().getName());
        test.info("<b>Tag: </b>" + Tags);
        //test.info("<b>Method: </b>" + context.getPrevRequest().getMethod());
        test.info("<b>Status: </b>" + Status);
        if (Status == "Failed") {
            test.fail("<b>Error: </b>" + Error);
        }
        //String response = new String(context.getPrevResponse().getBody());
        //test.info("<b>Response:</b>" + response);

        System.out.println("******" + "afterScenario"+ "******");


    }

    @Before
    public void beforeAll(Results results) {
        ExtentManager.createReport();
    }

    @After
    public void afterAll(Results results) {
        ExtentManager.getInstance().flush();
    }
}