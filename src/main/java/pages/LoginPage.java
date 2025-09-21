package pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    private By loginModalOpenButton  = By.xpath("//button[contains(@class,'sc-8f33a2bf-1') and contains(text(),'Login')]");
    private By emailInputField = By.xpath("//input[@placeholder='Enter email']");
    private By passwordInputField = By.xpath("//input[@placeholder='Enter password']");
    private By loginSubmitButton  = By.xpath("(//button[contains(@class, 'sc-8f33a2bf-1') and contains(text(), 'Login')])[1]");

    // captcha overlay locator (jo block kar raha hai)
    private By captchaOverlay = By.xpath("//div[contains(@class,'MuiDialog-container')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void clickLoginField() {
        wait.until(ExpectedConditions.elementToBeClickable(loginModalOpenButton)).click();
    }

    public void enterUserName(String username) {
        WebElement userEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInputField));
        userEmail.clear();
        userEmail.sendKeys(username);
    }

    public void enterUserPassword(String userpassword) {
        WebElement userPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInputField));
        userPassword.clear();
        userPassword.sendKeys(userpassword);
    }

    public void clickSubmit() {
        // Wait until captcha overlay disappears
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(captchaOverlay));
        } catch (TimeoutException e) {
            System.out.println("⚠️ Captcha overlay still visible, proceeding anyway...");
        }

        // Wait for button to be enabled and clickable
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(loginSubmitButton));
        
        // Extra safety: scroll into view (kabhi kabhi click block hota hai)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
        
        // Final click using JS (agar normal click fail kare)
        try {
            submitButton.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
        }
    }

    public boolean isLoginSuccessful() {
        return driver.getCurrentUrl().contains("dashboard");
    }

    public void login(String email, String password) {
        clickLoginField();
        enterUserName(email);
        enterUserPassword(password);
        clickSubmit();
    }
}
