package tests;

import org.testng.annotations.Test;
import base.BaseTest;
import pages.SearchPage;

public class SearchBoxTest extends BaseTest {

    @Test(priority = 1, groups = {"smoke", "sanity", "regression"})
    public void testSearchSpotifyProduct() {
        driver.get("https://driffle.com/");
        SearchPage searchPage = new SearchPage(driver);

        searchPage.enterProductName("Spotify");
        searchPage.clickProduct();
        searchPage.clickBuyNow();
        searchPage.clickCheckoutButton();
        searchPage.enterEmailOnCheckoutPage("test@test.com");
        searchPage.clickOnContinuePayment();
        searchPage.selectPaymentMethodUPI();
        searchPage.enterFirstName("Sanjay");
        searchPage.enterLastName("Kumar");
        searchPage.enterMobileNumber("9876543210");
        searchPage.enterPostalCode("110001");
        searchPage.enterCity("Delhi");
        searchPage.enterState("Delhi");
        searchPage.clickRememberMeCheckbox();
        searchPage.clickPayWithUPI();
    }
}
