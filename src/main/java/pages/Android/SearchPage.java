package pages.Android;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SearchPage extends BasePage {
    public SearchPage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(
                new AppiumFieldDecorator(driver),
                this
        );
    };

    @AndroidFindBy(
            xpath = "//android.widget.FrameLayout[@resource-id=\"com.flipkart.android:id/main_content\"]"
          + "/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]"
          + "/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[1]"
          + "//android.widget.EditText"
    )

    private WebElement SEARCH_INPUT;

    public WebElement getSearchInput() {
        return waitForClickable(SEARCH_INPUT);
    }

    public void enterSearchText(String text) {
        getSearchInput().sendKeys(text);
    }

    public WebElement getSuggestion(String contentDesc) {
        By suggestion = By.xpath("//android.view.ViewGroup[@content-desc=\"" + contentDesc + "\"]");
        return waitForClickable(suggestion);
    }

    public SearchResultsPage selectSuggestion(String contentDesc) {
        getSuggestion(contentDesc).click();
        return new SearchResultsPage(driver);
    }
}
