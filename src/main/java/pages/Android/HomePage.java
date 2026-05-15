package pages.Android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
    public HomePage(AppiumDriver driver) {
        super(driver);
         PageFactory.initElements(
                new AppiumFieldDecorator(driver),
                this
        );
    }


     @AndroidFindBy(
            xpath =  "(//android.widget.FrameLayout[@resource-id=\"com.flipkart.android:id/main_content\"])[1]"
          + "/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]"
          + "/android.view.ViewGroup[3]/android.view.ViewGroup/android.view.ViewGroup[1]"
          + "/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup"
          + "//android.view.ViewGroup//android.widget.TextView"
    )
    private WebElement SEARCH_BAR;

    public Boolean checkForSearchBarVisibility() {
        return SEARCH_BAR.isDisplayed();
    }

    public SearchPage openSearch() {
        SEARCH_BAR.click();
        return new SearchPage(driver);
    }
}
