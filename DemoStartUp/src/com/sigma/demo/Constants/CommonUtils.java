package com.sigma.demo.Constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonUtils {

	public static String regExpMail;
	public static String actualLoginId;

	public static boolean VerifyTextPresent(WebDriver webdriver,
			String ExpectedValue) {
		WebElement elem = webdriver.findElement(By.id("gbgs4dn"));
		String ActualValue = elem.getText();
		if (ExpectedValue.equalsIgnoreCase(ActualValue)) {
			return true;
		}
		return false;
	}
	public static String getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DataConstants.DATE_FORMAT_NOW);
		return sdf.format(cal.getTime());
	}
	
	public static String concatinateWithDelimit(String source, String dest) {
		String errorMsg = source + DataConstants.ERRORMESSAGEDELIMIT + dest;
		return errorMsg;
	}

	public static String isNULL(String s) {
		return (s != null) ? s.trim() : "";
	}

	public static boolean isEmpty(String s) {
		return ((s == null) || (s.length() == 0)) ? true : false;
	}
	public static String getCurrentTime()
	{
		SimpleDateFormat sdf = new SimpleDateFormat(DataConstants.DATE_FORMAT_NOW);
		Date date = new Date();
	    String currentTime = sdf.format(date);
		return currentTime;
	}

	public static void registrationMailGenerate() {
		Random generatorName = new Random();
		actualLoginId = "";
		char[] storName = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
				'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
				'w', 'x', 'y', 'z' };
		for (int i = 0; i <= 5; i++) {
			int randomLoginId = generatorName.nextInt(26);
			actualLoginId = actualLoginId + storName[randomLoginId];
		}
		regExpMail = actualLoginId + "@gmail.com";
	}
}
