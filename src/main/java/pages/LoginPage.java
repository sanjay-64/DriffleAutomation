package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage 
{
    WebDriver driver;
    WebDriverWait wait;
    
    
    private By loginModalOpenButton  = By.xpath("//button[contains(@class,'sc-8f33a2bf-1') and contains(text(),'Login')]");
    private By emailInputField = By.xpath("//input[@placeholder='Enter email']"); 
    private By passwordInputField = By.xpath("//input[@placeholder='Enter password']");
    private By loginSubmitButton  = By.xpath("(//button[contains(@class, 'sc-8f33a2bf-1') and contains(text(), 'Login')]\r\n"
    		+ ")[1]");
   
    
    
    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
        this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickLoginField()
    {
    	wait.until(ExpectedConditions.elementToBeClickable(loginModalOpenButton)).click();
    }
    
    public void enterUserName(String username) 
    {
    	WebElement userEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInputField));
    	userEmail.clear();
    	userEmail.sendKeys(username);
    }

    public void enterUserPassword(String userpassword)
    {
    	WebElement userPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInputField));
    	userPassword.clear();
    	userPassword.sendKeys(userpassword);
    }

    public void clickSubmit()
    {
    	WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(loginSubmitButton ));
    	submitButton.click();
    }

    public boolean isLoginSuccessful() 
    {
        return driver.getCurrentUrl().contains("dashboard");
    }
    
    public void login(String email, String password )
    {
    	clickLoginField();
    	enterUserName(email);
    	enterUserPassword(password);
    	clickSubmit() ;
    	
    	
    }
}
