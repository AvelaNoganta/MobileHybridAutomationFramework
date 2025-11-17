// src/test/java/com/group3/webassessment/pages/HomePage.java
package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private final WebDriverWait wait;

    @FindBy(id = "nav-mobile-btn-practice")
    private WebElement practiceButton;

    @FindBy(xpath = "//button[@id='nav-burger']")
    private WebElement burgerMenuButton;

    @FindBy(id = "signup-toggle")
    private WebElement signupButton;

    public HomePage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    public void clickPractice() {
        wait.until(ExpectedConditions.elementToBeClickable(practiceButton)).click();
    }
    public void clickBurgerMenuButton() {
        wait.until(ExpectedConditions.elementToBeClickable(burgerMenuButton)).click();
    }
    public void clickOnSignUpButton() {
        wait.until(ExpectedConditions.elementToBeClickable(signupButton)).click();
    }
}
