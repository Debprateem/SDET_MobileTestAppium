package tests;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.Assert;

import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;

public class gTasks {
	  AppiumDriver<MobileElement> driver = null;
	  DesiredCapabilities caps = null;
	  URL myUrl = null;
	
	  @BeforeClass
	  public void beforeClass() throws MalformedURLException {
		  caps = new DesiredCapabilities();
		  caps.setCapability("deviceId", "d85fff2");
	      caps.setCapability("platformName", "Android");
	      caps.setCapability("appPackage", "com.google.android.apps.tasks");
	      caps.setCapability("appActivity", ".ui.TaskListsActivity");
	      caps.setCapability("noReset", true);
	      
	      myUrl = new URL("http://0.0.0.0:4723/wd/hub");
	      
	      driver = new AndroidDriver<MobileElement>(myUrl,caps);
	      
	      driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  }
		
	  @Test
	  public void myTasks() {
		 
 		  //click add task
		  driver.findElementById("com.google.android.apps.tasks:id/tasks_fab").click();
		  //enter task1 name
		  driver.findElementById("com.google.android.apps.tasks:id/add_task_title").sendKeys("Complete Activity with Google Tasks");
		  //save task
		  driver.findElementById("com.google.android.apps.tasks:id/add_task_done").click();
		  
		  driver.findElementById("com.google.android.apps.tasks:id/tasks_fab").click();
		
		  driver.findElementById("com.google.android.apps.tasks:id/add_task_title").sendKeys("Complete Activity with Google Keep");
		  //save task
		  driver.findElementById("com.google.android.apps.tasks:id/add_task_done").click();
		 
		  driver.findElementById("com.google.android.apps.tasks:id/tasks_fab").click();
		
		  driver.findElementById("com.google.android.apps.tasks:id/add_task_title").sendKeys("Complete the second Activity Google Keep");
		
		  driver.findElementById("com.google.android.apps.tasks:id/add_task_done").click();

		  String task1 = driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector()).getChildByText(UiSelector().resourceId(\"com.google.android.apps.tasks:id/task_name\"),\"Complete Activity with Google Tasks\")")).getText();
		 
		  String task2 = driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector()).getChildByText(UiSelector().resourceId(\"com.google.android.apps.tasks:id/task_name\"),\"Complete Activity with Google Keep\")")).getText();
		 
		  String task3 = driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector()).getChildByText(UiSelector().resourceId(\"com.google.android.apps.tasks:id/task_name\"),\"Complete the second Activity Google Keep\")")).getText();
		  
		  System.out.println(task1);
		  System.out.println(task2);
		  System.out.println(task3);
		  
		  Assert.assertEquals(task1, "Complete Activity with Google Tasks");
		  Assert.assertEquals(task2, "Complete Activity with Google Keep");
		  Assert.assertEquals(task3, "Complete the second Activity Google Keep");

	  }
	
	  @AfterClass
	  public void afterClass() {
		  driver.quit();
	  }

}
