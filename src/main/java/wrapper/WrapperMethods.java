package wrapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.Connection;
import utils.Reporter;


public class WrapperMethods  extends Reporter implements Wrappers{

	public AndroidDriver<?> driver;
	protected static Properties prop;

	public WrapperMethods(AndroidDriver<?> driver, ExtentTest test) {
		this.driver = driver;
		this.test=test;
	}

	public WrapperMethods() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./config.properties")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see wrapper.Wrappers#loadObjects()
	 */
	public void loadObjects() throws FileNotFoundException, IOException{
		prop = new Properties();
		prop.load(new FileInputStream(new File("./object.properties")));

	}

	/* (non-Javadoc)
	 * @see wrapper.Wrappers#launchApp(java.lang.String, java.lang.String, java.lang.String)
	 */
	public boolean launchApp(String appPackage,String appActivity,String deviceName ){
		try {
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setCapability("appPackage", appPackage);
			dc.setCapability("appActivity", appActivity);
			dc.setCapability("deviceName", deviceName);
			dc.setCapability("nativeWebScreenshot", "true");
			driver = new AndroidDriver<WebElement>(new URL("http://0.0.0.0:4723/wd/hub"), dc);
			reportStep("The Appication package:" + appPackage + " launched successfully", "PASS");
		} catch (MalformedURLException e) {
			e.printStackTrace();
			reportStep("The Appication package:" + appPackage + " could not be launched", "FAIL");
		}
		return true;	
	}

	/* (non-Javadoc)
	 * @see wrapper.Wrappers#launchActivity(java.lang.String, java.lang.String)
	 */
	public boolean launchActivity(String appPackage, String appActivity){
		try {
			Activity activity = new Activity (appPackage, appActivity);
			driver.startActivity(activity);

			reportStep("The Appication package:" + appPackage + " launched successfully", "PASS");

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			reportStep("The Appication package:" + appPackage + " could not be launched", "FAIL");
		}
		return true;
	}



	/* (non-Javadoc)
	 * @see wrapper.Wrappers#verifyAndInstallApp(java.lang.String, java.lang.String)
	 */
	public boolean verifyAndInstallApp(String appPackage, String appPath) {
		boolean bInstallSuccess = false;

		try {
			if (driver.isAppInstalled(appPackage))
				driver.removeApp(appPackage);
			driver.installApp(appPath);
			reportStep("The Application:" + appPackage + " installed successfully", "PASS");

			bInstallSuccess = true;
		} catch (Exception e) {

			reportStep("The Application:" + appPackage + " could not be installed", "FAIL");
			// TODO: handle exception
		}
		return bInstallSuccess;
	}


