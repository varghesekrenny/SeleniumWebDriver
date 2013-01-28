package com.sigma.demo.TestCases;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import com.sigma.demo.Constants.ApplicationUtils;
import com.sigma.demo.Constants.CommonUtils;
import com.sigma.demo.Constants.DataConstants;
import com.sigma.demo.FrameWork.Utils.DemoStartUp;

public class PolicyGuidelinesTest extends ApplicationUtils {

	protected static DemoStartUp demo = new DemoStartUp();

	static {
		demo.getTestSheetMapping(2);
	}

	@Test
	public void testPolicyGuidelines() {
		demo.initializeTestProps("PriorApprovalPolicyGuideLine");
		webdriver.get(DataConstants.BASE_URL);
		loginConnectPlusApplication(USER_NAME, PASS_WORD);
		webdriver.findElement(By.xpath("/html/body/div/div[4]/a[6]")).click();
		String actualPolicy = webdriver.findElement(
				By.xpath("/html/body/div/div[4]/div/h1")).getText();
		assertEquals(EXPECTEDRESULT, actualPolicy);
		if (!EXPECTEDRESULT.equals(actualPolicy)) {
	//		String msg = "Actual result does not match with expected policy guidelines result";
			assertFail(EXPECTEDRESULT);

		}
		webdriver
				.findElement(
						By.xpath("/html/body/div/div[4]/div[2]/div[2]/div/div/div/a/img"))
				.click();
		assertPass();
	}

