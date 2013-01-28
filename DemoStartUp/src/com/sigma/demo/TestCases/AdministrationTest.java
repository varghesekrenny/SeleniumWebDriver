package com.sigma.demo.TestCases;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;

import com.sigma.demo.Constants.ApplicationUtils;
import com.sigma.demo.Constants.CommonUtils;
import com.sigma.demo.Constants.DataConstants;
import com.sigma.demo.FrameWork.Utils.DemoStartUp;

public class AdministrationTest extends ApplicationUtils {

	protected static DemoStartUp demo = new DemoStartUp();

	static {
		demo.getTestSheetMapping(5);
	}

	String items;

	@Test
	public void testAdminSearch() throws Exception {
		demo.initializeTestProps("AdminSearch");
		String[] expectedValue = EXPECTEDRESULT.split("##");
		
		testAdmin(expectedValue[0]);
		// File search based on File Group
		for (int i = 1; i <= 4; i++) {
			System.out.println("INSIDE FOR LOOP");
			items = webdriver.findElement(
					By.xpath(DataConstants.AdmindropdownSearchXpath
							+ "/option[" + i + "]")).getText();
			System.out.println("items" + items);
			if (("Policy & Guidelines").equalsIgnoreCase(items)) {
				System.out.println("inside app");
				webdriver.findElement(
						By.xpath(DataConstants.AdmindropdownSearchXpath
								+ "/option[" + i + "]")).click();
				dropDownFileGroupPolicy(expectedValue[1]);
				System.out.println("END OF THE APPROVE");
			} else if (items.equalsIgnoreCase("Training Center")) {
				System.out.println("inside Center");
				webdriver.findElement(
						By.xpath(DataConstants.AdmindropdownSearchXpath
								+ "/option[" + i + "]")).click();
				dropDownFileGroupTraining(expectedValue[2]);

			} else if (items.equalsIgnoreCase("Resource Center")) {
				System.out.println("inside Resource ");
				webdriver.findElement(
						By.xpath(DataConstants.AdmindropdownSearchXpath
								+ "/option[" + i + "]")).click();
				dropDownFileGroupResource(expectedValue[3]);
				String Reset = webdriver
						.findElement(
								By.xpath("html/body/div[1]/div[4]/div[2]/div/div[2]/form[2]/button"))
						.getText();
				System.out.println("Reset--" + Reset);
				// clicking on reset button
				webdriver
						.findElement(
								By.xpath("html/body/div[1]/div[4]/div[2]/div/div[2]/form[2]/button"))
						.click();
			}

		}
		assertPass();
	}

	@Test
	public void testTrainingCenterResource() throws Exception {
		demo.initializeTestProps("TrainingCenterResource");
		String[] expectedValue = EXPECTEDRESULT.split("##");
		
		testAdmin(expectedValue[0]);
		webdriver.findElement(By.xpath(DataConstants.uploadButtonXpath))
				.click();
		webdriver.findElement(
				By.xpath("/html/body/div/div[4]/div/div/ul/li[3]/a")).click();
		resourceCenter(expectedValue[1], expectedValue[2]);
		assertPass();
	}

}
