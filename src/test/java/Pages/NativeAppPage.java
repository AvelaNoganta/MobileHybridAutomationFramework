package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NativeAppPage {


    @FindBy(xpath = "//android.widget.TextView[@content-desc='Animation']")
    private WebElement animationOption;
    @FindBy(xpath = "//android.widget.TextView[@content-desc='Bouncing Balls']")
    private WebElement bouncingBallsOption;
    @FindBy(xpath = "//android.widget.TextView[@content-desc='Events']")
    private WebElement eventsOption;


    public NativeAppPage(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    public void clickOnAnimationOption() {
        animationOption.click();
    }
    public void animationPageIsDisplayed() {
        bouncingBallsOption.isDisplayed();
    }
    public void clickOnEventsOption() {
        eventsOption.click();
    }
}
