package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.SignupPage;

public class SignupTest extends BaseTest {

    @DataProvider(name = "signupData")
    public Object[][] signupData() {
        return new Object[][] {
            {"sanjaykumar536601@gmail.com", "sanjay001@"},
            {"testuser1@gmail.com", "Test@1234"},
            {"testuser2@gmail.com", "Test@5678"}
        };
    }

    @Test(dataProvider = "signupData", priority = 1, groups = {"smoke", "sanity", "regression"}, enabled = true, invocationCount=1)
    public void testSignup(String email, String password) {
        driver.get("https://driffle.com/");
        SignupPage signupPage = new SignupPage(driver);

        signupPage.clickSignupOnHomePage();
        signupPage.selectSignupOption();
        signupPage.enterEmail(email);
        signupPage.enterPassword(password);
        signupPage.enterConfirmPassword(password);
        signupPage.clickSubmit();

        String resultMessage = signupPage.getSignupResultMessage();
        System.out.println("Signup result for " + email + ": " + resultMessage);

        Assert.assertTrue(resultMessage.equals("Verify your email") || resultMessage.equals("Email already registered."),
                "Error: Signup flow did not return expected result for " + email);
    }
}
