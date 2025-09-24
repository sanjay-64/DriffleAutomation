package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupPage {

    WebDriver driver;
    WebDriverWait wait;

    // Locators
    private By signupOnHomePage = By.xpath("//button[@aria-label='sign up']");
    private By selectSignupOption = By.xpath("(//button[contains(@class,'sc-34567cf4-0')]//div/span)[4]");
    private By emailInput = By.id("user-register-email-input");
    private By passwordInput = By.xpath("//input[contains(@class,'sc-c0709e85-3') and (@placeholder='Enter password')]");
    private By confirmPasswordInput = By.xpath("//input[contains(@class,'sc-c0709e85-3') and (@placeholder='Re-enter password')]");
    private By submitButton = By.xpath("//button[@aria-label='register']");
    private By accountConfirmText = By.xpath("//div[contains(@class,'sc-302df0f-1') and (text()='Verify your email')]");
    private By alreadyCreatedText = By.xpath("//*[text()='Email already registered.']");

    public SignupPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Page Actions
    public void clickSignupOnHomePage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(signupOnHomePage)).click();
    }

    public void selectSignupOption() {
        wait.until(ExpectedConditions.elementToBeClickable(selectSignupOption)).click();
    }

    public void enterEmail(String email) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passField = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        passField.clear();
        passField.sendKeys(password);
    }

    public void enterConfirmPassword(String password) {
        WebElement confirmPassField = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordInput));
        confirmPassField.clear();
        confirmPassField.sendKeys(password);
    }

    public void clickSubmit() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    /**
     * Method to get signup result message based on account creation
     * @return String message either "Verify your email" or "Email already registered."
     */
    public String getSignupResultMessage() {
        try {
            // Try to get "Verify your email" message
            WebElement confirmText = wait.until(ExpectedConditions.visibilityOfElementLocated(accountConfirmText));
            return confirmText.getText().trim();
        } catch (Exception e) {
            // If not visible, check "Email already registered."
            try {
                WebElement alreadyText = wait.until(ExpectedConditions.visibilityOfElementLocated(alreadyCreatedText));
                return alreadyText.getText().trim();
            } catch (Exception ex) {
                return "Unknown signup status!";
            }
        }
    }
}
