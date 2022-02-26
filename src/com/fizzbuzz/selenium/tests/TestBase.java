package com.fizzbuzz.selenium.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestBase {

	protected WebDriver driver;

	public TestBase() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\LStar\\Documents\\Selenium\\chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\LStar\\Documents\\Selenium\\geckodriver.exe");
		System.setProperty("webdriver.edge.driver", "C:\\Users\\LStar\\Documents\\Selenium\\msedgedriver.exe");
	}

	public final WebDriver initializeBrowser(String browserName) {

		if (browserName.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
		}

		return driver;
	}
}
