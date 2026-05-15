package pages.Android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends BasePage {
    public CartPage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(
            new AppiumFieldDecorator(driver),
            this
        );
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Skip All\"]")
    private WebElement SKIP_ALL;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Place Order \"]")
    private WebElement PLACE_ORDER;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Continue \"]")
    private WebElement CONTINUE_BTN;


    public WebElement getSkipAll() {
        return waitForClickable(SKIP_ALL);
    }

    public void clickSkipAll() {
        getSkipAll().click();
    }

    public WebElement getPlaceOrder() {
        return waitForClickable(PLACE_ORDER);
    }

    public void clickPlaceOrder() {
        getPlaceOrder().click();
    }

    public WebElement getContinueButton() {
        return waitForClickable(CONTINUE_BTN);
    }

    public void clickContinue() {
        getContinueButton().click();
    }
}
