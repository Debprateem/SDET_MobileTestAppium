package tests;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class chromeTabOpener {
	WebDriverWait wait;
    AppiumDriver<MobileElement> driver = null;
 
    @BeforeClass
    public void beforeClass() throws MalformedURLException {
        // Set the Desired Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceId", "d85fff2");
	      caps.setCapability("platformName", "Android");
	      caps.setCapability("appPackage", "com.android.chrome");
	      caps.setCapability("appActivity", "com.google.android.apps.chrome.Main");
	      caps.setCapability("noReset", true);
 
        // Instantiate Appium Driver
        URL appServer = new URL("http://0.0.0.0:4723/wd/hub");
        driver = new AndroidDriver<MobileElement>(appServer, caps);
        wait = new WebDriverWait(driver, 10);
 
        // Open page
        driver.get("https://www.training-support.net/selenium");
    }
 
    @Test
    public void scrollIntoViewTest() {
    	System.out.println("running scrollIntoView");
    	
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View")));
 
       
        driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector().scrollable(true).instance(0)).scrollIntoView(text(\"Tab Opener\"))")).click();
        
        String pageTitle = driver.findElementByClassName("android.widget.TextView").getText();
        System.out.println("Page title is: " + pageTitle);
 
        driver.navigate().back();
    }
 
    @Test
    public void getChildTest() {
    	System.out.println("running getChild");
        
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View")));
        driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector().scrollable(true).instance(0)).getChildByText(UiSelector().className(\"android.widget.TextView\"), \"Tab Opener\")")).click();
        
        String pageTitle = driver.findElementByClassName("android.widget.TextView").getText();
        System.out.println("Page title is: " + pageTitle);
 
        driver.navigate().back();
    }
 
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
