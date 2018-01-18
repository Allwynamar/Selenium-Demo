package wrapper;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Wrappers {

	void loadObjects() throws FileNotFoundException, IOException;

	boolean launchApp(String appPackage, String appActivity, String deviceName);

	boolean launchActivity(String appPackage, String appActivity);

	boolean verifyAndInstallApp(String appPackage, String appPath);

	void sleep(int mSec);

	void printContext();

	boolean switchNativeView();

	boolean clickByID(String id);

	boolean clickByAccessebilityID(String id);

	boolean clickByXpath(String xpath);

	boolean verifyContentDescIsDisplayed(String xpath);

	boolean clickByLinkText(String LinkText);

	void enterTextByID(String id, String data);

	void pressEnter();

	void enterTextByXpath(String xpath, String data);

	long takeSnap();

	boolean verifyTextByID(String id, String data);

	boolean scrollDownInBrowser(int val);

	boolean backButton();

	boolean verifyAttributeTextByXPath(String xpath, String text);

	void enterTextByXpathUsingActions(String id, String data);

	boolean closeApp();

	boolean changeToPortrait();
	
	boolean hideKeyBoard();
	
	boolean getNetworkConnection();
	
	boolean setWIFINetworkConnection();
	
	boolean switchWebview();



}