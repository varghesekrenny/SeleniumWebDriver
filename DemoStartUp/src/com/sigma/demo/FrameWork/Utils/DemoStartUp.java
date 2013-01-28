package com.sigma.demo.FrameWork.Utils;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.MethodRule;
import org.junit.rules.TestName;
import org.junit.rules.TestWatchman;
import org.junit.runners.model.FrameworkMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sigma.demo.Constants.CommonUtils;
import com.sigma.demo.Constants.DataConstants;
import com.sigma.demo.FrameWork.Helpers.PropertyValue;
import com.sigma.demo.FrameWork.Helpers.TestSheetDetails;
import com.sigma.demo.FrameWork.Helpers.XMLDataHelper;

public class DemoStartUp {

	public static WebDriver webdriver;
	public static String TEST_ID;
	public static String USER_NAME;
	public static String PASS_WORD;
	public static String DESCRIPTION;
	public static String TEST_SEVERITY;
	public static String EXPECTEDRESULT;
	public static String EXPECTEDRESULT1;
	public static String CONTACTEMAIL;
	public static String STATUS;
	public static String IMAGE;

	/*********************************
	 * PRIORAPRROVAL MODULE VARIABLES
	 * ********************************/

	public static String CONTACTPHONE;
	public static String URL;
	public static String FROMDATE;
	public static String TODATE;

	/*********************************
	 * FILEMANAGEMENT MODULE VARIABLES
	 */

	public static String FILETITLE;
	public static String FILEDESCRIPTION;
	public static String THUMBNAIL;
	public static String UPLOADFILES;
	public static String COST;

	/************************************************************/
	
	public static String NEWSTITLE;
	public static String NEWSCONTENT;

	static Logger logger = Logger.getLogger(DemoStartUp.class);

	private static HashMap<String, Integer> testcaseid_rowno_hmp = null;
	public static ArrayList<PropertyValue> propertyList;
	private static ArrayList<XMLDataHelper> xmlDataDetails = null;
	protected ReadXLFile readFile = new ReadXLFile();
	protected static TestSheetDetails testSheet = new TestSheetDetails();
	private static XMLDataHelper xmlDataHelper = new XMLDataHelper();

	@Before
	public void setUp() throws Exception {
		Properties properties = new Properties();
		// Read properties in non static method -->
		// prop.load(getClass().getClassLoader().getResourceAsStream("config.properties");));
		DesiredCapabilities desiredCapabilites = new DesiredCapabilities();

		desiredCapabilites.setJavascriptEnabled(true);
		Capabilities capabilities = (Capabilities) desiredCapabilites;

		properties.load(new FileInputStream(DataConstants.file));
		PropertyConfigurator.configure(properties);

		webdriver = new FirefoxDriver(capabilities);
	}

	public void getTestSheetMapping(int sheetNumb) {
		ReadXLFile readXL = new ReadXLFile();
		String testSuiteName = readXL.getTestSuiteDetails(
				DataConstants.fileName, sheetNumb);
		DemoStartUp.testcaseid_rowno_hmp = readXL.createHashMap(
				DataConstants.fileName, sheetNumb);
		testSheet.setTestSheetNo(sheetNumb);
		testSheet.setTestSuiteName(testSuiteName);
	}

	public void readLoadTestProperties(String testid) {
		if (CommonUtils.isEmpty(testid)
				|| (testcaseid_rowno_hmp.get(testid.trim()) == null)) {
			logger.error("No Test caseId exists");
		}
		xmlDataHelper.setTestCaseID(testid);
		int rowNo = (Integer) testcaseid_rowno_hmp.get(testid.trim());
		propertyList = readFile.readPropertiesFromXL(DataConstants.fileName,
				testSheet.getTestSheetNo(), rowNo);
		xmlDataHelper.setTestCaseSeverity(readFile.readPropertyValues(
				propertyList, DataConstants.TESTCASESEVERITY));
		xmlDataHelper.setTestCaseName(readFile.readPropertyValues(propertyList,
				DataConstants.TESTCASEDESCRIPTION));

	}

	public void initializeTestProps(String testid) {
		readLoadTestProperties(testid);
		readDataFromXLFile();
	}

	public static void loginConnectPlusApplication(String username,
			String password) {
		new WebDriverWait(webdriver, 30);
		webdriver.get(DataConstants.BASE_URL);
		webdriver.navigate().refresh();

		webdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String actualLoginButton = webdriver.findElement(
				By.xpath("/html/body/div/div/ul/li[2]/a")).getText();
		String expectedLoginButton = "Log In ";
		assertEquals(expectedLoginButton, actualLoginButton);
		webdriver.findElement(By.xpath("/html/body/div/div/ul/li[2]/a"))
				.click();
		webdriver.findElement(By.name("username")).sendKeys(username);
		webdriver.findElement(By.name("password")).sendKeys(password);
		webdriver
				.findElement(
						By.xpath("/html/body/div/div[4]/div/div/div[2]/form/table/tbody/tr[4]/td[2]/button"))
				.click();

	}

