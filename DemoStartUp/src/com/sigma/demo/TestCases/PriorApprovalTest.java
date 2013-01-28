package com.sigma.demo.TestCases;

import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import com.sigma.demo.Constants.ApplicationUtils;
import com.sigma.demo.Constants.CommonUtils;
import com.sigma.demo.Constants.DataConstants;
import com.sigma.demo.FrameWork.Utils.DemoStartUp;

public class PriorApprovalTest extends ApplicationUtils {

	protected static DemoStartUp demo = new DemoStartUp();

	static {
		demo.getTestSheetMapping(1);
	}

	@Test
	public void testPriorApprovalRequest() throws Exception {
		demo.initializeTestProps("PriorApprovalRequest");
		priorApproval_Login();
		submitApprovalDetails(CONTACTPHONE, URL);
		uploadFile();
		selectFromDate();
		selectToDate();
		submitButton();
		confirmationPage(EXPECTEDRESULT);
		assertPass();
	}

	@Test
	public void testPriorApprovalRequestPending() throws Exception {
		demo.initializeTestProps("RequestPA_Pending");
		priorApproval_Login();
		submitApprovalDetails(CONTACTPHONE, URL);
		uploadFile();
		selectFromDate();
		selectToDate();
		submitButton();
		confirmationPageWithOutSubmit(EXPECTEDRESULT);

		String text = webdriver.findElement(
				By.xpath("html/body/div[1]/div[4]/div[2]/div[2]/p[1]"))
				.getText();

		String value = text.substring(60, text.length() - 1);
		System.out.println(value);

		webdriver.findElement(
				By.xpath("html/body/div[1]/div[4]/div[1]/div/ul/li[2]/a"))
				.click();
		String actualValue = webdriver.findElement(
				By.xpath(".//*[@id='pahistorytable']/tbody/tr[1]/td[7]"))
				.getText(); // Reading Status on History page
		if (!STATUS.equalsIgnoreCase(actualValue)) {
			assertFail(STATUS);
		}
		assertPass();
	}

	/*
	 * @Test public void testPriorApprovalRequestApproved() throws Exception {
	 * demo.initializeTestProps("RequestPA_Approved");
	 * PriorApprovalLoginAndContact(); webdriver.findElement(
	 * By.xpath("html/body/div[1]/div[4]/div[1]/div/ul/li[2]/a")) .click();
	 * webdriver.findElement(
	 * By.xpath("//*[@id='pahistorytable']/tbody/tr[1]/td[9]/a")) .click();
	 * String PriorApprovalStatus = webdriver .findElement( By.xpath(
	 * ".//*[@id='priorApprovalUpload']/div[1]/div[2]/table/tbody/tr[1]/td[2]/p"
	 * )) .getText(); webdriver.manage().timeouts().implicitlyWait(20,
	 * TimeUnit.SECONDS);
	 * webdriver.findElement(By.id("fileStatus_2666")).sendKeys("Pending");
	 * webdriver .findElement( By.xpath(
	 * ".//*[@id='priorApprovalUpload']/div[2]/div[2]/table/tbody/tr[6]/td[2]/div/table/tbody/tr[2]/td/input[1]"
	 * )) .click();
	 * /html/body/div/div[4]/form/div[2]/div[2]/table/tbody/tr[6]/td
	 * [2]/div/table/tbody/tr[2]/td/input[2] Alert alert =
	 * webdriver.switchTo().alert(); String alertValue = alert.getText(); if
	 * (!EXPECTEDRESULT.equalsIgnoreCase(alertValue)) { assertFail(alertValue);
	 * } alert.accept();
	 * 
	 * webdriver.findElement(By.id("fileStatus_2666")).sendKeys("Approved");
	 * webdriver .findElement( By.xpath(
	 * ".//*[@id='priorApprovalUpload']/div[2]/div[2]/table/tbody/tr[6]/td[2]/div/table/tbody/tr[2]/td/input[1]"
	 * )) .click(); String actualValue2 = webdriver.findElement(
	 * By.xpath(".//*[@id='pahistorytable']/tbody/tr[1]/td[7]")).getText();
	 * if(!STATUS.equalsIgnoreCase(actualValue2)) { assertFail(actualValue2); }
	 * assertPass(); }
	 */

