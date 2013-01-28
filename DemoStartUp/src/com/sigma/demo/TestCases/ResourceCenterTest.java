package com.sigma.demo.TestCases;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import com.sigma.demo.Constants.ApplicationUtils;
import com.sigma.demo.Constants.DataConstants;
import com.sigma.demo.FrameWork.Utils.DemoStartUp;

public class ResourceCenterTest extends ApplicationUtils {

	protected static DemoStartUp demo = new DemoStartUp();

	static {
		demo.getTestSheetMapping(4);
	}

	@Test
	public void testPriorApprovalTrainingCenter() {

		demo.initializeTestProps("PriorApprovalResourceCenter");
		webdriver.get(DataConstants.BASE_URL);
		loginConnectPlusApplication(USER_NAME, PASS_WORD);
		webdriver.findElement(By.xpath("/html/body/div/div[4]/a[5]")).click();
		String actualPolicy = webdriver.findElement(
				By.xpath("/html/body/div/div[4]/div/h1")).getText();
		// String expectedPolicy = "RESOURCE CENTER";
		assertEquals(EXPECTEDRESULT, actualPolicy);
		if (!EXPECTEDRESULT.equals(actualPolicy)) {
			String msg = "Actual result does not match with expected resource center result";
			assertFail(msg);
		}
		webdriver.findElement(
				By.xpath("/html/body/div/div[4]/div[2]/div[2]/div/span/a/img"))
				.click();
		assertPass();
	}

	@Test
	public void testPriorApprovalSubmitAllValues() {
		demo.initializeTestProps("PA_ResourceCenterSubmitAll");
		String[] expectedValue = EXPECTEDRESULT.split("##");
		testAdmin(expectedValue[0]);
		webdriver.findElement(By.xpath(DataConstants.UploadFile)).click();
		webdriver.findElement(
				By.xpath("html/body/div[1]/div[4]/div[1]/div/ul/li[3]/a"))
				.click();
		resourceCentreValue();
		webdriver.findElement(By.id("saveButton")).click();
		String actualValue = webdriver.findElement(
				By.xpath("html/body/div[1]/div[4]/div[2]/div/div[2]"))
				.getText();
		if (!expectedValue[1].equalsIgnoreCase(actualValue)) {
			assertFail(EXPECTEDRESULT + "doesnot get matches");
		}
		String fileTitleValue = webdriver
				.findElement(
						By.xpath("/html/body/div/div[4]/div[2]/div/div[3]/div/div/table/tbody/tr/td[2]"))
				.getText();
		if (!FILETITLE.equalsIgnoreCase(fileTitleValue)) {
			assertFail(FILETITLE + "\t" + "doesnot get match");
		}
		webdriver.findElement(By.xpath("html/body/div[1]/div[3]/a[2]")).click();
		webdriver.findElement(By.xpath("html/body/div[1]/div[4]/a[5]/h1/img"))
				.click();
		String resourceText = webdriver.findElement(
				By.xpath("/html/body/div/div[4]/div/h1")).getText();
		if (!expectedValue[2].equalsIgnoreCase(resourceText)) {
			assertFail(EXPECTEDRESULT + "\t" + "doesnot get matches");
		}
		webdriver.findElement(
				By.xpath("/html/body/div/div[4]/div[2]/div[2]/div/span/a/img"))
				.click();
		String fileTitlePopValue = webdriver.findElement(By.id("popupTitle"))
				.getText();
		if (!FILETITLE.equalsIgnoreCase(fileTitlePopValue)) {
			assertFail(FILETITLE + "\t" + "doesnot get matches");
		}
		String fileDescription = webdriver.findElement(
				By.id("popupDescritpion")).getText();
		if (!FILEDESCRIPTION.equalsIgnoreCase(fileDescription)) {
			assertFail(EXPECTEDRESULT + "\t" + "doesnot get matches");
		}
		String actualUrl = webdriver
				.findElement(
						By.xpath("/html/body/div/div[4]/div[2]/div[3]/table/tbody/tr/td[3]/table/tbody/tr[3]/td/a/u"))
				.getText();
		if (!URL.equalsIgnoreCase(actualUrl)) {
			assertFail(URL + "\t" + "doesnot get matches");
		}
		assertPass();
	}