	/* (non-Javadoc)
	 * @see wrapper.Wrappers#sleep(int)
	 */
	public void sleep(int mSec){
		try {
			Thread.sleep(mSec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see wrapper.Wrappers#printContext()
	 */
	public void printContext(){
		try {
			Set<String> contexts = driver.getContextHandles();
			for (String string : contexts) {
				System.out.println(string);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportStep("The Context could not be found", "FAIL");

		}
	}

	/* (non-Javadoc)
	 * @see wrapper.Wrappers#switchview()
	 */
	public boolean switchNativeView(){
		try {
			Set<String> contexts = driver.getContextHandles();
			for (String contextName : contexts) {
				if (contextName.contains("NATIVE"))
					driver.context(contextName);
			}

		} catch (Exception e) {
			e.printStackTrace();
			reportStep("The Context could not be switched", "FAIL");
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see wrapper.Wrappers#clickByID(java.lang.String)
	 */
	public boolean clickByID(String id){
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
			driver.findElementById(id).click();
			reportStep("The element with id: "+id+" is clicked.", "PASS");
		} catch (Exception e) {
			e.printStackTrace();
			reportStep("The element with id: "+id+" could not be clicked.", "FAIL");
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see wrapper.Wrappers#clickByAccessebilityID(java.lang.String)
	 */
	public boolean clickByAccessebilityID(String id){
		try {
			/*WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(ByAccessibilityId(ID));*/
			driver.findElementByAccessibilityId(id).click();
			reportStep("The element with Accessibility id: "+id+" is clicked.", "PASS");
		} catch (Exception e) {
			e.printStackTrace();
			reportStep("The element with Accessibility id: "+id+" could not be clicked.", "FAIL");
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see wrapper.Wrappers#clickByXpath(java.lang.String)
	 */
	public boolean clickByXpath(String xpath){
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
			driver.findElementByXPath(xpath).click();
			reportStep("The element with Xpath: "+xpath+" is clicked.", "PASS");

		} catch (Exception e) {
			e.printStackTrace();
			reportStep("The element with Xpath: "+xpath+" could not be clicked.", "FAIL");
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see wrapper.Wrappers#verifyContentDescIsDisplayed(java.lang.String)
	 */
	public boolean verifyContentDescIsDisplayed(String xpath){
		boolean bReturn = false;
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		if(driver.findElementByXPath(xpath).isDisplayed()){
			bReturn = true;
			reportStep("The element with Xpath: "+xpath+" is displayed.", "PASS");
		}else
		{
			reportStep("The element with Xpath: "+xpath+" is not displayed.", "FAIL");
			bReturn = false;
		}
		return bReturn;

	}

	/* (non-Javadoc)
	 * @see wrapper.Wrappers#clickByLinkText(java.lang.String)
	 */
	public boolean clickByLinkText(String LinkText){
		boolean bReturn = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(LinkText)));
			driver.findElementByLinkText(LinkText).click();
			reportStep("The element with LinkText: "+LinkText+" is clicked.", "PASS");
			bReturn = true;
		} catch (Exception e) {
			e.printStackTrace();
			reportStep("The element with LinkText: "+LinkText+" could not be clicked.", "FAIL");
			bReturn = false;
		}
		return bReturn;
	}

	/* (non-Javadoc)
	 * @see wrapper.Wrappers#enterTextByID(java.lang.String, java.lang.String)
	 */
	public void enterTextByID(String id,String data){
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
			driver.findElementById(id).clear();
			driver.findElementById(id).sendKeys(data);
			reportStep("The data: "+data+" entered successfully in the field with Id:"+id, "PASS");
		} catch (NoSuchElementException e) {
			reportStep("The data: "+data+" could not be entered in the field with Id:"+id, "FAIL");
		} catch (Exception e) {
			reportStep("Unknown exception occured while entering "+data+" in the field with Id:"+id, "FAIL");
		}

	}

	/* (non-Javadoc)
	 * @see wrapper.Wrappers#pressEnter()
	 */
	public void pressEnter(){
		try {
			driver.pressKeyCode(66);
			reportStep("Enter button in the keyboard pressed successfully", "PASS");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("Enter button in the keyboard could not be pressed successfully", "FAIL");
		}
	}

	/* (non-Javadoc)
	 * @see wrapper.Wrappers#enterTextByXpath(java.lang.String, java.lang.String)
	 */
	public void enterTextByXpath(String xpath,String data){
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
			driver.findElementByXPath(xpath).clear();
			driver.findElementByXPath(xpath).sendKeys(data);
			reportStep("The data: "+data+" entered successfully in the field with Xpath:"+xpath, "PASS");

		} catch (NoSuchElementException e) {
			reportStep("The data: "+data+" could not be entered in the field with Xpath:"+xpath, "FAIL");
		} catch (Exception e) {
			reportStep("Unknown exception occured while entering "+data+" in the field with Xpath:"+xpath, "FAIL");
		}

	}

	/* (non-Javadoc)
	 * @see wrapper.Wrappers#takeSnap()
	 */
	public long takeSnap(){
		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L; 
		try {
			FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE) , new File("./reports/images/"+number+".jpg"));

		} catch (WebDriverException e) {
			reportStep("The browser has been closed.", "FAIL");
		} catch (IOException e) {
			reportStep("The snapshot could not be taken", "WARN");
		}
		return number;
	}

	/* (non-Javadoc)
	 * @see wrapper.Wrappers#verifyTextByID(java.lang.String, java.lang.String)
	 */
	public boolean verifyTextByID(String id,String data){
		boolean bReturn = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
			String name = driver.findElementById(id).getText();
			if(name.contains(data)){
				bReturn = true;
				reportStep("The text: "+name+" matches with the value :"+data, "PASS");
			}else
				bReturn = false;
			reportStep("The text: "+name+" did not match with the value :"+data, "FAIL");
		} catch (Exception e) {
			e.printStackTrace();
			reportStep("Unknown exception occured while verifying the title", "FAIL");
		}
		return bReturn;
	}


	/* (non-Javadoc)
	 * @see wrapper.Wrappers#backButton()
	 */
	public boolean backButton(){
		try {
			driver.navigate().back();
			reportStep("The Application screen is moved back", "PASS");
		} catch (Exception e) {
			e.printStackTrace();
			reportStep("The Application screen could not be moved back", "PASS");
		}
		return true;
	}





	/* (non-Javadoc)
	 * @see wrapper.Wrappers#verifyAttributeTextByXPath(java.lang.String, java.lang.String)
	 */
	public boolean verifyAttributeTextByXPath(String xpath,String text){
		boolean val = false;
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		String name = driver.findElementByXPath(xpath).getAttribute("content-desc");
		System.out.println(name);
		try {

			if(name.contains(text)){
				val = true;
				reportStep("The text: "+name+" matches with the value :"+text, "PASS");
			}else
				val = false;
			reportStep("The text: "+name+" did not match with the value :"+text, "FAIL");
		} catch (Exception e) {
			e.printStackTrace();
			reportStep("The text: "+name+" did not match with the value :"+text, "FAIL");
		}
		return val;
	}


	/* (non-Javadoc)
	 * @see wrapper.Wrappers#scrollDownInBrowser(int)
	 */
	public boolean scrollDownInBrowser(int val){
		try {
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,"+val+"\")", "");
		} catch (Exception e) {
			//			e.printStackTrace();
		}
		return true;
	}


	public void enterTextByXpathUsingActions(String xpath,String data){
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
			Actions actions = new Actions(driver);
			actions.moveToElement(driver.findElement(By.xpath(xpath)));
			actions.click();
			actions.sendKeys(data);
			actions.build().perform();
			reportStep("The data: "+data+" entered successfully in the field with Xpath:"+xpath, "PASS");

		} catch (NoSuchElementException e) {
			reportStep("The data: "+data+" could not be entered in the field with Xpath:"+xpath, "FAIL");
		} catch (Exception e) {
			reportStep("Unknown exception occured while entering "+data+" in the field with Xpath:"+xpath, "FAIL");
		}

	}


