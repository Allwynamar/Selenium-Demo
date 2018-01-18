package testcases;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import leafOrg.LoginPage;
import wrapper.LeafOrgWrappers;

public class TC01_Login extends LeafOrgWrappers{

	@BeforeClass
	public void setValues(){
		dataSheetName 	= "TC001";
		testCaseName 	= "Login to LeafOrg App";
		testDescription = "Login to LeafOrg app (using POM framework)";
		categories = "smoke";
		authors = "Testleaf";

	}

	@Test(dataProvider="fetchData")
	public void login(String deviceName,String email,String Password) throws IOException {
		new LoginPage(driver, test)
		.loginToLeafOrg(deviceName, email, Password)
		.clickSettings()
		.clickLogout()
		.clickYesLogout();

	}

}