	@Test
	public void testPriorApprovalCancelAll() {
		demo.initializeTestProps("PA_ResourceCenterCancelAll");
		String[] expectedResult = EXPECTEDRESULT.split("##");
		testAdmin(expectedResult[0]);
		webdriver.findElement(By.xpath(DataConstants.UploadFile)).click();
		webdriver.findElement(
				By.xpath("html/body/div[1]/div[4]/div[1]/div/ul/li[3]/a"))
				.click();
		resourceCentreValue();
		webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		webdriver.findElement(By.xpath(".//*[@id='buttonRow']/td[2]/div/a"))
				.click();
		Alert alert = webdriver.switchTo().alert();
		String alertValue = alert.getText();
		if (!expectedResult[1].equalsIgnoreCase(alertValue)) {
			assertFail(expectedResult[1] + "\t " + "doesnot get match ");
		}
		alert.accept();
		assertPass();
	}

	@Test
	public void testPriorApprovalSubmitWithOutValues() {
		demo.initializeTestProps("PA_ResourceCenterSubmitWithoutVal");
		String[] expectedResult = EXPECTEDRESULT.split("##");
		testAdmin(expectedResult[0]);
		webdriver.findElement(By.xpath(DataConstants.UploadFile)).click();
		webdriver.findElement(
				By.xpath("html/body/div[1]/div[4]/div[1]/div/ul/li[3]/a"))
				.click();
		resourceCentreValue();
		webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		webdriver.findElement(By.id("saveButton")).click();
		String actualValue1 = webdriver.findElement(By.id("errorDiv1"))
				.getText();
		String actualValue2 = webdriver.findElement(By.id("errorDiv6"))
				.getText();
		String actualValue3 = webdriver.findElement(By.id("errorDiv4"))
				.getText();
		String actualValue4 = webdriver.findElement(By.id("errorDiv3"))
				.getText();
		if (!expectedResult[1].equalsIgnoreCase(actualValue1)) {
			assertFail(expectedResult[1] + "\t" + " doesnot matches");
		}
		if (!expectedResult[2].equalsIgnoreCase(actualValue2)) {
			assertFail(expectedResult[2] + "\t" + " doesnot matches");
		}
		if (!expectedResult[3].equalsIgnoreCase(actualValue3)) {
			assertFail(expectedResult[3] + "\t" + " doesnot matches");
		}
		if (!expectedResult[4].equalsIgnoreCase(actualValue4)) {
			assertFail(expectedResult[4] + "\t" + " doesnot matches");
		}
	}

	@Test
	public void testPriorApprovalCancelWithOutValues() {
		demo.initializeTestProps("PA_ResourceCenterCancelWithoutVal");
		String[] expectedResult = EXPECTEDRESULT.split("##");
		testAdmin(expectedResult[0]);
		webdriver.findElement(By.xpath(DataConstants.UploadFile)).click();
		webdriver.findElement(
				By.xpath("html/body/div[1]/div[4]/div[1]/div/ul/li[3]/a"))
				.click();
		resourceCentreValue();
		webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		webdriver.findElement(By.xpath(".//*[@id='buttonRow']/td[2]/div/a"))
				.click();
		Alert alert = webdriver.switchTo().alert();
		String alertValue = alert.getText();
		if (!expectedResult[1].equalsIgnoreCase(alertValue)) {
			assertFail(expectedResult[1] + "\t " + "doesnot get match");
		}
		alert.accept();
		assertPass();
	}
	@Test
	public void testPriorApprovalDealerUserCheck()
	{
		demo.initializeTestProps("PA_ResourceCenterDealerTab");
		webdriver.get(DataConstants.BASE_URL);
		loginConnectPlusApplication(USER_NAME, PASS_WORD);
		webdriver.findElement(By.xpath("/html/body/div/div[4]/a[5]")).click();
		String actualPolicy = webdriver.findElement(By.xpath("/html/body/div/div[4]/div/h1")).getText();
		if(!EXPECTEDRESULT.equalsIgnoreCase(actualPolicy))
		{
			assertFail(EXPECTEDRESULT + "\t" + "doesnot get matches");
		}
		assertPass();
	}
	
}