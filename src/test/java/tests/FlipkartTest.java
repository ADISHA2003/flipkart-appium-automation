package tests;

import pages.Android.CartPage;
import pages.Android.HomePage;
import pages.Android.ProductPage;
import pages.Android.SearchPage;
import pages.Android.SearchResultsPage;

import org.testng.Assert;
import org.testng.annotations.Test;

import utils.TestDataReader;

public class FlipkartTest extends BaseTest {

    @Test
    public void placeOrderFlow() {
        String searchTerm = TestDataReader.get("searchQuery");
        String suggestionContentText = TestDataReader.get("searchSuggestionText");


        // 1. Search bar on homepage
        HomePage homePage = new HomePage(driver);        
        Assert.assertTrue(homePage.checkForSearchBarVisibility(),
                "Step 1 FAILED: Search bar is not displayed on the homepage.");
        SearchPage searchPage = homePage.openSearch();

        // 2. Search input on search page: sendKeys > iphone17
        Assert.assertTrue(searchPage.getSearchInput().isEnabled(),
                "Step 2 FAILED: Search input field is not enabled.");
        searchPage.enterSearchText(searchTerm);
        Assert.assertEquals(searchPage.getSearchInput().getText(), searchTerm,
                "Step 2 FAILED: Search input text does not match '" + searchTerm + "'.");

        // 3. First suggestion
        Assert.assertTrue(searchPage.getSuggestion(suggestionContentText).isDisplayed(),
                "Step 3 FAILED: First suggestion is not displayed.");
        SearchResultsPage resultsPage = searchPage.selectSuggestion(suggestionContentText);

        // 4. First result on search results page
        Assert.assertTrue(resultsPage.getFirstResult().isDisplayed(),
                "Step 4 FAILED: First product result is not displayed.");
        ProductPage productPage = resultsPage.openFirstResult();

        // 5. Cart icon on product page
        Assert.assertTrue(productPage.getCartIcon().isDisplayed(),
                "Step 5 FAILED: Cart icon is not displayed on the product page.");
        CartPage cartPage = productPage.openCart();

        // 6. Skip All
        Assert.assertTrue(cartPage.getSkipAll().isDisplayed(),
                "Step 6 FAILED: 'Skip All' button is not displayed.");
        cartPage.clickSkipAll();

        // 7. Place Order
        Assert.assertTrue(cartPage.getPlaceOrder().isDisplayed(),
                "Step 7 FAILED: 'Place Order' button is not displayed.");
        cartPage.clickPlaceOrder();

        // 8. Continue
        Assert.assertTrue(cartPage.getContinueButton().isDisplayed(),
                "Step 8 FAILED: 'Continue' button is not displayed.");
        cartPage.clickContinue();

        System.out.println("Automation flow completed successfully. All assertions passed.");
    }
}
