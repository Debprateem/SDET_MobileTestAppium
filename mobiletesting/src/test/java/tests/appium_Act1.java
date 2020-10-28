package tests;

import org.testng.annotations.Test;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.Assert;

import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;


public class appium_Act1 {
	
	AppiumDriver<MobileElement> driver = null;
	WebDriverWait wait;
	MobileElement newTask = null;
	public String homepageTxt; 
	List<MobileElement> taskList = new ArrayList<MobileElement>();
	
	
	 @BeforeClass
	    public void beforeClass() throws MalformedURLException {
	        // Set the Desired Capabilities
	        DesiredCapabilities caps = new DesiredCapabilities();
	        caps.setCapability("deviceId", "d85fff2");
	        caps.setCapability("platformName", "Android");
	        caps.setCapability("appPackage", "com.google.android.apps.tasks");
	        caps.setCapability("appActivity", ".ui.TaskListsActivity");
	        caps.setCapability("noReset", true);

	        // Instantiate Appium Driver
	        
	        URL appServer = new URL("http://0.0.0.0:4723/wd/hub");
	        driver = new AndroidDriver<MobileElement>(appServer, caps);
	        wait = new WebDriverWait(driver, 5);
	        
	      
	    }

	    @Test (priority =1)
	    public void verifyHomePage() {
	    	homepageTxt = driver.findElementById("task_list_title").getText(); 
	    	System.out.println(homepageTxt);
	    	Assert.assertEquals(homepageTxt, "My Tasks");
	        //driver.findElementById("tasks_fab").click();
	       

	    }
	    
	    @Test (priority =2)
	    public void addTask() throws Exception {
	    	Thread.sleep(2000);
	        driver.findElementById("tasks_fab").click();
	        Thread.sleep(2000);
	        newTask = driver.findElementById("add_task_title");
	        newTask.click();
	        newTask.sendKeys("Complete Activity with Google Tasks");
	        Thread.sleep(2000);
	        driver.findElementById("add_task_done").click();
	        
	        
	        
	        Thread.sleep(2000);
	        driver.findElementById("tasks_fab").click();
	        Thread.sleep(2000);
	        newTask.click();
	        newTask.sendKeys("Complete Activity with Google Keep");
	        Thread.sleep(2000);
	        driver.findElementById("add_task_done").click();
	        
	        
	        
	        Thread.sleep(2000);
	        driver.findElementById("tasks_fab").click();
	        Thread.sleep(2000);
	        newTask.click();
	        newTask.sendKeys("Complete the second Activity Google Keep");
	        Thread.sleep(2000);
	        driver.findElementById("add_task_done").click();
	        Thread.sleep(2000);

	    }

	    @Test (priority =3)
	    public void verifyTask() {
	    	
	    	taskList = driver.findElementsById("tasks_list");
	    	for(MobileElement t : taskList) {
	    		String taskName = t.getText();
	    		System.out.println(taskName);
	    	}
	    	
	    	
	    	
	    }

	    

	    @AfterClass
	    public void afterClass() {
	        driver.quit();
	    }
}
