package pages.Android;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected static final Duration DEFAULT_WAIT = Duration.ofSeconds(20);

    protected final AppiumDriver driver;
    protected final WebDriverWait wait;

    protected BasePage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_WAIT);
    }

    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected WebElement waitForClickable(WebElement locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected WebElement waitForVisibility(WebElement locator) {
        return wait.until(ExpectedConditions.visibilityOf(locator));
    }

    protected void clickWhenClickable(WebElement locator) {
        waitForClickable(locator).click();
    }

    protected void typeText(WebElement locator, String text) {
        waitForClickable(locator).sendKeys(text);
    }
    
}