	@Test
	public void testPriorApprovalRequestDenied() throws Exception {
		demo.initializeTestProps("RequestPA_Deny");
		priorApproval_Login();
		webdriver.findElement(
				By.xpath("html/body/div[1]/div[4]/div[1]/div/ul/li[2]/a"))
				.click();
		webdriver.findElement(
				By.xpath("//*[@id='pahistorytable']/tbody/tr[1]/td[9]/a"))
				.click();
		webdriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		webdriver
				.findElement(
						By.xpath("/html/body/div/div[4]/form/div[2]/div[2]/table/tbody/tr[6]/td[2]/div/table/tbody/tr[2]/td/input[2]"))
				.click();
		String actualValue = webdriver.findElement(
				By.xpath(".//*[@id='pahistorytable']/tbody/tr[1]/td[7]"))
				.getText();
		if (!STATUS.equalsIgnoreCase(actualValue)) {
			assertFail(STATUS);
		}
		assertPass();
	}

	@Test
	public void testPriorApprovalRequestModified() throws Exception {
		demo.initializeTestProps("RequestPA_Modified");
		priorApproval_Login();
		webdriver.findElement(
				By.xpath("html/body/div[1]/div[4]/div[1]/div/ul/li[2]/a"))
				.click();
		webdriver.findElement(
				By.xpath("//*[@id='pahistorytable']/tbody/tr[1]/td[9]/a"))
				.click();
		webdriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		webdriver
				.findElement(
						By.xpath("/html/body/div/div[4]/form/div[2]/div[2]/table/tbody/tr[6]/td[2]/div/table/tbody/tr[2]/td/input[3]"))
				.click();
		String actualValue = webdriver.findElement(
				By.xpath(".//*[@id='pahistorytable']/tbody/tr[1]/td[7]"))
				.getText();
		if (!STATUS.equalsIgnoreCase(actualValue)) {
			assertFail(STATUS);
		}
		assertPass();
	}

	@Test
	public void testPriorApprovalRequestOnGoingRadioButton() throws Exception {
		demo.initializeTestProps("RequestPA_OnGoingRadioButton");
		priorApproval_Login();
		submitApprovalDetails(CONTACTPHONE, URL);
		uploadFile();
		webdriver.findElement(By.xpath(".//*[@id='ongingActivityRadio']"))
				.click();
		submitButton();
		confirmationPage(EXPECTEDRESULT);
		assertPass();
	}

	@Test
	public void testRequestPAAllFieldsNull() throws Exception {
		demo.initializeTestProps("RequestPA_AllFieldsNull");
		String[] expectedResult = new String[100];
		String[] expectedResult1 = new String[100];
		expectedResult = EXPECTEDRESULT.split("##");
		expectedResult1 = EXPECTEDRESULT1.split("##");

		priorApproval_Login();
		submitButton();
		String actualSelect = webdriver.findElement(
				By.xpath(DataConstants.AllFieldsXpath + "/li[1]")).getText();
		if (!expectedResult[0].equals(actualSelect)) {
			String msg = "Actual result does not match with expected dealer name result";
			assertFail(msg);
		}
		String actualContactName = webdriver.findElement(
				By.xpath(DataConstants.AllFieldsXpath + "/li[2]")).getText();
		if (!expectedResult[1].equals(actualContactName)) {
			String msg = "Actual result does not match with expected contact name result";
			assertFail(msg);
		}
		String actualPhone = webdriver.findElement(
				By.xpath(DataConstants.AllFieldsXpath + "/li[3]")).getText();
		if (!expectedResult[2].equals(actualPhone)) {
			String msg = "Actual result does not match with expected phone number result";
			assertFail(msg);
		}
		String actualUrl = webdriver.findElement(
				By.xpath(DataConstants.AllFieldsXpath + "/li[4]")).getText();
		if (!expectedResult[3].equals(actualUrl)) {
			String msg = "Actual result does not match with expected url result";
			assertFail(msg);
		}
		String actualEmail = webdriver.findElement(
				By.xpath(DataConstants.AllFieldsXpath + "/li[5]")).getText();
		if (!expectedResult1[0].equals(actualEmail)) {
			String msg = "Actual result does not match with expected email result";
			assertFail(msg);
		}
		String actualMediaSelect = webdriver.findElement(
				By.xpath(DataConstants.AllFieldsXpath + "/li[6]")).getText();
		if (!expectedResult1[1].equals(actualMediaSelect)) {
			String msg = "Actual result does not match with expected media result";
			assertFail(msg);

		}

		String actualMediaFrom = webdriver.findElement(
				By.xpath(DataConstants.AllFieldsXpath + "/li[7]")).getText();
		if (!expectedResult1[2].equals(actualMediaFrom)) {
			String msg = "Actual result does not match with expected media from date result";
			assertFail(msg);
		}
		String actualMediaTo = webdriver.findElement(
				By.xpath(DataConstants.AllFieldsXpath + "/li[8]")).getText();
		if (!expectedResult1[3].equals(actualMediaTo)) {
			String msg = "Actual result does not match with expected media to date result";
			assertFail(msg);
		}
		assertPass();
	}

