package stepdefinitions;
import base.DriverFactory;
import base.ReportManager;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Hooks {

    private Properties config;

    @Before
    public void before(Scenario scenario) throws IOException {
        config = new Properties();
        config.load(new FileInputStream("src/test/resources/config.properties"));

        //Always ensure report exists
        ReportManager.initReports();
        ReportManager.createTest(scenario.getName());

        String execType = config.getProperty("executionType");
        if (!execType.equalsIgnoreCase("api")) {
            DriverFactory.initDriver(config);
        } else {
            System.out.println("API mode – skipping Appium driver startup.");
        }

    }

    @After
    public void after(Scenario scenario) {
        if (scenario.isFailed()) {
            ReportManager.getTest().fail("Scenario failed: " + scenario.getName());
        } else {
            ReportManager.getTest().pass("Scenario passed: " + scenario.getName());
        }

        DriverFactory.quitDriver();
        ReportManager.flushReports();
    }
}
