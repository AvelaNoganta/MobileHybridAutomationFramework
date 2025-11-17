package base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class DriverFactory {
    private static AndroidDriver driver;

    public static void initDriver(Properties config) throws MalformedURLException {
        if (driver != null) return;  //Prevent double initialization

        String execType = config.getProperty("executionType").trim();
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("emulator-5554")
                .setAutomationName("UiAutomator2")
                .setPlatformName("Android");

        if (execType.equalsIgnoreCase("mobileWeb")) {
            options.withBrowserName("Chrome");
            System.out.println("Launching Chrome browser...");
        } else if (execType.equalsIgnoreCase("nativeApp")) {
            String appPath = System.getProperty("user.dir") + "/" + config.getProperty("appPath");
            options.setApp(appPath);
            System.out.println("Launching native app: " + appPath);
        }

        driver = new AndroidDriver(new URL(config.getProperty("appiumServer")), options);
    }

    public static AndroidDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
