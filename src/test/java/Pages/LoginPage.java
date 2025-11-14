package Pages;

import base.ReportManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "login-email")
    private WebElement email;
    @FindBy(id = "login-password")
    private WebElement passwordElement;
    @FindBy(id = "login-submit")
    private WebElement submit;

    Alert alert;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void verifyEmailAndPasswordFields(String inputType, String emailValue, String passwordValue) throws InterruptedException {

        //Enter email
        email.click();
        email.clear();
        email.sendKeys(emailValue);
        ReportManager.getTest().info("Entered (" + inputType + ") email value: " + emailValue);

        //Enter password
        passwordElement.click();
        passwordElement.clear();
        passwordElement.sendKeys(passwordValue);
        ReportManager.getTest().info("Entered (" + inputType + ") password value: " + passwordValue);

        //Verify password field masking behavior
        String fieldType = passwordElement.getAttribute("type");
        Assert.assertEquals(fieldType, "password", "Password field is not masked!");
        ReportManager.getTest().pass("Password field masks input correctly (type=password).");

        //Tap Login
        Thread.sleep(2000);
        submit.click();
        Thread.sleep(2000);
        ReportManager.getTest().info("Tapped Login button after entering " + inputType + " input.");

        //Validate alert message based on input type
        validateAlertMessage(inputType);
    }

    private void validateAlertMessage(String inputType) {
        String expectedAlert = switch (inputType.toLowerCase().trim()) {
            case "empty" -> "Please enter both email and password";
            case "invalid password" -> "Invalid email or password";
            case "invalid username" -> "Invalid email or password";
            case "valid username and password" -> ""; // No alert expected
            default -> throw new IllegalArgumentException("Unknown inputType: " + inputType);

        };

        try {
            //Try to handle browser alert first
            alert = driver.switchTo().alert();
            String alertText = alert.getText().trim();
            ReportManager.getTest().info("Captured alert text: " + alertText);

            if (!expectedAlert.isEmpty()) {
                Assert.assertEquals(alertText, expectedAlert,
                        "Alert text mismatch for " + inputType + " input.");
                ReportManager.getTest().pass("Correct alert shown for " + inputType + ": " + alertText);
            } else {
                ReportManager.getTest().fail("Unexpected alert for valid credentials: " + alertText);
                Assert.fail("Unexpected alert appeared for valid credentials: " + alertText);
            }

            alert.accept();

        } catch (NoAlertPresentException e) {
            try {
                //Fallback to inline HTML message
                if (!expectedAlert.isEmpty()) {
                    WebElement inlineMsg = driver.findElement(
                            By.xpath("//*[contains(text(),'" + expectedAlert + "')]"));
                    Assert.assertTrue(inlineMsg.isDisplayed(),
                            "Expected inline message not visible for " + inputType + " input.");
                    ReportManager.getTest().pass("Inline message displayed for " + inputType + ": " + expectedAlert);
                } else {
                    // Valid case: no alert or inline expected
                    ReportManager.getTest().info("No alert displayed for valid credentials — expected behavior.");
                }

            } catch (NoSuchElementException ex) {
                if (expectedAlert.isEmpty()) {
                    ReportManager.getTest().pass("No alert found for valid credentials (expected).");
                } else {
                    ReportManager.getTest().fail("No alert or inline message found for " + inputType + " input.");
                    Assert.fail("Expected message not found for " + inputType + " input.");
                }
            }
        }
    }


}
