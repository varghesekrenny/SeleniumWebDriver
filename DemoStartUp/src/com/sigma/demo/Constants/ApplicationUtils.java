package com.sigma.demo.Constants;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.sigma.demo.FrameWork.Utils.DemoStartUp;

public class ApplicationUtils extends DemoStartUp {
	public void testAdmin(String expectedValue) {

		try {
			loginConnectPlusApplication(USER_NAME, PASS_WORD);
			String actualElement = webdriver.findElement(
					By.xpath("/html/body/div/div[2]/div/h2")).getText();
			if (!expectedValue.equals(actualElement)) {
				String msg = "Actual result does not match with expected login result";
				assertFail(msg);
			}
			webdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			webdriver.findElement(By.xpath(".//*[@id='admin']")).click();
			webdriver.findElement(
					By.xpath(DataConstants.adminXpath + "/li[4]/a")).click();

			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void assignNewUpdateDefault()
    {
        webdriver.findElement(By.xpath(".//*[@id='newsTitle']")).clear();
		webdriver.findElement(By.xpath(DataConstants.welcomeNews + "/form/div[3]/table/tbody/tr[2]/td[2]/div[2]/div")).clear();
		webdriver.findElement(By.xpath(".//*[@id='url']")).clear();
    }
    
	
	public void assignDefaultWelcomePage() {
		webdriver.findElement(By.id("welcomeTitle")).clear();
		webdriver.findElement(By.xpath("/html/body/div/div[4]/div[2]/form/div[3]/table/tbody/tr[2]/td[2]/div[2]/div")).clear();
		webdriver.findElement(By.id("welcomeURL")).clear();
	}

	public void testWelcome() {

		try {
			loginConnectPlusApplication(USER_NAME, PASS_WORD);

			webdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			webdriver.findElement(By.xpath(".//*[@id='admin']")).click();
			webdriver.findElement(By.xpath(".//*[@id='popup']/div/ul/li[2]/a"))
					.click();

			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void testPolicyGuideLineClear() {
		try {
			webdriver.findElement(By.id("fileTitle")).clear();
			webdriver.findElement(By.id("dealerDescription")).clear();
			webdriver.findElement(By.id("url")).clear();
		} catch (NoSuchElementException ext) {
			System.out.println("Throwing no element");
		}
		// webdriver.findElement(By.id("thumbnail")).clear();
	}

	public void dropDownFileGroupPolicy(String expectedValue) {
		// clicking on SEARCH BUTTON
		webdriver
				.findElement(
						By.xpath("/html/body/div/div[4]/div[2]/div/div[2]/form/table/tbody/tr/td[5]/button"))
				.click();
		String actualPolicy = webdriver
				.findElement(
						By.xpath("/html/body/div/div[4]/div[2]/div/div[2]/div/div/table/tbody/tr/td[3]"))
				.getText();
		if (!expectedValue.equals(actualPolicy)) {
			String msg = "Actual result does not match with expected policy guidelines result";
			assertFail(msg);

		}
	}

	public void goToFileMangementModule() {
		webdriver.findElement(By.id("admin")).click();
		webdriver.findElement(By.xpath(".//*[@id='popup']/div/ul/li[4]/a"))
				.click();
		webdriver
				.findElement(
						By.xpath("html/body/div[1]/div[4]/div[2]/div/div[1]/form/button"))
				.click();
	}

	public void dropDownFileGroupTraining(String expectedValue) {
		// clicking on SEARCH BUTTON
		webdriver
				.findElement(
						By.xpath("/html/body/div/div[4]/div[2]/div/div[2]/form/table/tbody/tr/td[5]/button"))
				.click();
		String actualTraining = webdriver
				.findElement(
						By.xpath("/html/body/div/div[4]/div[2]/div/div[2]/div/div/table/tbody/tr/td[3]"))
				.getText();
		if (!expectedValue.equals(actualTraining)) {
			String msg = "Actual result does not match with expected Training center result";
			assertFail(msg);

		}

	}

	public void dropDownFileGroupResource(String expectedValue) {
		// clicking on SEARCH BUTTON
		webdriver
				.findElement(
						By.xpath("/html/body/div/div[4]/div[2]/div/div[2]/form/table/tbody/tr/td[5]/button"))
				.click();
		String actualResource = webdriver
				.findElement(
						By.xpath("/html/body/div/div[4]/div[2]/div/div[2]/div/div/table/tbody/tr/td[3]"))
				.getText();
		if (!expectedValue.equals(actualResource)) {
			String msg = "Actual result does not match with expected Resource center result";
			assertFail(msg);

		}
		webdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	public void dropDownFilePolicy(String expectedValue1,
			String expectedValue2, String expectedValue3,
			String expectedValue4, String expectedValue5) {
		// click on submit
		webdriver.findElement(By.id("saveButton")).click();
		String actualFileTitle = webdriver.findElement(By.id("errorDiv2"))
				.getText();
		if (!expectedValue1.equals(actualFileTitle)) {
			String msg = "Actual result does not match with expected FileTitle result";
			assertFail(msg);
		}
		String actualDescription = webdriver.findElement(By.id("errorDiv3"))
				.getText();
		if (!expectedValue2.equals(actualDescription)) {
			String msg = "Actual result does not match with expected Description result";
			assertFail(msg);

		}

		String actualThumb = webdriver.findElement(By.id("errorDiv6"))
				.getText();
		if (!expectedValue3.equals(actualThumb)) {
			String msg = "Actual result does not match with expected Thumbnail result";
			assertFail(msg);

		}
		String actualUploadFile = webdriver.findElement(By.id("errorDiv8"))
				.getText();

		if (!expectedValue4.equals(actualUploadFile)) {
			String msg = "Actual result does not match with expected Upload file result";
			assertFail(msg);

		}
		// clicking on cancel button
		webdriver.findElement(By.xpath(".//*[@id='buttonRow']/td[2]/div/a"))
				.click();
		Alert alertDismis = webdriver.switchTo().alert();
		alertDismis.accept();
		webdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String actualValue = webdriver.findElement(
				By.xpath("/html/body/div/div[4]/div/h1")).getText();

	}

	public void dropDownPolicyGuideLines(String expectedValue) {
		CommonUtils.registrationMailGenerate();

		webdriver.findElement(By.id("fileTitle")).sendKeys(
				CommonUtils.actualLoginId);
		webdriver.findElement(By.id("dealerDescription")).sendKeys(
				CommonUtils.actualLoginId);
		webdriver.findElement(By.id("url")).sendKeys(DemoStartUp.URL);
		webdriver.findElement(By.id("thumbnail")).sendKeys(
				DemoStartUp.THUMBNAIL);
		webdriver.findElement(By.id("submitThumbnail")).click();
		webdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		webdriver.findElement(By.id("saveButton")).click();
		String FileUpload = webdriver.findElement(
				By.xpath("/html/body/div/div[4]/div[2]/div/div[2]")).getText();
		if (!expectedValue.equals(FileUpload)) {
			String msg = "Actual result does not match with expected Upload file result";
			assertFail(msg);

		}
		webdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	// used in policy guidelies
	public void policyGuidelines(String expectedValue1, String expectedValue2) {

		CommonUtils.registrationMailGenerate();
		webdriver.findElement(By.id("fileTitle")).sendKeys(
				CommonUtils.actualLoginId);
		webdriver.findElement(By.id("dealerDescription")).sendKeys(
				CommonUtils.actualLoginId);
		webdriver.findElement(By.id("thumbnail")).sendKeys(
				DemoStartUp.THUMBNAIL);
		webdriver.findElement(By.id("submitThumbnail")).click();
		webdriver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		webdriver.findElement(By.id("file")).sendKeys(DemoStartUp.UPLOADFILES);
		webdriver.findElement(By.id("submitFile")).click();
		webdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		webdriver.findElement(By.id("saveButton")).click();
		System.out.println("NOW AT EOD OF POLLICY GUIDELINES ----- ");
		String FileUpload = webdriver.findElement(
				By.xpath("/html/body/div/div[4]/div[2]/div/div[2]")).getText();
		System.out.println("FileUpload-0----" + FileUpload);
		// String ExpFileUpload = "Files uploaded successfully";
		// assertEquals(expectedValue1, FileUpload);
		if (!expectedValue1.equals(FileUpload)) {
			String msg = "Actual result does not match with expected Upload file result";
			assertFail(msg);

		}
		String actualPolicy = webdriver.findElement(
				By.xpath(DataConstants.filemanagementPage_Xpath)).getText();
		// String expectedPolicy = "Policy & Guidelines";
		// assertEquals(expectedValue2, actualPolicy);
		if (!expectedValue2.equals(actualPolicy)) {
			String msg = "Actual result does not match with expected policy guidelines result";
			assertFail(msg);

		}
		// System.out.println("NOW AT EOD OF POLLICY GUIDELINES");
		webdriver
				.findElement(
						By.xpath("html/body/div[1]/div[4]/div[2]/div/div[1]/form/button"))
				.click();
		webdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void goingTrainigCenterTab() {
		webdriver.findElement(By.xpath("html/body/div[1]/div[3]/a[2]")).click();
		webdriver.findElement(By.xpath("html/body/div[1]/div[4]/a[3]/h1/img"))
				.click();
	}

	// used in training center
	public void dropDownTrainingCentervideo(String expectedValue) {

		webdriver.findElement(
				By.xpath(DataConstants.dropdownFileManagement + "/option[2]"))
				.click();
		webdriver.findElement(By.id("fileTitle")).sendKeys(
				DemoStartUp.FILETITLE);
		webdriver.findElement(By.id("dealerDescription")).sendKeys(
				DemoStartUp.FILEDESCRIPTION);
		WebElement fileupload = webdriver.findElement(By.id("theFile"));
		fileupload.sendKeys(DemoStartUp.UPLOADFILES);
		webdriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		webdriver.findElement(By.id("submitVideo")).click();
		String actualvideos = webdriver.findElement(
				By.xpath("html/body/div[1]/div[4]/div[2]/div/div[2]"))
				.getText();
		if (!expectedValue.equalsIgnoreCase(actualvideos)) {
			assertFail(expectedValue + "doesn't matches");
		}
		webdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void dropDownTrainingCenterWebinars(String expectedValue1,
			String expectedValue2, String[] uploadFiles) {
		String actualVideos;
		for (int i = 0; i < 2; i++) {
			webdriver.findElement(By.xpath(DataConstants.uploadButtonXpath))
					.click();
			webdriver.findElement(
					By.xpath("html/body/div[1]/div[4]/div[1]/div/ul/li[2]/a"))
					.click();
			webdriver.findElement(
					By.xpath(DataConstants.dropdownFileManagement
							+ "/option[3]")).click();
			if (i == 0) {
				webdriver
						.findElement(
								By.xpath("/html/body/div/div[4]/div[2]/div[3]/form/table/tbody/tr[2]/td[2]/select/option[2]"))
						.click();
			} else {
				webdriver
						.findElement(
								By.xpath("/html/body/div/div[4]/div[2]/div[3]/form/table/tbody/tr[2]/td[2]/select/option[3]"))
						.click();
			}
			webdriver.findElement(By.id("fileTitle")).sendKeys(FILETITLE);
			webdriver.findElement(By.id("dealerDescription")).sendKeys(
					FILEDESCRIPTION);
			if (i == 0) {
				webdriver.findElement(By.id("theFile"))
						.sendKeys(uploadFiles[i]);
				webdriver.manage().timeouts()
						.implicitlyWait(30, TimeUnit.SECONDS);
				webdriver.findElement(By.id("submitVideo")).click();
				actualVideos = webdriver.findElement(
						By.xpath("/html/body/div/div[4]/div[2]/div/div[2]"))
						.getText();
				if (!expectedValue1.equals(actualVideos)) {
					assertFail(expectedValue1 + "doesnot get match");
				}
				webdriver.manage().timeouts()
						.implicitlyWait(50, TimeUnit.SECONDS);
			} else {
				webdriver.findElement(By.id("fileTraining")).sendKeys(
						uploadFiles[i]);
				webdriver.manage().timeouts()
						.implicitlyWait(30, TimeUnit.SECONDS);
				webdriver.findElement(
						By.xpath(".//*[@id='saveButtontraining']")).click();
				actualVideos = webdriver.findElement(
						By.xpath("html/body/div[1]/div[4]/div[2]/div/div[2]"))
						.getText();
				if (!expectedValue2.equalsIgnoreCase(actualVideos)) {
					assertFail(expectedValue2 + "doesnot get match");
				}
				webdriver.manage().timeouts()
						.implicitlyWait(50, TimeUnit.SECONDS);
			}
			webdriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		}
	}

	public void dropDownTrainingCenterDownloads(String expectedValue1,
			String expectedValue2) {
		webdriver.findElement(
				By.xpath(DataConstants.dropdownFileManagement + "/option[4]"))
				.click();
		webdriver.findElement(By.id("fileTitle")).sendKeys(
				DemoStartUp.FILETITLE);
		webdriver.findElement(By.id("dealerDescription")).sendKeys(
				DemoStartUp.FILEDESCRIPTION);
		webdriver.findElement(By.id("fileTraining")).sendKeys(
				DemoStartUp.UPLOADFILES);
		webdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		webdriver.findElement(By.id("saveButtontraining")).click();
		String actualvideos = webdriver.findElement(
				By.xpath("/html/body/div/div[4]/div[2]/div/div[2]")).getText();
		if (!expectedValue1.equals(actualvideos)) {
			String msg = "Actual result does not match with expected Videos result";
			assertFail(msg);

		}
		String actualdownload = webdriver.findElement(
				By.xpath(DataConstants.filemanagementPage_Xpath)).getText();
		webdriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

	}

	public void resourceCenter(String expectedValue1, String expectedValue2) {
		webdriver.findElement(By.id("fileTitle")).sendKeys(
				DemoStartUp.FILETITLE);
		webdriver.findElement(By.id("dealerDescription")).sendKeys(
				DemoStartUp.FILEDESCRIPTION);
		webdriver.findElement(By.id("url")).sendKeys(DemoStartUp.URL);
		webdriver.findElement(By.id("thumbnail")).sendKeys(
				DemoStartUp.THUMBNAIL);
		webdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		webdriver.findElement(By.id("saveButton")).click();
		String actualvideos = webdriver.findElement(
				By.xpath("/html/body/div/div[4]/div[2]/div/div[2]")).getText();
		if (!expectedValue1.equals(actualvideos)) {
			String msg = "Actual result does not match with expected Videos result";
			assertFail(msg);

		}
		String actualResource = webdriver.findElement(
				By.xpath(DataConstants.filemanagementPage_Xpath)).getText();
		if (!expectedValue2.equals(actualResource)) {
			String msg = "Actual result does not match with expected resource result";
			assertFail(msg);

		}
		webdriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

	}

	public void submitApprovalContactName(String contactPhone, String url) {
		webdriver.findElement(By.xpath(".//*[@id='dealerName_Id']/option[2]"))
				.click();
		webdriver.findElement(By.id("contactName")).sendKeys(" ");
		webdriver.findElement(By.id("contactEmail")).sendKeys(
				CommonUtils.regExpMail);
		webdriver.findElement(By.id("contactPhone")).sendKeys(contactPhone);
		webdriver.findElement(By.id("url")).sendKeys(url);
		webdriver.findElement(By.xpath(".//*[@id='mediaType']/option[2]"))
				.click();
	}

	public void submitApprovalContactEmail(String contactPhone, String url) {
		webdriver.findElement(By.xpath(".//*[@id='dealerName_Id']/option[2]"))
				.click();
		webdriver.findElement(By.id("contactName")).sendKeys(
				CommonUtils.actualLoginId);
		webdriver.findElement(By.id("contactEmail")).sendKeys(
				"vargh..vargh@gmail.com");
		webdriver.findElement(By.id("contactPhone")).sendKeys(contactPhone);
		webdriver.findElement(By.id("url")).sendKeys(url);
		webdriver.findElement(By.xpath(".//*[@id='mediaType']/option[2]"))
				.click();
	}

	public void submitApprovalDetails(String contactPhone, String url) {
		webdriver.findElement(By.xpath(".//*[@id='dealerName_Id']/option[2]"))
				.click();
		webdriver.findElement(By.id("contactName")).sendKeys(
				CommonUtils.actualLoginId);
		webdriver.findElement(By.id("contactEmail")).sendKeys(
				CommonUtils.regExpMail);
		webdriver.findElement(By.id("contactPhone")).sendKeys(contactPhone);
		webdriver.findElement(By.id("url")).sendKeys(url);
		webdriver.findElement(By.xpath(".//*[@id='mediaType']/option[2]"))
				.click();
	}

	public void assignDefaultTestValue() {
		webdriver.findElement(By.id("contactName")).clear();
		webdriver.findElement(By.id("contactEmail")).clear();
		webdriver.findElement(By.id("contactPhone")).clear();
		webdriver.findElement(By.id("url")).clear();
		webdriver.findElement(By.id("datepicker")).clear();
		webdriver.findElement(By.id("datepicker1")).clear();
		// webdriver.findElement(By.id("file")).clear();
	}

	public void uploadFile() {

		webdriver.findElement(By.id("file")).sendKeys(DataConstants.PICTURE);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		webdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		webdriver.findElement(By.name("submit")).click();
	}

	public void selectFromDate() {

		webdriver.findElement(By.id("datepicker")).sendKeys(FROMDATE);
	}

	public void selectToDate() {
		webdriver.findElement(By.id("datepicker1")).sendKeys(TODATE);
	}

	public void submitButton() {
		webdriver.findElement(By.id("submitButton")).click();
		webdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void confirmationPageWithOutSubmit(String expectedValue) {
		String actualPage = webdriver.findElement(
				By.xpath("html/body/div[1]/div[4]/div[2]/div[2]/h2")).getText();
		if (!expectedValue.equals(actualPage)) {
			String msg = "Actual result does not match with expected confirmation prior approval result";
			assertFail(msg);
		}
	}

	public void confirmationPage(String expectedValue) {
		String actualPage = webdriver.findElement(
				By.xpath("html/body/div[1]/div[4]/div[2]/div[2]/h2")).getText();
		if (!expectedValue.equals(actualPage)) {
			String msg = "Actual result does not match with expected confirmation prior approval result";
			assertFail(msg);
		}
		webdriver.findElement(By.xpath(".//*[@id='submitBtn']")).click();
	}

	public void priorApproval_Login() {
		loginConnectPlusApplication(USER_NAME, PASS_WORD);
		CommonUtils.registrationMailGenerate();
		webdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// clicking on prior approval tab
		webdriver.findElement(By.xpath(DataConstants.PaXpath)).click();
		// checking conditions : when user click on submit approval tab
		webdriver.findElement(
				By.xpath("html/body/div[1]/div[4]/div[1]/div/ul/li[1]/a"))
				.click();
	}

	public void resourceCentreValue() {
		if (FILETITLE.equalsIgnoreCase("NIL")) {
			FILETITLE = "";
		}
		if (FILEDESCRIPTION.equalsIgnoreCase("NIL")) {
			FILEDESCRIPTION = "";
		}
		if (URL.equalsIgnoreCase("NIL")) {
			URL = "";
		}
		if (THUMBNAIL.equalsIgnoreCase("NIL")) {
			THUMBNAIL = "";
		}
		webdriver.findElement(By.id("fileTitle")).sendKeys(FILETITLE);
		webdriver.findElement(By.id("dealerDescription")).sendKeys(
				FILEDESCRIPTION);
		webdriver.findElement(By.id("url")).sendKeys(URL);
		webdriver.findElement(By.id("thumbnail")).sendKeys(THUMBNAIL);
	}
}
