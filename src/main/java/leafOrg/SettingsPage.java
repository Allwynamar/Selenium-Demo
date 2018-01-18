package leafOrg;

import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.android.AndroidDriver;
import wrapper.LeafOrgWrappers;

public class SettingsPage  extends LeafOrgWrappers{ 

	public SettingsPage(AndroidDriver<?> driver, ExtentTest test) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		this.test = test;
	}

	public MyProfilePage clickMyProfile(){
		clickByXpath(prop.getProperty("Settings.MyProfile.Xpath"));		
		return new MyProfilePage(driver, test);
	}

	public SettingsPage clickLogout(){
		clickByXpath(prop.getProperty("Settings.Logout.Xpath"));		
		return this;
	}

	public LoginPage clickYesLogout(){
		clickByXpath(prop.getProperty("Settings.YesLogout.Xpath"));		
		return new LoginPage(driver, test);
	}


}
