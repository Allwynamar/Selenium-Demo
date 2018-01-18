package leafOrg;

import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.android.AndroidDriver;
import wrapper.LeafOrgWrappers;

public class HomePage extends LeafOrgWrappers{
	
	public HomePage(AndroidDriver<?> driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}

	public SettingsPage clickSettings(){
		clickByXpath(prop.getProperty("HomePage.Settings.Xpath"));
		return new SettingsPage(driver, test);
	}


}