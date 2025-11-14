package stepdefinitions;

import Pages.HomePage;
import Pages.LoginPage;
import base.DriverFactory;
import base.ReportManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoginWebSteps {

    private WebDriver driver;
    private Properties config;

    HomePage homePage = new HomePage(DriverFactory.getDriver());
    LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

    public LoginWebSteps() {
        driver = DriverFactory.getDriver();
        config = new Properties();
        try {
            config.load(new FileInputStream("src/test/resources/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Given("I launch the mobile web browser login")
    public void i_launch_the_mobile_web_browser() {
        ReportManager.getTest().info("✅ Mobile Chrome browser launched.");
    }
    @When("I navigate to the Ndosi Automation homepage login")
    public void i_navigate_to_homepage() {
        String url = config.getProperty("webUrl");
        ReportManager.getTest().info("Navigating to: " + url);
        driver.get(url);
    }
    @Then("I should click on the menu icon login")
    public void iShouldClickOnTheMenuIcon() {
        homePage.clickBurgerMenuButton();
        ReportManager.getTest().info("Clicked on the menu icon.");
    }

    @And("I click on the Learning Materials link login")
    public void iClickOnTheLearningMaterialsLink() {
        homePage.clickPractice();
        ReportManager.getTest().info("Clicked on the Learning Materials link.");
    }

    @Then("I test the email and password field with input type {string} and email {string} and {string} and validate Alert popup")
    public void iTestTheEmailAndPasswordFieldWithInputTypeAndEmailAndAndValidateAlertPopup(String input, String emailValue, String passwordValue) throws InterruptedException {
        loginPage.verifyEmailAndPasswordFields(input,emailValue,passwordValue);
    }

}
