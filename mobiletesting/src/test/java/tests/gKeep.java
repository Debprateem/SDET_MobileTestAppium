package tests;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.Assert;

import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;

public class gKeep {
	  AppiumDriver<MobileElement> driver = null;
	  DesiredCapabilities caps = null;
	  URL myUrl = null;
	
	  @BeforeClass
	  public void beforeClass() throws MalformedURLException {
		  caps = new DesiredCapabilities();
		  caps.setCapability("deviceId", "d85fff2");
	      caps.setCapability("platformName", "Android");
	      caps.setCapability("appPackage", "com.google.android.keep");
	      caps.setCapability("appActivity", ".activities.BrowseActivity");
	      caps.setCapability("noReset", true);
	      
	      myUrl = new URL("http://0.0.0.0:4723/wd/hub");
	      
	      driver = new AndroidDriver<MobileElement>(myUrl,caps);
	      
	      driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  }
		
	  @Test
	  public void myNotes() {
		 
		  
		  driver.findElementById("com.google.android.keep:id/new_note_button").click();
		 
		  driver.findElementById("com.google.android.keep:id/editable_title").sendKeys("My Notes");
		  
		  driver.findElementById("com.google.android.keep:id/edit_note_text").sendKeys("Complete SDET project");
		 
		  driver.findElementByAccessibilityId("Navigate up").click();
		 
		  String noteTitle = driver.findElementById("com.google.android.keep:id/index_note_title").getText();
		  String noteDescription = driver.findElementById("com.google.android.keep:id/index_note_text_description").getText();
		  
		  System.out.println(noteTitle);
		  System.out.println(noteDescription);
		  
		  Assert.assertEquals(noteTitle, "My Notes");
		  Assert.assertEquals(noteDescription, "Complete SDET project");
	  }
	
	  @AfterClass
	  public void afterClass() {
		  driver.quit();
	  }
}
