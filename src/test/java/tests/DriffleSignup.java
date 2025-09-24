package tests;



import java.time.Duration;



import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;

import org.testng.annotations.Test;



import base.BaseTest;



public class DriffleSignup extends BaseTest



{

	WebDriverWait wait;

	

	@Test

	public void testSignup()

	{

		driver.get("https://driffle.com/");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		

		WebElement signupOnHomePage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label='sign up']")));

		signupOnHomePage.click();

		

		WebElement selectSignupOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(@class,'sc-34567cf4-0')]//div/span)[4]")));

		selectSignupOption.click();

		

		WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-register-email-input")));

		emailInput.clear();

		emailInput.sendKeys("sanjaykumar536601@gmail.com");

		

		WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@class,'sc-c0709e85-3') and (@placeholder='Enter password')]")));

		passwordInput.clear();

		passwordInput.sendKeys("sanjay001@");

		

		WebElement confirmPasswordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@class,'sc-c0709e85-3') and (@placeholder='Re-enter password')]")));

		confirmPasswordInput.clear();

		confirmPasswordInput.sendKeys("sanjay001@");

		

		WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='register']")));

		submitButton.click();

		

		WebElement acountConfirmText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'sc-302df0f-1') and (text()='Verify your email')]")));

	    String actualtext = acountConfirmText.getText().trim();

	    System.out.println("form is submitted successfully!." + actualtext);

	    

	    Assert.assertEquals(actualtext, "Verify your email" ,"Error : SignUp form not submitted successfully!.");

	

	

	}



}

