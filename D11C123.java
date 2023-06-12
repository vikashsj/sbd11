package com.selenium.Cse1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class D11C123 {
	static ExtentTest test; // run tests
	static ExtentReports extent; // provides data to html file
	ExtentHtmlReporter htmlReporter; // generate reports in html with config
	@BeforeClass
	public void startreport()
	{
		htmlReporter =  new ExtentHtmlReporter("D:\\d10ce14.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		//configuration items to change the look and feel
        //add content, manage tests etc
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("Simple Automation Report");
        htmlReporter.config().setReportName("Test Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);
	}
	
	@Test
	  public void f() {
			  	WebDriverManager.chromedriver().setup();
				WebDriver  driver;
				ChromeOptions co = new ChromeOptions();
				co.addArguments("--remote-allow-origins=*");
				driver=new ChromeDriver(co);
				driver.get("https://www.godaddy.com/en-in");
				driver.manage().window().maximize();
				String actualTitle="Domain Names, Websites, Hosting & Online Marketing Tools - GoDaddy IN";
				String extractedTitle=driver.getTitle();
				Assert.assertEquals(actualTitle, extractedTitle);
				String actualURL="https://www.godaddy.com/en-in";
				String extractedURL=driver.getCurrentUrl();
				Assert.assertEquals(actualURL, extractedURL);//System.out.println("Smoke test");
				driver.quit();
				//create a test report data reg the test
				test = extent.createTest("Test Case 1", "The test case 1 has passed");		
	  }
		@Test
		  public void f1() throws Exception {
			  WebDriverManager.chromedriver().setup();
				WebDriver  driver;
				ChromeOptions co = new ChromeOptions();
				co.addArguments("--remote-allow-origins=*");
				driver=new ChromeDriver(co);
				driver.get("https://www.godaddy.com/en-in");
				driver.manage().window().maximize();
				driver.navigate().refresh();
				Thread.sleep(3000);
				driver.findElement(By.xpath("//*[@id=\"id-e6aaf13d-272e-44df-903a-e07e539d57c7\"]/span[1]")).click();
				//driver.findElement(By.linkText("Domain Name Search")).click();
				System.out.println(driver.getTitle());//System.out.println("Regressiontest");
				driver.quit();
				//create a test report data reg the test
				test = extent.createTest("Test Case 2", "The test case 2 has passed");
		  }
		
		 @AfterMethod
		 //getstatus returns status (pass or fail) of the test
		  public void getResult(ITestResult result) {
			  if(result.getStatus() == ITestResult.FAILURE) {
		          test.log(Status.FAIL,result.getThrowable());
		      }
		      else if(result.getStatus() == ITestResult.SUCCESS) {
		          test.log(Status.PASS, result.getTestName());
		      }
		      else {
		          test.log(Status.SKIP, result.getTestName());
		      }
		  }
		  @AfterTest
		  public void tearDown() {
		      //to write or update test information to reporter
		      extent.flush();
		  }
}
