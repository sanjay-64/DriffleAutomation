package tests;

import base.BaseTest;
import pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest 

{

    @DataProvider(name = "loginData")
    public Object[][] loginData() 
    {
        return new Object[][] 
        {
            {"sanjay@gmail.com", "sk@1234567"},
            {"sanjay1@gmail.com", "pass@123456"},
            {"userSanjay@gmail.com", "pass@word123"}
        };
    }

    @Test(dataProvider = "loginData", priority=1, enabled=true, groups= {"smoke","sanity"}, invocationCount=1)
    public void testLogin(String username, String password) 
    {
        driver.get("https://driffle.com/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        

        // Assert login success
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed for user: " + username);
    }
}
