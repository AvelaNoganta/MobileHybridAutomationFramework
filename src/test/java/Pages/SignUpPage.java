package Pages;

import base.ReportManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class SignUpPage {
    private WebDriver driver ;

    @FindBy(id = "register-firstName")
    private WebElement firstName;

    @FindBy(id = "register-lastName")
    private WebElement lastName;

    @FindBy(id = "register-email")
    private WebElement email;

    @FindBy(id = "register-password")
    private WebElement password;

    @FindBy(id = "register-confirmPassword")
    private WebElement confirmPassword;

    @FindBy(id = "register-submit")
    private WebElement submit;

    JavascriptExecutor js = (JavascriptExecutor) driver;
    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }


    private void verifyFocusAndKeyboard(WebElement element, String fieldName) {
        element.click();

        Boolean isFocused = (Boolean) ((JavascriptExecutor) driver).executeScript("return document.activeElement === arguments[0];", element);
        Assert.assertTrue(isFocused, fieldName + " field did not receive focus!");

        Boolean keyboardShown = (Boolean) ((JavascriptExecutor) driver).executeScript(
                "return (typeof window.visualViewport !== 'undefined') && (window.visualViewport.height < window.innerHeight);"
        );
        if (keyboardShown) {
            ReportManager.getTest().info("Mobile keyboard appeared after tapping " + fieldName + " field.");
        } else {
            ReportManager.getTest().warning("Keyboard did not appear after tapping " + fieldName + " field (could be hidden in emulator).");
        }
    }
    public void verifyFocusAndKeyboardFirstName() {
        verifyFocusAndKeyboard(firstName, "FirstName");
    }

    public void verifyFocusAndKeyboardLastName() {
        verifyFocusAndKeyboard(lastName, "LastName");
    }

    public void verifyFocusAndKeyboardEmail() {
        verifyFocusAndKeyboard(email, "Email");
    }

    public void verifyFocusAndKeyboardPassword() {
        verifyFocusAndKeyboard(password, "Password");
    }
    public void verifyFocusAndKeyboardConfirmPassword() {
        verifyFocusAndKeyboard(confirmPassword, "ConfirmPassword");
    }

    //Verify Password Field type is 'password'
    public void verifyPasswordFieldType() {

        String initialType = password.getAttribute("type");
        Assert.assertEquals(initialType, "password", "Input type should initially be 'password'");
        ReportManager.getTest().info("Initial type verified as password.");
    }
    public void validateSubmitButtonFunctionality() {
        ReportManager.getTest().info("Testing Create Account button functionality...");

        Assert.assertTrue(submit.isDisplayed(), "Create Account button is not visible!");
        Assert.assertTrue(submit.isEnabled(), "Create Account button is disabled!");
        ReportManager.getTest().info("Create Account button is visible and enabled.");

        submit.click();
        ReportManager.getTest().info("Clicked the Create Account button.");
    }

}
