import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {
        "pretty",
        "html:target/cucumber-report.html",
        "html:target/cucumber-reports/report.html",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
    features = {"src/test/java/feature/features/feature1.feature"},
    publish = true
)
public class KarateRunner {
   @Test
   public void testKarateTest() throws Exception{
      //test 1
      //Results r = Runner.path("classpath:feature/features").outputCucumberJson(true).parallel(1);
      Results r  = Runner.path("C:/Users/SOL INVICTUS/IdeaProjects/KarateReporting/src/test/java/feature/features")
              .hook(new ExtentReportHook())
              .outputCucumberJson(true)
              .outputHtmlReport(true)
              .parallel(2);
      generateReport(r.getReportDir());

   }

   public static void generateReport(String karateOutputPath) throws Exception{
      Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[] {"json"}, false);
      final List<String> jsonPaths = new ArrayList<>(jsonFiles.size());
      jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
      Configuration config = new Configuration(new File("target"), "KarateReporting");

      System.out.println("________________");
      System.out.println(config.getReportDirectory());
      System.out.println(karateOutputPath);

      System.out.println(jsonPaths);
      System.out.println("________________");

      ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
      reportBuilder.generateReports();
   }
}