	@Test
	// Checking condition: when from date > to date
	public void testPA_DateValidation() throws Exception {
		demo.initializeTestProps("RequestPA_CalendarDateValidation");
		priorApproval_Login();
		submitApprovalDetails(CONTACTPHONE, URL);
		uploadFile();
		selectFromDate();
		selectToDate();
		submitButton();
		String actualDate = webdriver.findElement(
				By.xpath("/html/body/div/div[4]/form/div/div/div/ul/li"))
				.getText();
		webdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		assertEquals(EXPECTEDRESULT, actualDate);
		if (!EXPECTEDRESULT.equals(actualDate)) {
			assertFail(actualDate);

		}
		assertPass();

	}

	@Test
	public void testPA_ContactNameValidation() {
		demo.initializeTestProps("PA_FieldContactNameValidation");
		priorApproval_Login();

		submitApprovalContactName(CONTACTPHONE, URL);
		uploadFile();
		selectFromDate();
		selectToDate();
		submitButton();
		String actualMessage = webdriver.findElement(
				By.xpath("/html/body/div/div[4]/form/div/div/div/ul/li"))
				.getText();

		webdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// assertEquals(EXPECTEDELEMENT, actualDate);
		if (!EXPECTEDRESULT.equals(actualMessage)) {
			assertFail(actualMessage);
		}
		assertPass();
	}

	@Test
	public void testPA_FieldValidationContactPhoneValidation() {
		demo.initializeTestProps("PA_FieldContactPhoneValidation");
		priorApproval_Login();
		String[] expectedPhoneNumber = CONTACTPHONE.split("##");
		String[] expectedElement = EXPECTEDRESULT.split("##");

		for (int i = 0; i < 6; i++) {
			if (expectedPhoneNumber[i].equalsIgnoreCase("SPACE")) {
				expectedPhoneNumber[i] = "  ";
			}
			if (expectedPhoneNumber[i].equalsIgnoreCase("NULL")) {
				expectedPhoneNumber[i] = "";
			}
			webdriver.findElement(
					By.xpath(".//*[@id='dealerName_Id']/option[2]")).click();
			webdriver.findElement(By.id("contactName")).sendKeys(
					CommonUtils.actualLoginId);
			webdriver.findElement(By.id("contactEmail")).sendKeys(
					CommonUtils.regExpMail);
			webdriver.findElement(By.id("contactPhone")).sendKeys(
					expectedPhoneNumber[i]);
			webdriver.findElement(By.id("url")).sendKeys(URL);
			webdriver.findElement(By.xpath(".//*[@id='mediaType']/option[2]"))
					.click();
			uploadFile();
			selectFromDate();
			selectToDate();
			submitButton();
			String actualMessage = webdriver.findElement(
					By.xpath(".//*[@id='errorMessage']/ul/li")).getText();
			/*System.out.println(actualMessage);
             System.out.println(expectedElement[i]);*/
			webdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			if (!expectedElement[i].equals(actualMessage)) {
				String msg = "Actual result does not match with Expected Message";
				assertFail(expectedElement[i]);
			}
			assignDefaultTestValue();
		}
		assertPass();
	}

