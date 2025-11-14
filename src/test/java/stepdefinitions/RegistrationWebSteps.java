
package stepdefinitions;

import Pages.HomePage;
import Pages.SignUpPage;
import base.BaseTest;
import base.DriverFactory;
import base.ReportManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.PostmanRunner;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class RegistrationWebSteps extends BaseTest {


    private WebDriver driver;
    private Properties config;

    public RegistrationWebSteps() {
        driver = DriverFactory.getDriver();
        config = new Properties();
        try {
            config.load(new FileInputStream("src/test/resources/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    HomePage homePage = new HomePage(DriverFactory.getDriver());
    SignUpPage signUpPage = new SignUpPage(DriverFactory.getDriver());

    @Given("I launch the mobile web browser")
    public void i_launch_the_mobile_web_browser() {
        ReportManager.getTest().info("✅ Mobile Chrome browser launched.");
    }

    @When("I navigate to the Ndosi Automation homepage")
    public void i_navigate_to_homepage() {
        String url = config.getProperty("webUrl");
        ReportManager.getTest().info("Navigating to: " + url);
        driver.get(url);
    }

    @Then("I should see the page title")
    public void i_should_see_page_title() {
        String title = driver().getTitle();
        ReportManager.getTest().info("Page title: " + title);
        Assert.assertTrue(title != null && title.length() > 0, "Expected non-empty title");
    }

    @Then("I should click on the menu icon")
    public void iShouldClickOnTheMenuIcon() {
        homePage.clickBurgerMenuButton();
        ReportManager.getTest().info("Clicked on the menu icon.");
    }

    @And("I click on the Learning Materials link")
    public void iClickOnTheLearningMaterialsLink() {
        homePage.clickPractice();
        ReportManager.getTest().info("Clicked on the Learning Materials link.");
    }

    @Then("I click on Sign Up link")
    public void iClickOnSignUpLink() {
        homePage.clickOnSignUpButton();
        ReportManager.getTest().info("Clicked on the Sign Up link.");
    }

    @When("I verify the focus and keyboard input on the Sign Up page")
    public void iVerifyTheFocusAndKeyboardInputOnTheSignUpPage() {
        signUpPage.verifyFocusAndKeyboardFirstName();
        signUpPage.verifyFocusAndKeyboardLastName();
        signUpPage.verifyFocusAndKeyboardEmail();
        signUpPage.verifyFocusAndKeyboardPassword();
        signUpPage.verifyFocusAndKeyboardConfirmPassword();
    }

    @Then("I Check password visibility toggle")
    public void iCheckPasswordVisibilityToggle() {
        signUpPage.verifyPasswordFieldType();
    }

    @And("I Validate the Create Account button functionality")
    public void iValidateTheCreateAccountButtonFunctionality() {
        signUpPage.validateSubmitButtonFunctionality();
    }
    @Then("I Test form validation messages {string}")
    public void iTestFormValidationMessages(String collectionName) {
        String collectionPath = "postman/" + collectionName + ".json";
        String environmentPath = "postman/NdosiTestimonialsEnv.postman_environment.json";
        PostmanRunner.runPostmanCollection(collectionPath, environmentPath);
    }



}
