package pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {

    WebDriver driver;
    WebDriverWait wait;

    private By searchBox = By.id("web-search-input");
    private By product = By.xpath("//span[contains(text(),'Spotify Premium')]");
    private By buyNowButton = By.xpath("(//button[@aria-label='buy now'])[2]");
    private By checkoutButton = By.xpath("//button[@aria-label='continue']");
    private By emailCheckoutPage = By.xpath("//input[@placeholder='Enter email address']");
    private By continuePayment = By.xpath("//button[@aria-label='button' and @type='submit']");
    private By paymentMethodUPI = By.xpath("//p[text()='UPI']");
    private By firstNameInput = By.id("first-name");
    private By lastNameInput = By.id("last-name");
    private By mobileNumberInput = By.id("phone-number");
    private By postalCodeInput = By.id("postal-code");
    private By cityInput = By.id("city");
    private By stateInput = By.id("state");
    private By checkBox = By.xpath("//input[@type='checkbox']");
    private By payWithUPI = By.id("submit-button");

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private void clickElement(By locator, String elementName) {
        for (int i = 0; i < 3; i++) {
            try {
                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element);
                new Actions(driver).moveToElement(element).click().perform();
                System.out.println("Clicked on " + elementName);
                return;
            } catch (Exception e) {
                System.out.println("Attempt " + (i + 1) + " to click " + elementName + " failed.");
                if (i == 2) {
                    WebElement element = driver.findElement(locator);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                    System.out.println("Clicked on " + elementName + " using JS fallback.");
                }
                try { Thread.sleep(500); } catch (InterruptedException ignored) {}
            }
        }
    }

    private void enterText(By locator, String text, String fieldName) {
        try {
            WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            input.clear();
            input.sendKeys(text);
            System.out.println("Entered '" + text + "' into " + fieldName);
        } catch (Exception e) {
            System.out.println("Failed to enter text in " + fieldName);
            throw e;
        }
    }

    public void enterProductName(String productName) {
        enterText(searchBox, productName, "Search Box");
        driver.findElement(searchBox).sendKeys(Keys.ENTER);
    }

    public void clickProduct() { 
    	clickElement(product, "Product"); 
    	
    }

    public void clickBuyNow() { 
        clickElement(buyNowButton, "Buy Now button"); 
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutButton));
    }

    public void clickCheckoutButton() { 
    	clickElement(checkoutButton, "Checkout button"); 
    	
    }

    public void enterEmailOnCheckoutPage(String email) {
        enterText(emailCheckoutPage, email, "Checkout Email");
    }

    public void clickOnContinuePayment() { 
    	clickElement(continuePayment, "Continue Payment button"); 
    	
    }

    public void selectPaymentMethodUPI() { 
    	clickElement(paymentMethodUPI, "Payment option as UPI"); 
    	
    }

    public void enterFirstName(String firstName) { 
    	enterText(firstNameInput, firstName, "First Name"); 
    	
    }

    public void enterLastName(String lastName) { 
    	enterText(lastNameInput, lastName, "Last Name"); 
    	
    }

    public void enterMobileNumber(String mobileNumber) { 
    	enterText(mobileNumberInput, mobileNumber, "Mobile Number"); 
    	
    }

    public void enterPostalCode(String postalCode) { 
    	enterText(postalCodeInput, postalCode, "Postal Code"); 
    	
    }

    public void enterCity(String city) { 
    	enterText(cityInput, city, "City"); 
    	
    }

    public void enterState(String state) { 
    	enterText(stateInput, state, "State"); 
    	
    }

    public void clickRememberMeCheckbox() { 
    	clickElement(checkBox, "Remember Me checkbox"); 
    	
    }

    public void clickPayWithUPI() { 
    	clickElement(payWithUPI, "Pay with UPI button"); 
    	
    }
}
