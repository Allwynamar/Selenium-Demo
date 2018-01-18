package leafOrg;

import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.android.AndroidDriver;
import wrapper.LeafOrgWrappers;

public class LoginPage extends LeafOrgWrappers{
	public LoginPage(AndroidDriver<?> driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}

	public LoginPage launchLeafOrgApp(String deviceName){
		launchApp("com.testleaf.leaforg", "com.testleaf.leaforg.MainActivity", deviceName);	
		switchWebview();
		return this;
	}


	public LoginPage enterUserName(String email){
		enterTextByXpath(prop.getProperty("LoginPage.Email.Xpath"),email);
		return this;
	}

	public LoginPage enterPassword(String password){
		enterTextByXpath(prop.getProperty("LoginPage.Password.Xpath"), password);
		return this;
	}

	public HomePage clickLogin(){
		clickByXpath(prop.getProperty("LoginPage.Login.Xpath"));
		return new HomePage(driver, test);
	}

	public  HomePage loginToLeafOrg(String deviceName,String email,String password){
		launchApp("com.testleaf.leaforg", "com.testleaf.leaforg.MainActivity", deviceName);	
		switchWebview();
		enterTextByXpath(prop.getProperty("LoginPage.Email.Xpath"),email);
		enterTextByXpath(prop.getProperty("LoginPage.Password.Xpath"), password);
		clickByXpath(prop.getProperty("LoginPage.Login.Xpath"));
		return new HomePage(driver, test);
	}




}