	public boolean closeApp(){
		try {
			driver.closeApp();
			reportStep("The Appication is closed successfully", "PASS");

		} catch (Exception e) {
			e.printStackTrace();
			reportStep("The Appication could not be closed", "FAIL");
		}
		return true;
	}

	public boolean changeToPortrait(){
		try {


			ScreenOrientation orientation= driver.getOrientation();
			System.out.println(orientation.value());

			if(orientation.value().contains("LANDSCAPE"))
			{
				driver.rotate(ScreenOrientation.PORTRAIT);
				ScreenOrientation or  = driver.getOrientation();
				reportStep("The Screen is in "+or+" ", "PASS");
			}
			else
			{
				driver.rotate(ScreenOrientation.LANDSCAPE);				
				ScreenOrientation or =driver.getOrientation();
				reportStep("The Screen is in "+or+" ", "PASS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportStep("App closed for unknown reason", "FAIL");
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see wrapper.Wrappers#hideKeyBoard()
	 */
	public boolean hideKeyBoard(){
		try {
			driver.hideKeyboard();
			reportStep("The Keyboard is hidden successfully", "PASS");

		} catch (Exception e) {
			e.printStackTrace();
			reportStep("The Keyboard is still available", "FAIL");
		}
		return true;
	}


	/* (non-Javadoc)
	 * @see wrapper.Wrappers#getNetworkConnection()
	 */
	public boolean getNetworkConnection(){
		try {
			Connection con = driver.getConnection();
			reportStep("The Network  is in "+con+" ", "PASS");

		} catch (Exception e) {
			e.printStackTrace();
			reportStep("The Phone crashed", "FAIL");
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see wrapper.Wrappers#setWIFINetworkConnection()
	 */
	public boolean setWIFINetworkConnection(){
		try {
			driver.setConnection(Connection.WIFI);
			reportStep("The Network is setted to WIFI connection ", "PASS");

		} catch (Exception e) {
			e.printStackTrace();
			reportStep("The WIFI connection could not be established", "FAIL");
		}
		return true;
	}

	public boolean switchWebview(){
		try {		
			Set<String> contextNames = driver.getContextHandles();
			for (String contextName : contextNames) {
				if (contextName.contains("WEBVIEW"))
					driver.context(contextName);				

			}
		} catch (Exception e) {
			e.printStackTrace();
			reportStep("The Webview couldnot be found", "FAIL");
		}
		return true;
	}


}
