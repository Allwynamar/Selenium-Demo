package wrapper;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.relevantcodes.extentreports.ExtentTest;

import utils.DataInputProvider;

public class LeafOrgWrappers extends WrapperMethods {
	
	protected String browserName;
	protected String dataSheetName;
	protected String testCaseName;
	protected String testDescription;
	
	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, IOException{
		startResult();
		loadObjects();
	}

	@BeforeTest
	public void beforeTest(){
	System.out.println();	
	}
	
	@BeforeMethod
	public void beforeMethod(){
		ExtentTest test = startTestCase(testCaseName,testDescription);
		test.assignAuthor(authors);
		test.assignCategory(categories);
	}
		
	@AfterSuite
	public void afterSuite(){
		endResult();
	}

	@AfterTest
	public void afterTest(){
		
	}
	
	@AfterMethod
	public void afterMethod(){
		//closeApp();
		endTestcase();
	}
	
	@DataProvider(name="fetchData")
	public Object[][] getData(){
		return DataInputProvider.getSheet(dataSheetName);		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
