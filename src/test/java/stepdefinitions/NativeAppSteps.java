
package stepdefinitions;

import Pages.NativeAppPage;
import base.BaseTest;
import base.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class NativeAppSteps extends BaseTest {
    private WebDriver driver;
    private Properties config;

    public NativeAppSteps() {
        driver = DriverFactory.getDriver();
        config = new Properties();
        try {
            config.load(new FileInputStream("src/test/resources/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    NativeAppPage nativeAppPage = new NativeAppPage(DriverFactory.getDriver());
    @Given("I am on the animation test page")
    public void i_am_on_the_animation_test_page() {

        nativeAppPage.clickOnAnimationOption();
        nativeAppPage.animationPageIsDisplayed();
    }
    @When("I click the Event option")
    public void i_click_the_event_option() {
        nativeAppPage.clickOnEventsOption();
    }

}
