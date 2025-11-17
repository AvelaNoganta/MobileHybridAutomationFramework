package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@CucumberOptions(
        features = "src/test/resources/features/",
        glue = {"stepdefinitions"},
        plugin = {
                "pretty",
                "html:Reports/CucumberReport.html",
                "summary"
        },
        monochrome = true,
        tags =" @mobileWeb or @login or @mobileWeb or @registration or @nativeApp"
)
public class RunCucumberTests extends AbstractTestNGCucumberTests {

    @BeforeClass(alwaysRun = true)
    public static void beforeClass() throws IOException {
        Properties config = new Properties();
        config.load(new FileInputStream("src/test/resources/config.properties"));

        String execType = config.getProperty("executionType", "mobileWeb").toLowerCase();
        String featurePath = switch (execType) {
            case "mobileweb" -> "src/test/resources/features/mobileWeb";
            case "nativeapp" -> "src/test/resources/features/nativeApp";
            default -> "src/test/resources/features";
        };
        System.setProperty("cucumber.features", featurePath);
        System.out.println("Running features from: " + featurePath);
    }

    @DataProvider(parallel = false)
    @Override
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