	@Test
	public void testPA_FieldValidationContactEmailValidation() {
		demo.initializeTestProps("PA_FieldContactEmailValidation");
		priorApproval_Login();
		String[] contactEmail = CONTACTEMAIL.split("##");
		String[] expectedElement = EXPECTEDRESULT.split("##");
		for (int i = 0; i < 5; i++) {
			if (contactEmail[i].equalsIgnoreCase("SPACE")) {
				contactEmail[i] = "  ";
			}
			webdriver.findElement(
					By.xpath(".//*[@id='dealerName_Id']/option[2]")).click();
			webdriver.findElement(By.id("contactName")).sendKeys(
					CommonUtils.actualLoginId);
			webdriver.findElement(By.id("contactEmail")).sendKeys(
					contactEmail[i]);
			webdriver.findElement(By.id("contactPhone")).sendKeys(CONTACTPHONE);
			webdriver.findElement(By.id("url")).sendKeys(URL);
			webdriver.findElement(By.xpath(".//*[@id='mediaType']/option[2]"))
					.click();
			uploadFile();
			selectFromDate();
			selectToDate();
			submitButton();
			String actualMessage = webdriver.findElement(
					By.xpath(".//*[@id='errorMessage']/ul/li[1]")).getText();

			webdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			if (!expectedElement[i].equals(actualMessage)) {
				String msg = "Actual result does not match with Expected Message";
				assertFail(expectedElement[i]);
			}
			assignDefaultTestValue();
			webdriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}
		assertPass();
	}

	@Test
	public void testDealerUserStatusSearch() {
		// Search for approved and pending
		demo.initializeTestProps("RequestPA_SearchAP");
		String actualValue;
		priorApproval_Login();
		webdriver.findElement(
				By.xpath("html/body/div[1]/div[4]/div[1]/div/ul/li[2]/a"))
				.click();
		String[] expectedValue = STATUS.split("##");
		try {
			for (int i = 0; i <= 1; i++) {
				selectFromDate();
				selectToDate();
				webdriver.findElement(By.id("statusSelected")).sendKeys(
						expectedValue[i]);
				webdriver
						.findElement(
								By.xpath("html/body/div[1]/div[4]/div[3]/div[2]/form/table/tbody/tr/td[5]/input"))
						.click();
				if (i == 0) {
					actualValue = webdriver
							.findElement(
									By.xpath(".//*[@id='pahistorytable']/tbody/tr[1]/td[7]"))
							.getText();
				} else {
					actualValue = webdriver
							.findElement(
									By.xpath(".//*[@id='pahistorytable']/tbody/tr[1]/td[7]"))
							.getText();
				}
				if (!expectedValue[i].equalsIgnoreCase(actualValue)) {
					assertFail(STATUS);
				}
			}
		} catch (Exception ex) {
			webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			assertFail("Not able to find actual Value");
			ex.printStackTrace();

		}
	}

	@Test
	public void testDealerDetailsManage() {
		// Search for dealer details(manage tab)
		demo.initializeTestProps("RequestPA_DealerManage");
		priorApproval_Login();
		try {
		webdriver.findElement(
				By.xpath("html/body/div[1]/div[4]/div[1]/div/ul/li[2]/a"))
				.click();
		webdriver.findElement(
				By.xpath(".//*[@id='pahistorytable']/tbody/tr[1]/td[7]"))
				.click();
		webdriver.findElement(
				By.xpath(".//*[@id='pahistorytable']/tbody/tr[1]/td[9]/a"))
				.click();
		webdriver.findElement(By.id("file")).sendKeys(IMAGE);
		webdriver
				.findElement(
						By.xpath(".//*[@id='priorApprovalUpload']/div[1]/div[2]/table/tbody/tr[12]/td[2]/div/input"))
				.click();
		webdriver.findElement(By.id("submitButton")).click();
		} catch(Exception ex)
		{
			ex.printStackTrace();
			assertFail("Element Not Found");
		}
		webdriver.findElement(
				By.xpath(".//*[@id='pahistorytable']/tbody/tr[1]/td[9]/a"))
				.click();
		// Go there and manually check whether file has been uploaded
		// successfully (.png was added-->message)
		assertPass();
	}
}