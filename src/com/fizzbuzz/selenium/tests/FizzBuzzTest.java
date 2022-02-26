package com.fizzbuzz.selenium.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class FizzBuzzTest {

	// WebDriver object
	protected WebDriver driver;

	// Import methods from base class
	protected TestBase base = new TestBase();

	// Page URL
	String fizzBuzzPageUrl = "TBD";

	// locator for the text input box
	By textInputBoxLocator = By.id("lteq30");

	// locator for submit button
	By submitButtonLocator = By.name("submitbutton");

	// locator for first row expected output text
	By firstRowOutputLocator = By.xpath("//table/tbody/tr[2]/td[2]");

	// locator for second row within first cell expected output text
	By secondRowOutputLocator = By.xpath("//tbody/tr[2]/td[2] //tbody/tr[2]");

	// int data array for table
	int[] integerTestDataArray = { 1, 3, 5, 15, 23 };

	// string data array for table
	String[] stringTestDataArray = { "A", " ", "bcd" };

	// Variable to store browser in use
	String browserParam;

	@BeforeTest
	@Parameters("browser")
	public void openBrowser(@Optional("chrome") String browser) {
		// Set which browser is currently is being used
		browserParam = browser;

		driver = base.initializeBrowser(browser);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		// log which browser the test is carried on for the test result
		Reporter.log("Now running the test in " + browserParam);
	}

	@Test
	public void testMultipleOf3() {

		Reporter.log("Testing the output for multiples of 3");

		driver.get(fizzBuzzPageUrl);

		WebElement textBox = driver.findElement(textInputBoxLocator);
		WebElement submitButton = driver.findElement(submitButtonLocator);

		for (int data : integerTestDataArray) {

			// convert data to string for selenium sendkeys
			String convertedText = String.valueOf(data);

			textBox.sendKeys(convertedText);
			submitButton.click();
			String resultTableText = driver.findElement(firstRowOutputLocator).getText();

			if (data % 3 == 0 && data % 5 != 0) {
				Assert.assertEquals(resultTableText, "Fizz");
			} else {
				Assert.assertNotEquals(resultTableText, "Fizz");
			}

		}

	}

	@Test
	public void testMultipleOf5() {
		Reporter.log("Testing the output for multiples of 5");

		driver.get(fizzBuzzPageUrl);

		WebElement textBox = driver.findElement(textInputBoxLocator);
		WebElement submitButton = driver.findElement(submitButtonLocator);

		for (int data : integerTestDataArray) {

			// convert data to string for selenium sendkeys
			String convertedText = String.valueOf(data);

			textBox.sendKeys(convertedText);
			submitButton.click();
			String resultTableText = driver.findElement(firstRowOutputLocator).getText();

			if (data % 5 == 0 && data % 3 != 0) {
				Assert.assertEquals(resultTableText, "Buzz");
			} else {
				Assert.assertNotEquals(resultTableText, "Buzz");
			}

		}
	}

	@Test
	public void testMultipleOfBoth3And5() {
		Reporter.log("Testing the output for multiples of 3 and 5");

		driver.get(fizzBuzzPageUrl);

		WebElement textBox = driver.findElement(textInputBoxLocator);
		WebElement submitButton = driver.findElement(submitButtonLocator);

		for (int data : integerTestDataArray) {

			// convert data to string for selenium sendkeys
			String convertedText = String.valueOf(data);

			textBox.sendKeys(convertedText);
			submitButton.click();
			String resultTableText = driver.findElement(firstRowOutputLocator).getText();

			if (data % 5 == 0 && data % 3 == 0) {
				Assert.assertEquals(resultTableText, "FizzBuzz");
			} else {
				Assert.assertNotEquals(resultTableText, "FizzBuzz");
			}

		}
	}

	@Test
	public void testNonNumericValue() {

		Reporter.log("Testing the output for Non Numeric Values");

		driver.get(fizzBuzzPageUrl);

		WebElement textBox = driver.findElement(textInputBoxLocator);
		WebElement submitButton = driver.findElement(submitButtonLocator);

		for (String data : stringTestDataArray) {

			textBox.sendKeys(data);
			submitButton.click();
			String resultTableText = driver.findElement(firstRowOutputLocator).getText();
			Assert.assertEquals(resultTableText, "Invalid Item");

		}
	}

	@Test
	public void testNonMultipleOf3And5() {

		Reporter.log("Testing the output for non multiples of 3 and 5");

		SoftAssert softAssert = new SoftAssert();

		driver.get(fizzBuzzPageUrl);

		WebElement textBox = driver.findElement(textInputBoxLocator);
		WebElement submitButton = driver.findElement(submitButtonLocator);

		for (int data : integerTestDataArray) {

			// convert data to string for selenium sendkeys
			String convertedText = String.valueOf(data);

			textBox.sendKeys(convertedText);
			submitButton.click();
			String firstOutputText = driver.findElement(firstRowOutputLocator).getText();
			String secondOutputText = driver.findElement(secondRowOutputLocator).getText();

			if (data % 5 != 0 && data % 3 != 0) {
				softAssert.assertEquals(firstOutputText, data / 3.0);
				softAssert.assertEquals(secondOutputText, data / 5.0);

			}

		}
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