	public void readDataFromXLFile() {
		ReadXLFile readXLFile = new ReadXLFile();
		TEST_ID = readXLFile.readPropertyValues(propertyList, "TESTID");
		DESCRIPTION = readXLFile
				.readPropertyValues(propertyList, "DESCRIPTION");
		USER_NAME = readXLFile.readPropertyValues(propertyList, "USERNAME");
		PASS_WORD = readXLFile.readPropertyValues(propertyList, "PASSWORD");
		TEST_SEVERITY = readXLFile.readPropertyValues(propertyList, "SEVERITY");
		FILETITLE = readXLFile.readPropertyValues(propertyList, "FILETITLE");
		FILEDESCRIPTION = readXLFile.readPropertyValues(propertyList,
				"FILEDESCRIPTION");
		THUMBNAIL = readXLFile.readPropertyValues(propertyList, "THUMBNAIL");
		UPLOADFILES = readXLFile
				.readPropertyValues(propertyList, "UPLOADFILES");
		CONTACTPHONE = readXLFile.readPropertyValues(propertyList,
				"CONTACTPHONE");
		URL = readXLFile.readPropertyValues(propertyList, "URL");
		EXPECTEDRESULT = readXLFile.readPropertyValues(propertyList,
				"EXPECTEDRESULT");
		EXPECTEDRESULT1 = readXLFile.readPropertyValues(propertyList,
				"EXPECTEDRESULT1");
		CONTACTEMAIL = readXLFile.readPropertyValues(propertyList,
				"CONTACTEMAIL");
		STATUS = readXLFile.readPropertyValues(propertyList, "STATUS");
		FROMDATE = readXLFile.readPropertyValues(propertyList, "FROMDATE");
		TODATE = readXLFile.readPropertyValues(propertyList, "TODATE");
		IMAGE = readXLFile.readPropertyValues(propertyList, "IMAGE");
		COST = readXLFile.readPropertyValues(propertyList, "COST");
		NEWSTITLE = readXLFile.readPropertyValues(propertyList, "NEWSTITLE");
		NEWSCONTENT = readXLFile.readPropertyValues(propertyList, "NEWSCONTENT");
	}

	public void assertPass() {
		xmlDataHelper.setTestCaseStatus(DataConstants.TESTCASERESULT_SUCC);

	}

	public void assertFail(String message) {

		xmlDataHelper.setTestCaseStatus(DataConstants.TESTCASERESULT_FAIL);
		if (CommonUtils.isEmpty(xmlDataHelper.getTestCaseError())) {
			xmlDataHelper.setTestCaseError(message);
		} else {
			xmlDataHelper.setTestCaseError(CommonUtils.concatinateWithDelimit(
					xmlDataHelper.getTestCaseError(), message));
		}
		failed();
	}

	public void assignDefaultValueXml() {
		xmlDataHelper.setTestCaseError("");
		xmlDataHelper.setTestCaseID("");
		xmlDataHelper.setTestCaseName("");
		xmlDataHelper.setTestCaseSeverity("");
		xmlDataHelper.setTestCaseStatus("");
		xmlDataHelper.setTestScreenShot("");
	}

	/**
	 * TestName to get Current method used for testing
	 */
	@Rule
	public TestName name = new TestName();

	/**
	 * Rule to intercept different sections of code execution.
	 * 
	 */
	@Rule
	public MethodRule watchman = new TestWatchman() {

		/**
		 * Calls the finish Method once testcase method gets finished.
		 */
		@Override
		public void finished(FrameworkMethod method) {
			try {
				xmlDataDetails = new ArrayList<XMLDataHelper>();
				GeneralXMLOutput.updateXMLFile(xmlDataDetails,
						xmlDataHelper.getTestCaseID(),
						xmlDataHelper.getTestCaseName(),
						xmlDataHelper.getTestCaseStatus(),
						xmlDataHelper.getTestCaseError(),
						xmlDataHelper.getTestCaseSeverity(),
						xmlDataHelper.getTestScreenShot());
				GeneralXMLOutput.updateXMLFileContent(
						DataConstants.fileDetails, xmlDataDetails);
				String current = webdriver.getWindowHandle();
				webdriver.quit();
				assignDefaultValueXml();
			} catch (Throwable exception) {
				logger.error("Error closing selenium: "
						+ exception.getMessage());
			}
		}
	};

	/**
	 * Failed method gets called once testmethod gets fails And Take the
	 * screenshot.
	 */

	public void failed() {
		logger.error(String.format("An error occurred in %s",
				name.getMethodName()));
		try {
			// Take a screenshot.
			// Throwable cause = e.getCause();
			File screenShot;
			screenShot = ((TakesScreenshot) webdriver)
					.getScreenshotAs(OutputType.FILE);

			logger.info("Save the screenshot.");
			String screenshotPath = DataConstants.SCREENSHOTFILE
					+ name.getMethodName() + ".png";
			xmlDataHelper.setTestScreenShot(screenshotPath);
			FileUtils.copyFile(screenShot, new File(screenshotPath));
		} catch (final Throwable t) {
			logger.error("Error on the failure logging: " + t.getMessage());
		}

	}
}