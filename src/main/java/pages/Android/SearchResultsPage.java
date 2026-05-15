package pages.Android;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SearchResultsPage extends BasePage {
    public SearchResultsPage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(
                new AppiumFieldDecorator(driver),
                this
        );
    }

    @AndroidFindBy(
            xpath = "//android.view.ViewGroup[contains(@content-desc,\"Apple iPhone 17 (White, 256 GB)\")]")

    private WebElement FIRST_RESULT;
    public WebElement getFirstResult() {
        return waitForClickable(FIRST_RESULT);
    }

    public ProductPage openFirstResult() {
        getFirstResult().click();
        return new ProductPage(driver);
    }
}
