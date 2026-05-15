package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;


public class DriverManager {

    private static final String APPIUM_SERVER_URL;
    private static final String PLATFORM_NAME;
    private static final String AUTOMATION_NAME;
    private static final String DEVICE_NAME;
    private static final String UDID;
    private static final String APP_PACKAGE;
    private static final String APP_ACTIVITY;

    static {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = DriverManager.class.getClassLoader().getResourceAsStream("config.json")) {
            if (is == null) {
                throw new RuntimeException("config.json not found in resources folder");
            }
            JsonNode config = mapper.readTree(is);
            APPIUM_SERVER_URL = config.get("appiumServerUrl").asText();
            PLATFORM_NAME = config.get("platformName").asText();
            AUTOMATION_NAME = config.get("automationName").asText();
            DEVICE_NAME = config.get("deviceName").asText();
            UDID = config.get("udid").asText();
            APP_PACKAGE = config.get("appPackage").asText();
            APP_ACTIVITY = config.get("appActivity").asText();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration from config.json", e);
        }
    }

   protected AppiumDriver driver;

    public AppiumDriver initDriver(String platformName) throws MalformedURLException {

       switch (platformName.toLowerCase()) {
        case "an":
            return getAndroidDriver();
        case "ios":
            return getIosDriver();
        default:
            return driver;
       }
      // Should not be reached if default throws exception
    }

    public AppiumDriver getAndroidDriver() {
         UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName(PLATFORM_NAME);
        options.setAutomationName(AUTOMATION_NAME);
        options.setDeviceName(DEVICE_NAME);
        options.setUdid(UDID);
        options.setAppPackage(APP_PACKAGE);
        options.setAppActivity(APP_ACTIVITY);
        options.setNoReset(true);
        // Force-launch the configured activity each session so a leftover screen
        // from a prior run doesn't hide the homepage.
        options.setCapability("appium:forceAppLaunch", true);
        try{
        driver = new AndroidDriver(new URL(APPIUM_SERVER_URL), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        catch(MalformedURLException me){

        }
        return driver;

    }

    public AppiumDriver getIosDriver(){
        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
