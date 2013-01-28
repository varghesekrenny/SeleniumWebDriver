package com.sigma.demo.TestCases;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.internal.seleniumemulation.GetText;

import com.sigma.demo.Constants.ApplicationUtils;
import com.sigma.demo.Constants.CommonUtils;
import com.sigma.demo.Constants.DataConstants;
import com.sigma.demo.FrameWork.Utils.DemoStartUp;

public class WelcomePageTest extends ApplicationUtils {

	protected static DemoStartUp demo = new DemoStartUp();

	static {
		demo.getTestSheetMapping(5);
	}
    @Test
    public void testWelcomeNewEditContent()
    {
    	demo.initializeTestProps("WelcomeUpdate");
    	String [] expectedResult = EXPECTEDRESULT.split("##");
    	webdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	testWelcome();
    	
    	webdriver.findElement(By.xpath("html/body/div[1]/div[4]/div[2]/div[2]/div/table/tbody/tr[2]/td[4]/div/a")).click();
    	assignDefaultWelcomePage();
    	webdriver.findElement(By.id("welcomeTitle")).sendKeys(NEWSTITLE);
    	webdriver.findElement(By.xpath("/html/body/div/div[4]/div[2]/form/div[3]/table/tbody/tr[2]/td[2]/div[2]/div")).sendKeys(NEWSCONTENT);
    	webdriver.findElement(By.id("welcomeURL")).sendKeys(URL);
    	
    	webdriver.findElement(By.id("save")).click();
    	
    	String fileTitle = webdriver.findElement(By.xpath(DataConstants.welcomeUpdate+"/td[1]/p")).getText();
    	String fileDescript = webdriver.findElement(By.xpath(DataConstants.welcomeUpdate+"/td[2]/p")).getText();
    	String fileUrl = webdriver.findElement(By.xpath(DataConstants.welcomeUpdate+"/td[3]/p")).getText();
    	
    	if(!expectedResult[0].equalsIgnoreCase(fileTitle))
    			{
    		assertFail(expectedResult[0]+ "\t" + "doesnot get match");
    			}
    	if(!expectedResult[1].equalsIgnoreCase(fileDescript))
    	{
    		assertFail(expectedResult[1] + "\t" + "doesnot get match");
    	}
    	if(!expectedResult[2].equalsIgnoreCase(fileUrl))
    	{
    		assertFail(expectedResult[2] + "\t" + "doesnot get match");
    	}
    	webdriver.findElement(By.xpath("html/body/div[1]/div[3]/a[1]")).click();
    	String fileWelcomeTitle = webdriver.findElement(By.xpath("html/body/div[1]/div[4]/div/div[1]/p")).getText();
    	if(!expectedResult[1].equalsIgnoreCase(fileWelcomeTitle))
    	{
    		assertFail(expectedResult[0] +"\t" + "doesnot get match");
    	}
    	assertPass();
    }
	
    @Test
    public void testWelcomeUpdateTabForDealer()
    {
    	demo.initializeTestProps("WelcomeUpdatetab_Dealer");
    	webdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	String [] expectedResult = EXPECTEDRESULT.split("##");
        loginConnectPlusApplication(USER_NAME, PASS_WORD);
        webdriver.findElement(By.xpath("html/body/div[1]/div[3]/a[1]")).click();
       
        String actualWelcome = webdriver.findElement(By.xpath(DataConstants.dealerWelcome + "/div[1]/h1")).getText();
        String actualUpdate = webdriver.findElement(By.xpath(DataConstants.dealerWelcome + "/div[2]/h2")).getText();
        if(!expectedResult[0].equalsIgnoreCase(actualWelcome))
        		{
        	assertFail(expectedResult[0] + "doesnot get match");
        		}
        if(!expectedResult[1].equalsIgnoreCase(actualUpdate))
        {
        	assertFail(expectedResult[1] + "doesnot get matches");
        }
    	assertPass();
    }
    
	@Test
	public void testWelcomeUpdateNews() throws Exception {
		demo.initializeTestProps("WelcomeNews_Updates");
		String[] expectedResult = EXPECTEDRESULT.split("##");
		String[] newsTitle = NEWSTITLE.split("##");
		String[] newsContent = NEWSCONTENT.split("##");
		String[] url = URL.split("##");
		testWelcome();
		try {
		String actualWelcome = webdriver.findElement(
				By.xpath(DataConstants.welcomeNews + "/div[2]/div/div[1]"))
				.getText();
		System.out.println(actualWelcome);
		String newsTitleValue = null;
		
		if(!expectedResult[0].equalsIgnoreCase(actualWelcome))
		{
			assertFail(expectedResult[0] + "doesnot get match");
		}
		for(int i=0; i<2; i++)
		{
			if(i==0)
			{
		webdriver.findElement(By.xpath(DataConstants.welcomeNews + "/div[2]/div/div[2]/a")).click();
			}
		webdriver.findElement(By.xpath(".//*[@id='newsTitle']")).sendKeys(newsTitle[i]);
		webdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		webdriver.findElement(By.xpath(DataConstants.welcomeNews + "/form/div[3]/table/tbody/tr[2]/td[2]/div[2]/div")).sendKeys(newsContent[i]);
		webdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		webdriver.findElement(By.xpath(".//*[@id='url']")).sendKeys(url[i]);
	
		webdriver.findElement(By.id("save")).click();
		webdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		String actualNewsTitle = webdriver.findElement(By.xpath(".//*[@id='NewsListTable']/tbody/tr[1]/td[1]/p")).getText();
		String acutalNewsContent = webdriver.findElement(By.xpath(".//*[@id='NewsListTable']/tbody/tr[1]/td[2]/p")).getText();
		String actualUrl = webdriver.findElement(By.xpath(".//*[@id='NewsListTable']/tbody/tr[1]/td[3]/p")).getText();
		System.out.println(actualUrl);
		webdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		newsTitleValue = newsTitle[i];
		if(!newsTitle[i].equalsIgnoreCase(actualNewsTitle))
		{
			assertFail(NEWSTITLE + "doesnot get match");
		}
		if(!newsContent[i].equalsIgnoreCase(acutalNewsContent))
		{
			assertFail(NEWSCONTENT + "doesnot get match");
		}
		if(!url[i].equalsIgnoreCase(actualUrl))
		{
			assertFail(URL + "doesnot get match");
		}
		// edit button
		if(i==0)
		{
		webdriver.findElement(By.xpath(".//*[@id='NewsListTable']/tbody/tr[1]/td[4]/div[1]/a")).click();
		// Editing by clearing and clicking
		assignNewUpdateDefault();
		}
		}
		webdriver.findElement(By.xpath("html/body/div[1]/div[3]/a[1]")).click();
		String newUpdateTitle  = webdriver.findElement(By.xpath("html/body/div[1]/div[4]/div/div[2]/div[1]/span")).getText();
		if(!newUpdateTitle.equalsIgnoreCase(newsTitleValue))
		{
			assertFail(newUpdateTitle + "not found");
		}
		} catch(Exception ex)
		{
			assertFail("Not able to find the element");
		}
        assertPass();
	}
	
}
