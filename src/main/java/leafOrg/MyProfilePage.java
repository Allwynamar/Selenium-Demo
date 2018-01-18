package leafOrg;

import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.android.AndroidDriver;
import wrapper.LeafOrgWrappers;

public class MyProfilePage extends LeafOrgWrappers{ 

	public MyProfilePage(AndroidDriver<?> driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}

	public MyProfilePage clickSaveChanges(){
		clickByXpath(prop.getProperty("MyProfilePage.SaveChanges.Xpath"));		
		return this;
	}
}
