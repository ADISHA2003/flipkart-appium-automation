package pages.Android;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductPage extends BasePage {
    public ProductPage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(
                new AppiumFieldDecorator(driver),
                this
        );
    }

    @AndroidFindBy(
            xpath = "//android.widget.FrameLayout[@resource-id=\"com.flipkart.android:id/main_content\"]"
          + "/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]"
          + "/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup"
          + "/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup"
          + "/com.horcrux.svg.SvgView"
    )

    private WebElement CART_ICON;

    public WebElement getCartIcon() {
        return waitForClickable(CART_ICON);
    }

    public CartPage openCart() {
        getCartIcon().click();
        return new CartPage(driver);
    }
}