	@Test
	public void testPolicyGuideLineWithoutValuesCancel() {
		demo.initializeTestProps("PriorApprovalPolicyGuideLineCancel");
		webdriver.get(DataConstants.BASE_URL);
		loginConnectPlusApplication(USER_NAME, PASS_WORD);
		goToFileMangementModule();
		webdriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		webdriver.findElement(By.xpath(".//*[@id='buttonRow']/td[2]/div/a")).click();
		Alert alert = webdriver.switchTo().alert();
		String actualValue = alert.getText();
		if (!EXPECTEDRESULT.equalsIgnoreCase(actualValue)) {
			assertFail(actualValue);
		}
		alert.accept();
		assertPass();
		webdriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@Test
	public void testPolicyGuideLineWithoutValueSubmit() {
		demo.initializeTestProps("PriorApprovalPolicyGuideLineSubmit");
		webdriver.get(DataConstants.BASE_URL);
		loginConnectPlusApplication(USER_NAME, PASS_WORD);
		goToFileMangementModule();
		webdriver.findElement(By.id("saveButton")).click();
		String[] actualValue = { "Please provide a File Title.",
				"Please provide a Description.",
				"Please upload the Thumbnail for the File.",
				"Please either upload a File or enter the URL." };
		String[] expectedValue = EXPECTEDRESULT.split("##");
		for (int i = 0; i < expectedValue.length; i++) {
			if (!expectedValue[i].equalsIgnoreCase(actualValue[i])) {
				assertFail(expectedValue[i]);
			}
		}
		assertPass();
		webdriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@Test
	public void testPolicyGuideLineFileTitle() {

		demo.initializeTestProps("PriorApprovalPolicyGuideLineFileTitle");
		webdriver.get(DataConstants.BASE_URL);
		loginConnectPlusApplication(USER_NAME, PASS_WORD);
		goToFileMangementModule();
		String[] expectedValue = EXPECTEDRESULT.split("##");
		String[] fileTitle = FILETITLE.split("##");
		String actualVal = "No element Found";
		for (int i = 0; i < 2; i++) {
			if (fileTitle[i].equalsIgnoreCase("BLANK")) {
				fileTitle[i] = "";
			}
				webdriver.findElement(By.id("fileTitle"))
						.sendKeys(fileTitle[i]);
			
			webdriver.findElement(By.id("dealerDescription")).sendKeys(
					DESCRIPTION);
			webdriver.findElement(By.id("url")).sendKeys(URL);
			webdriver.findElement(By.id("thumbnail")).sendKeys(THUMBNAIL);
			webdriver.findElement(By.id("submitThumbnail")).click();
			webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			webdriver.findElement(By.id("saveButton")).click();
			webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			try {
				    if(i==0)
				    {
					String actualValue = webdriver.findElement(
							By.xpath(".//*[@id='errorDiv2']")).getText();
					actualVal = actualValue;
				    }
				    else
				    {
				    	String actualValue = webdriver.findElement(By.xpath("html/body/div[1]/div[4]/div[2]/div/div[2]")).getText();
				    	actualVal = actualValue;		
				    }
			} catch (NoSuchElementException ex) {
				System.out.println("Error happened in finding element");
			}
			if (!expectedValue[i].equals(actualVal)) {
				assertFail(expectedValue[i]);
			}
			webdriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			testPolicyGuideLineClear();
		}
		assertPass();
	}

	@Test
	public void testPolicyGuideLineThumbNail() {

		demo.initializeTestProps("PriorApprovalPolicyGuideLineThumbNail");
		webdriver.get(DataConstants.BASE_URL);
		loginConnectPlusApplication(USER_NAME, PASS_WORD);
		goToFileMangementModule();
		String[] expectedElement = EXPECTEDRESULT.split("##");
		String[] thumbNail = THUMBNAIL.split("##");

		for (int i = 0; i < 2; i++) {
			if (thumbNail[i].equalsIgnoreCase("BLANK")) {
				thumbNail[i] = " ";
			}
			webdriver.findElement(By.id("fileTitle")).sendKeys(FILETITLE);
			webdriver.findElement(By.id("dealerDescription")).sendKeys(
					DESCRIPTION);
			webdriver.findElement(By.id("url")).sendKeys(URL);
			webdriver.findElement(By.id("thumbnail")).sendKeys(thumbNail[i]);
			if (i == 0) {
				webdriver.findElement(By.id("submitThumbnail")).click();
			}
			String actualValue = webdriver.findElement(By.id("errorDiv5"))
					.getText();
			if (!expectedElement[i].equalsIgnoreCase(actualValue)) {
				assertFail(EXPECTEDRESULT);
			}
			testPolicyGuideLineClear();
		}
		assertPass();
	}
	// Uploading files(Training center, Policy guidelines, resource center)
			@Test
			public void testPolicyGuideLineUploadUrl() throws Exception {
				demo.initializeTestProps("PriorApprovalPolicyGuideLineUploadUrl");
				String[] expectedValue = EXPECTEDRESULT.split("##");
				
				testAdmin(expectedValue[0]);
				webdriver.findElement(
						By.xpath(DataConstants.fileManagementXpath
								+ "/div[1]/form/button")).click();
				String actualPage = webdriver.findElement(
						By.xpath("html/body/div[1]/div[4]/div[2]/div[3]/span"))
						.getText();
				// String expectedPage = "POLICY & GUIDELINES";

				if(!expectedValue[1].equalsIgnoreCase(actualPage))
				{
					assertFail(expectedValue[1]);
				}
				dropDownPolicyGuideLines(expectedValue[2]);
				assertPass();
				

			}

			// ISSUE(ISSUES NEED TO BE FIXED BY DEVELOPERS)
			@Test
			public void testPolicyGuideLineUploadFile() throws Exception {
				demo.initializeTestProps("PriorApprovalPolicyGuideLineUploadFile");
				String[] expectedValue = EXPECTEDRESULT.split("##");
			
				testAdmin(expectedValue[0]);
				webdriver.findElement(By.xpath(DataConstants.uploadButtonXpath))
						.click();
				String actualPage = webdriver.findElement(
						By.xpath("html/body/div[1]/div[4]/div[2]/div[3]/span"))
						.getText();
				// String expectedPage = "POLICY & GUIDELINES";
				if (!expectedValue[1].equals(actualPage)) {
					String msg = "Actual result does not match with expected policy guidelines result";
					assertFail(msg);

				}
				policyGuidelines(expectedValue[2], expectedValue[3]);
				System.out.println(CommonUtils.getCurrentTime());
				assertPass();
			}

			@Test
			public void testPolicyGuidelinesAllFieldsNull() throws Exception {
				demo.initializeTestProps("PolicyGuidelinesAllFieldsNull");
				String[] expectedValue = new String[50];
				String[] expectedResult = new String[50];
				expectedValue = EXPECTEDRESULT.split("##");
				expectedResult = EXPECTEDRESULT1.split("##");
			
				testAdmin(expectedValue[0]);
				webdriver.findElement(By.xpath(DataConstants.uploadButtonXpath))
						.click();
				String actualPage = webdriver.findElement(
						By.xpath("html/body/div[1]/div[4]/div[2]/div[3]/span"))
						.getText();
				// String expectedPage = "POLICY & GUIDELINES";
				
				if (!expectedValue[1].equals(actualPage)) {
					String msg = "Actual result does not match with expected policy guidelines result";
					assertFail(msg);
				}
				dropDownFilePolicy(expectedValue[2], expectedValue[3], expectedResult[0],
						expectedResult[1], expectedResult[2]);
				assertPass();
			}
	
}