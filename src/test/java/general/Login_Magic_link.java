
package general;

import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.ByteArrayInputStream;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.codeborne.selenide.testng.TextReport;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.qameta.allure.Allure;
import util.send_request;

public class Login_Magic_link

{

	public TextReport report = new TextReport();

	static DesiredCapabilities cap = new DesiredCapabilities();

	public static AndroidDriver<MobileElement> driver;

	public static void Home() throws Exception

	{

		try

		{

			// Confirm that you are on Home screen
			WebDriverWait wait = new WebDriverWait(driver, 30);
			// Wait to load the screen For Home
			WebElement div = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Home']")));
			// Print that You are on Home screen
			div.click();

		} catch (Exception e)

		{

			System.out.println(e);
			Allure.addAttachment("AllureSelenide",
					new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

		}
	}

	public static void login() throws Exception

	{
		try

		{

			WebDriverWait wait = new WebDriverWait(driver, 100);

			// Enter Your PIN
			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//android.widget.TextView[@text='Enter your new PIN']")));

			// Click on 1
			driver.findElement(By.xpath("//android.widget.TextView[@text='1']")).click();

			// Click on 2
			driver.findElement(By.xpath("//android.widget.TextView[@text='2']")).click();

			// Click on 3
			driver.findElement(By.xpath("//android.widget.TextView[@text='3']")).click();

			// Click on 4
			driver.findElement(By.xpath("//android.widget.TextView[@text='4']")).click();

			// Click on 5
			driver.findElement(By.xpath("//android.widget.TextView[@text='5']")).click();

			// WebDriverWait wait2 = new WebDriverWait(driver, 20);

			WebElement div2 = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//android.widget.TextView[@text='Confirm your new PIN']")));

			// Click on 1
			driver.findElement(By.xpath("//android.widget.TextView[@text='1']")).click();

			// Click on 2
			driver.findElement(By.xpath("//android.widget.TextView[@text='2']")).click();

			// Click on 3
			driver.findElement(By.xpath("//android.widget.TextView[@text='3']")).click();

			// Click on 4
			driver.findElement(By.xpath("//android.widget.TextView[@text='4']")).click();

			// Click on 5
			driver.findElement(By.xpath("//android.widget.TextView[@text='5']")).click();

			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='PIN Set Up Successful']")));

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[@text='Skip']")))
					.click();

			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("(//*[@resource-id='com.impossible_research.sandbox.starboard:id/head']) [1]")));
			driver.findElement(By.xpath("(//*[@resource-id='com.impossible_research.sandbox.starboard:id/head']) [1]"))
					.getText();
			driver.findElement(By.xpath("(//*[@resource-id='com.impossible_research.sandbox.starboard:id/head']) [1]"))
					.click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[@text='Allow']")))
					.click();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[@text='Home']")));

		}

		catch (Exception e1)

		{

			System.out.println(e1);
			Allure.addAttachment("AllureSelenide",
					new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

		}

	}

	@BeforeSuite
	public void LoginTOApplication() throws Exception {
		try

		{

			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
			cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
			cap.setCapability(MobileCapabilityType.NO_RESET, true); // It will always clear the caches
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
			// cap.setCapability(MobileCapabilityType.DEVICE_NAME,"R9ZRA09AK2M");
			cap.setCapability("ignoreHiddenApiPolicyError", true);
			cap.setCapability("pageLoadStrategy", "none");
			cap.setCapability("chromedriverExecutable",
					"/Users/runner/work/starboard-android/starboard-android/chromedriver-linux64/chromedriver");
			cap.setCapability("app", "/Users/runner/work/starboard-android/starboard-android/apk/Starboard-develop-dev.apk");
			cap.setCapability("appPackage", "com.impossible_research.sandbox.starboard"); // Starboard package name
			cap.setCapability("appActivity", "com.impossible_research.sandbox.starboard.ui.splash.view.SplashActivity"); // Starboard login activity

			driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub/"), cap);

			String magic_link = new send_request().sendRequest();
			
			
			driver.navigate().to(magic_link);
			System.out.println(" clicked on magic link");

		}

		
		catch (Exception e2)

		{

			System.out.println(e2);
			String pageSource = driver.getPageSource();
			System.out.println(pageSource);
			Allure.addAttachment("AllureSelenide",
					new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

		}

		login();

	}

	@AfterSuite
	public void TearDown() throws Exception

	{
		Runtime runtime = Runtime.getRuntime();
        // Total memory in bytes
        long totalMemory = runtime.totalMemory();
        // Free memory in bytes
        long freeMemory = runtime.freeMemory();
        // Maximum memory that the JVM can use in bytes
        long maxMemory = runtime.maxMemory();
        // Print memory information
        System.out.println("Total Memory: " + totalMemory + " bytes");
        System.out.println("Free Memory: " + freeMemory + " bytes");
        System.out.println("Max Memory: " + maxMemory + " bytes");
        
		// driver.terminateApp("com.google.android.gm");

		//Reporter.log("==========Gmail Application closed==========", true);

		// driver.terminateApp("com.impossible_research.sandbox.starboard");

		Reporter.log("==========Starboard Application closed==========", true);

	}

}
