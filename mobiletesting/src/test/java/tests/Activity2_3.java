package tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.LocksDevice;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.Assert;

public class Activity2_3 {
    AppiumDriver<MobileElement> driver = null;
    WebDriverWait wait;

    @BeforeClass
    public void beforeClass() throws MalformedURLException {
        // Set the Desired Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceId", "d85fff2");
        caps.setCapability("platformName", "Android");
        caps.setCapability("appPackage", "com.android.contacts");
        caps.setCapability("appActivity", ".activities.PeopleActivity");
		/*
		 * caps.setCapability("unlockType", "pin"); caps.setCapability("unlockKey",
		 * "1112");
		 */
        //caps.setCapability("noReset", true);

        // Instantiate Appium Driver
        URL appServer = new URL("http://0.0.0.0:4723/wd/hub");
        driver = new AndroidDriver<MobileElement>(appServer, caps);
        wait = new WebDriverWait(driver, 5);
        
        
    }

    @Test
    public void addContact() throws Exception {
    	
		
		/*
		 * if (((AndroidDriver) driver).isDeviceLocked()) ((AndroidDriver)
		 * driver).unlockDevice(); else ((AndroidDriver) driver).lockDevice();
		 */ 
    	
    	//((AndroidDriver) driver).unlockDevice();
    	//Runtime.getRuntime().exec("adb shell am start -n io.appium.unlock/.Unlock");
    	
    	// Click on add new contact floating button
    	//unlockDevice();
    	
		//  ((AndroidDriver) driver).unlockDevice();
    	Thread.sleep(5000);
        driver.findElementById("android:id/icon").click();
        Thread.sleep(5000);
        // Find the first name, last name, and phone number fields
        MobileElement firstName = driver.findElementByXPath("//android.widget.EditText[@text='First name']");
        MobileElement lastName = driver.findElementByXPath("//android.widget.EditText[@text='Surname']");
        MobileElement phoneNumber = driver.findElementByXPath("//android.widget.EditText[@text='Phone']");
        
        // Enter the text in the fields
        firstName.sendKeys("Aaditya");
        lastName.sendKeys("Varma");
        phoneNumber.sendKeys("9991284782");
        
        // Save the contact
        driver.findElementById("editor_menu_save_button").click();
        
        // Wait for contact card to appear
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("toolbar_parent")));

        // Assertion
        MobileElement mobileCard = driver.findElementById("toolbar_parent");
        Assert.assertTrue(mobileCard.isDisplayed());
        
        String contactName = driver.findElementById("large_title").getText();
        Assert.assertEquals(contactName, "Aaditya Varma");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
    
    
  
    
    
}