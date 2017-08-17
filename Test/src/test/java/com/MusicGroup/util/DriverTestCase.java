
package test.java.com.MusicGroup.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.security.UserAndPassword;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


/*
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.WebDriverWait;
*/

import test.java.com.MusicGroup.pagehelper.Unified_OrderSparePartsHelper;
//import test.java.com.MusicGroup.scripts.UnifiedTestCases.sample;
//import test.java.com.MusicGroup.scripts.UnifiedTestCases.sample.LoginWindow;
public abstract class DriverTestCase {

	//Define object
	public WebDriver driver;



    
    public Unified_OrderSparePartsHelper unified_OrderSparePartsHelper;
    //Initialize objects
	public PropertyReader propertyReader = new PropertyReader();
	//Declare variables
	public String URL = propertyReader.readApplicationFile("URL");
	public String browser_Type1 = propertyReader.readApplicationFile("Browser_Type");
	public Browser_Type browser_Type = Browser_Type.valueOf(browser_Type1);
    
    enum Browser_Type 
    {
    	Firefox, IE, Chrome, Safari;
    }    
    
	@BeforeClass
	public void setUp()
	{
		String chromeDriverPath= System.getProperty("user.dir")+"/chromedriver";
        switch(browser_Type)
        {
        
            case Firefox:
                driver = new FirefoxDriver();
                break;
            case IE:
                driver = new InternetExplorerDriver(); ;
                break;
            case Chrome:
			String Root_Path = System.getProperty("user.dir");
			System.out.println(chromeDriverPath);
            	System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            	  ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                driver = new ChromeDriver(options);
                break;
            case Safari:
                driver = new SafariDriver();
                break;
            default:
                driver = new FirefoxDriver();
                break;
            	
            		  
            		 
            		  //Set the required properties to instantiate Chrome driver. Place any latest Chromedriver.exe files under Drivers folder
        }
       
        //maximize window
		driver.manage().window().maximize();
		//Delete cookies
		driver.manage().deleteAllCookies();		
		driver.navigate().to(URL);
		
		System.out.println("Launched base URL");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	}

	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	public WebDriver getWebDriver()
	{
		return driver;
	}
	
	//capturing screenshot 
	public void captureScreenshot(String fileName) {
		try {
			String screenshotName = this.getFileName(fileName);
			String dirName = this.getDirectoryName(fileName);
			FileOutputStream out = new FileOutputStream("screenshots//" + dirName +"//" + screenshotName + ".jpg");
			out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
			out.close();
			String path = getPath();
			String  screen = "file://"+path+"/screenshots/" + dirName +"//" +screenshotName + ".jpg";
			Reporter.log("<a href= '"+screen+  "'target='_blank' >" + screenshotName + "</a>");
		} catch (Exception e) {

		}
	}
	
	//Creating file name
	public  String getFileName(String file){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();		 
		String fileName = file+dateFormat.format(cal.getTime());
		return fileName;
	}
	
	//Creating directory 
	public  String getDirectoryName(String dirName)
	{
		String path = getPath();
		String s=path+"//screenshots//" + dirName +"";
		File theDir = new File(s);
		// if the directory does not exist, create it
		if (!theDir.exists()) 
		{
		    boolean result = false;
		    try{
		        theDir.mkdir();
		        result = true;
		    } 
		    catch(SecurityException se){
		        //handle it
		    }        
		    if(result) {    
		        System.out.println("DIR created");  
		    }
		}
		return dirName;
	}
	
	//Get absolute path
	public String getPath()
	{
		String path ="";		
		File file = new File("");
		String absolutePathOfFirstFile = file.getAbsolutePath();
		path = absolutePathOfFirstFile.replaceAll("\\\\+", "/");		
		return path;
	}

	//Get absolute path
	public String getPathUpload()
	{
		String path ="";		
		File file = new File("");
		String absolutePathOfFirstFile = file.getAbsolutePath();
		path = absolutePathOfFirstFile.replaceAll("/", "//");		
		return path;
	}
	
	//inner class for Login thread    
    public class LoginWindow implements Runnable 
    {

	        @Override
	        public void run() {
	            try {
	                login();
	            } catch (Exception ex) {
	                System.out.println("Error in Login Thread: " + ex.getMessage());
	            }
	        }//run close

	        public void login() throws Exception 
	        {

	            //wait - increase this wait period if required
	            Thread.sleep(20000);

	            //create robot for keyboard operations
	            Robot rb = new Robot();

	            //Enter user name by ctrl-v
	            StringSelection username = new StringSelection("surya.kant");
	            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(username, null);            
	            rb.keyPress(KeyEvent.VK_CONTROL);
	            rb.keyPress(KeyEvent.VK_V);
	            System.out.println("username provided");
	            rb.keyRelease(KeyEvent.VK_V);
	            rb.keyRelease(KeyEvent.VK_CONTROL);

	            //tab to password entry field
	            rb.keyPress(KeyEvent.VK_TAB);
	            rb.keyRelease(KeyEvent.VK_TAB);
	            Thread.sleep(3000);

	            //Enter password by ctrl-v
	            StringSelection pwd = new StringSelection("360Logica8932");
	            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(pwd, null);
	            rb.keyPress(KeyEvent.VK_CONTROL);
	            rb.keyPress(KeyEvent.VK_V);
	            
	            rb.keyRelease(KeyEvent.VK_V);
	            rb.keyRelease(KeyEvent.VK_CONTROL);

	            //press enter
	            rb.keyPress(KeyEvent.VK_ENTER);
	            rb.keyRelease(KeyEvent.VK_ENTER);

	            //wait
	            Thread.sleep(5000);
	        }//login close                        
	    }// class close
	
  }
