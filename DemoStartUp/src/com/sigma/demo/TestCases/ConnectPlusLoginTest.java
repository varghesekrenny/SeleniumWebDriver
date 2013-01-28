package com.sigma.demo.TestCases;

import org.junit.Test;
import org.openqa.selenium.By;

import com.sigma.demo.Constants.ApplicationUtils;
import com.sigma.demo.FrameWork.Utils.DemoStartUp;

public class ConnectPlusLoginTest extends ApplicationUtils {

	protected static DemoStartUp demo = new DemoStartUp();
	
	static{
		demo.getTestSheetMapping(0);
	}
	@Test
	public void testValidLogin() {
		demo.initializeTestProps("LoginConnectPlus");
		String[] userName = USER_NAME.split("##");
		String[] passWord = PASS_WORD.split("##");
		
		for(int i=0; i<2; i++)
		{
		loginConnectPlusApplication(userName[i], passWord[i]);
		String actualElement= webdriver.findElement(By.xpath("/html/body/div/div[2]/div/h2")).getText();
		if(!EXPECTEDRESULT.equals(actualElement)){
			String msg= "Actual result does not match with expected Login result";
			assertFail(msg);
		}
		webdriver.findElement(By.xpath("html/body/div[1]/div[1]/ul/li[2]/a")).click();
		}
		assertPass();
      }
	@Test
	public void testInvalidLoginWithOutUser()
	{
		demo.initializeTestProps("InvalidLoginWithOutUserID");
		USER_NAME = null;
		loginConnectPlusApplication(USER_NAME, PASS_WORD);
		
		String actualElement= webdriver.findElement(By.xpath(".//*[@id='forgotPasswordRow']/td[2]/a")).getText();
		if(!EXPECTEDRESULT.equalsIgnoreCase(actualElement)){
			String msg= "Actual result does not match with expected Login result";
			assertFail(msg);
		}
		assertPass();
	}
	@Test
	public void testInvalidLoginWithOutPassword()
	{
		demo.initializeTestProps("InvalidLoginWithOutPWD");
		PASS_WORD = null;
		String[] userName = USER_NAME.split("##");
		for(int i=0; i<2; i++)
		{
		loginConnectPlusApplication(userName[i], PASS_WORD);
		String actualElement= webdriver.findElement(By.xpath(".//*[@id='forgotPasswordRow']/td[2]/a")).getText();
	//	System.out.println(actualElement);
		if(!EXPECTEDRESULT.equalsIgnoreCase(actualElement)){
			String msg= "Actual result does not match with expected Login result";
			assertFail(msg);
		}
		}
		assertPass();
	}
	@Test
	public void testInvalidLogin()
	{
		demo.initializeTestProps("InvalidLogin");
		USER_NAME = null;
		PASS_WORD = null;
		
		loginConnectPlusApplication(USER_NAME, PASS_WORD);
		String actualElement= webdriver.findElement(By.xpath(".//*[@id='forgotPasswordRow']/td[2]/a")).getText();
		if(!EXPECTEDRESULT.equalsIgnoreCase(actualElement)){
			String msg= "Actual result does not match with expected Login result";
			assertFail(msg);
			
		}
		assertPass();
	}
}
