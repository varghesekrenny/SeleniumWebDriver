package com.sigma.demo.TestCases;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import com.sigma.demo.Constants.ApplicationUtils;
import com.sigma.demo.Constants.DataConstants;
import com.sigma.demo.FrameWork.Utils.DemoStartUp;

public class TrainingCenterTest extends ApplicationUtils {

	protected static DemoStartUp demo = new DemoStartUp();

	static {
		demo.getTestSheetMapping(3);
	}

	// Going directly Training Center Tab
	@Test
	public void testPriorApprovalTrainingCenterTab() {

		demo.initializeTestProps("TrainingCenterTabPage");
		String [] expectedValue = EXPECTEDRESULT.split("##");
		webdriver.get(DataConstants.BASE_URL);
		webdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		loginConnectPlusApplication(USER_NAME, PASS_WORD);
		webdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		webdriver.findElement(By.xpath("/html/body/div/div[4]/a[3]")).click();
		webdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String actualValue0 = webdriver.findElement(By.xpath("html/body/div[1]/div[5]/div[1]/div/ul/li[1]/a/label")).getText();
		String actualValue1 = webdriver.findElement(By.xpath("html/body/div[1]/div[5]/div[1]/div/ul/li[2]/a/label")).getText();
		String actualValue2 = webdriver.findElement(By.xpath("html/body/div[1]/div[5]/div[1]/div/ul/li[3]/a/label")).getText();
		
		String actualTraining = webdriver.findElement(
				By.xpath("/html/body/div/div[5]/div/h1")).getText();
		// String expTraining = "TRAINING CENTER";
		if (!expectedValue[0].equals(actualTraining)) {
			String msg = "Actual result does not match with expected Training center result";
			assertFail(msg);
		}
		else if(!expectedValue[1].equalsIgnoreCase(actualValue0))
		{
			assertFail(expectedValue[1]+"doesnot matches");
		}
		else if(!expectedValue[2].equalsIgnoreCase(actualValue1))
		{
			assertFail(expectedValue[2] + "doesnot matches");
		}
		else if(!expectedValue[3].equalsIgnoreCase(actualValue2))
		{
			assertFail(expectedValue[3] + "doesnot matches");
		}
		webdriver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		assertPass();
	}
	@Test
	public void testTrainingCenterVideos() throws Exception {
		demo.initializeTestProps("TrainingCenterVideos");
		String[] expectedValue = EXPECTEDRESULT.split("##");
		
		testAdmin(expectedValue[0]);
		webdriver.findElement(By.xpath(DataConstants.uploadButtonXpath))
				.click();
		webdriver.findElement(
				By.xpath("html/body/div[1]/div[4]/div[1]/div/ul/li[2]/a"))
				.click();
		dropDownTrainingCentervideo(expectedValue[1]);
		String value = webdriver.findElement(By.xpath("/html/body/div/div[4]/div[2]/div/div[3]/div/div/table/tbody/tr/td[2]")).getText();
		if(!FILETITLE.equalsIgnoreCase(value))
		{
			assertFail(FILETITLE + " doesn't match");
		}
		goingTrainigCenterTab();
		String trainingCenterTabValue = webdriver.findElement(By.xpath(".//*[@id='video']/div/ul[1]/li/label/a")).getText();
		if(!FILETITLE.equalsIgnoreCase(trainingCenterTabValue))
		{
			assertFail(FILETITLE + "\t " +"not on the top");
		}
		assertPass();
		// Thread.sleep(2000);

	}
    // Checking Webinars with videos and other file(Zero bytes) 
	@Test
	public void testTrainingCenterWebinars() throws Exception {
		demo.initializeTestProps("TrainingCenterWebinars");
		String[] expectedValue = EXPECTEDRESULT.split("##");
		String[] uploadFiles = UPLOADFILES.split("##");
		
		testAdmin(expectedValue[0]);
		dropDownTrainingCenterWebinars(expectedValue[1], expectedValue[2], uploadFiles);
		goingTrainigCenterTab();
		String trainingCenterTabValue = webdriver.findElement(By.xpath(".//*[@id='webinar']/div/ul/li[1]/label/a")).getText();
		if(!FILETITLE.equalsIgnoreCase(trainingCenterTabValue))
		{
			assertFail(FILETITLE + "\t " +"not on the top");
		}
		assertPass();
	}
	
    //Checking  Downloads option is working properly 
	@Test
	public void testTrainingCenterDownloads() throws Exception {
		demo.initializeTestProps("TrainingCenterDownloads");
		String[] expectedValue = EXPECTEDRESULT.split("##");
		
		testAdmin(expectedValue[0]);
		webdriver.findElement(By.xpath(DataConstants.uploadButtonXpath))
				.click();
		webdriver.findElement(
				By.xpath("html/body/div[1]/div[4]/div[1]/div/ul/li[2]/a"))
				.click();
		dropDownTrainingCenterDownloads(expectedValue[1], expectedValue[2]);
		String value = webdriver.findElement(By.xpath("/html/body/div/div[4]/div[2]/div/div[3]/div/div/table/tbody/tr/td[2]")).getText();
		if(!FILETITLE.equalsIgnoreCase(value))
		{
			assertFail(FILETITLE + " doesn't match");
		}
		goingTrainigCenterTab();
		String trainingCenterTabValue = webdriver.findElement(By.xpath(".//*[@id='download']/div/ul/li[1]/label/a")).getText();
		if(!FILETITLE.equalsIgnoreCase(trainingCenterTabValue))
		{
			assertFail(FILETITLE + "\t " +"not on the top");
		}
		assertPass();
	}
	
	// Checking Popups for Changing filegroupName
	@Test
	public void testChangeFileGroupSubName() throws Exception
	{
		demo.initializeTestProps("TrainingCentersubGroupChange");
		String[] expectedValue = EXPECTEDRESULT.split("##");
		
		testAdmin(expectedValue[0]);
		webdriver.findElement(By.xpath(DataConstants.uploadButtonXpath)).click();
		webdriver.findElement(By.xpath("html/body/div[1]/div[4]/div[1]/div/ul/li[2]/a")).click();
		webdriver.findElement(By.xpath(".//*[@id='subgroup']/td[2]/a/u")).click();
		Alert alert = webdriver.switchTo().alert();
		alert.accept();
		try 
		{
		for(int i =1; i<=4; i++)
		{
			if(expectedValue[i].equalsIgnoreCase("BLANK"))
			{
				expectedValue[i] = " ";
			}
	//	webdriver.findElement(By.id("fileSubGroup")).sendKeys("Webinars");
		webdriver.findElement(By.xpath(DataConstants.dropdownFileManagement+ "/option[3]")).click();
		webdriver.findElement(By.xpath("/html/body/div/div[4]/div[2]/div[3]/form/table/tbody/tr/td[2]/a/u")).click();
		webdriver.findElement(By.id("newSubGroupName")).sendKeys(expectedValue[i]);
		webdriver.findElement(By.xpath(".//*[@id='login-box']/form/div/table/tbody/tr[3]/td/input")).click();
		String Value = webdriver.findElement(By.xpath(DataConstants.dropdownFileManagement+ "/option[3]")).getText();
		if(!expectedValue[i].equals(Value))
		{
			assertFail(expectedValue[i] +"\t" + "doesnot match");
		}
		}
		} catch(Exception ex)
		{
			ex.printStackTrace();
			assertFail("No element found");
		}
		assertPass();
		
	}
	
	
}