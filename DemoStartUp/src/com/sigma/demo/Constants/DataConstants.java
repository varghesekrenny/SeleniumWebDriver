package com.sigma.demo.Constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

public class DataConstants {

	public static final String fileName = "/home/sigma/SeleniumWS/DemoStartUp/config/datasheet.xls";
	public static final String DATE_FORMAT_NOW = "MM/dd/yyyy";
	public static final String randomValue = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"; 
	public static final String TestDataID = "TESTID";
	public static final String TESTCASEDESCRIPTION = "DESCRIPTION";
	public static final String TESTCASESEVERITY = "SEVERITY";
	public static final String fileDetails = "/home/sigma/SeleniumWS/DemoStartUp/results/reports/framedxml/data.xml";
	public static final int TESTSUITEROWNUMBER = 0;
	public static final int TESTSUITECELLNO = 0;
	public static final String TESTCASERESULT_SUCC = "SUCCESS";
	public static final String TESTCASERESULT_FAIL = "FAIL";
	public static final String ERRORMESSAGEDELIMIT = "##";
	public static final String ERROREXCEPTION = "Exception:";
	public static final String EXPECTEDVALUE = "varghese.k@sigmainfo.net";
	public static final String file = "/home/sigma/SeleniumWS/DemoStartUp/config/log4j.properties";
	public static final String SCREENSHOTFILE = "/home/sigma/SeleniumWS/DemoStartUp/results/screenshots/";
	public static final String BASE_URL = "http://192.168.4.22:8088/ConnectPlus/";
	public static final String PICTURE = "/home/sigma/SeleniumWS/DemoStartUp/config/a.jpg";

	/*********************** XPATH *************************************************/
	public static String AdmindropdownSearchXpath = "/html/body/div/div[4]/div[2]/div/div[2]/form/table/tbody/tr/td[4]/table/tbody/tr/td/select";
	public static String fileManagementXpath = "html/body/div[1]/div[4]/div[2]/div";
	public static String uploadButtonXpath = "html/body/div[1]/div[4]/div[2]/div/div[1]/form/button";
	public static String dropdownFileManagement = "/html/body/div/div[4]/div[2]/div[3]/form/table/tbody/tr/td[2]/select";
	public static String filemanagementPage_Xpath = "/html/body/div/div[4]/div[2]/div/div[3]/div/div/table/tbody/tr/td[3]";
	public static String adminXpath = "/html/body/div/div[5]/div/div/ul";
	public static String AllFieldsXpath = ".//*[@id='errorMessage']/ul";
	public static String PaXpath = " html/body/div[1]/div[4]/a[1]";
	public static String UploadFile = "html/body/div[1]/div[4]/div[2]/div/div[1]/form/button";
	public static String welcomeUpdate = "html/body/div[1]/div[4]/div[2]/div[2]/div/table/tbody/tr[2]";
	public static String dealerWelcome ="html/body/div[1]/div[4]/div";
	public static String welcomeNews = "html/body/div[1]/div[4]/div[2]";


	private static String removeStaticExpString(String errorMsg) {
		if (!errorMsg.contains(ERROREXCEPTION)) {
			return errorMsg;
		}
		String errStrArr[] = Pattern.compile(ERROREXCEPTION).split(errorMsg);
		return errStrArr[1];
	}
}
