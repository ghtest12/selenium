package subbu;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.Properties;

//import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ObjectRepository.loginPageOR;
import ObjectRepository.securityQuestionsOR;
import ObjectRepository.graphPageOR;

public class openfutures {
	static String s1 = new String();
	static String s2 = new String();
	static String a1 = new String();
	static String a2 = new String();
	static WebDriver driver;
	static Properties prop;
	static FileInputStream fip;

	public static void main(String[] args) throws InterruptedException, IOException {
		a1 = "";
		a2 = "";
		
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println( sdf.format(cal.getTime()) );
		
		System.setProperty("webdriver.gecko.driver", "./gecko/geckodriver.exe");

		/* Disable Browser Push Notifications */
		ProfilesIni profile = new ProfilesIni();
		FirefoxProfile testprofile = profile.getProfile("default");
		testprofile.setPreference("dom.webnotifications.enabled", false);
		DesiredCapabilities dc = DesiredCapabilities.firefox();
		dc.setCapability(FirefoxDriver.PROFILE, testprofile);
		FirefoxOptions opt = new FirefoxOptions();
		opt.merge(dc);
		/*Disable Browser Push Notifications*/
		
		driver =  new FirefoxDriver(opt);
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("https://kite.zerodha.com");
		driver.manage().window().maximize();
		
		//create three new objects for object repositories
		loginPageOR lpo = new loginPageOR(driver);
		securityQuestionsOR sqo = new securityQuestionsOR(driver);
		graphPageOR gpo = new graphPageOR(driver);
		
		//Login page
		prop = new Properties();
		fip = new FileInputStream("./data/logindetails.properties");
		prop.load(fip);
		
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");

		lpo.lpusername().sendKeys(username);
		lpo.lppassword().sendKeys(password);
		lpo.lpsubmitbutton().click();
		
		//Security Question Page
		s1 = sqo.sqone().getText();
		s2 = sqo.sqtwo().getText();
		
		a1 = securityAnswers(s1);
		a2 = securityAnswers(s2);
		
		sqo.aone().sendKeys(a1);
		sqo.atwo().sendKeys(a2);

		sqo.sqpsubmitbutton().click();
		
		//graph page - click on the fifth icon at the bottom
		gpo.fifthtab().click();
		
		//IF there are any existing elements in fifth tab first delete them
		//deleteExistingElements();
		
		Datadriven dd = new Datadriven();
		ArrayList<String> data = dd.getData();
		//System.out.println("Size is "+data.size());
		for(int i=0; i<data.size(); i++) {
			//String str = new String((String) data.get(i));
			
			gpo.symbolinput().sendKeys(data.get(i));
			gpo.symbolinput().sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			Actions a = new Actions(driver);
			WebElement move = gpo.symbolarea();
			a.moveToElement(move).build().perform();
			gpo.opengraphbutton().click();
			
			//WebDriverWait wait = new WebDriverWait(driver, 2 /*timeout in seconds*/);
			for(int ik=0; ik<3; ik++)
			{
				if(isAlertPresent()==true)
				{
					System.out.println("Alert is present");
			    	driver.switchTo().alert().dismiss();
			    	if(ik<2) {
			    		a.moveToElement(move).build().perform();
			    		gpo.opengraphbutton().click();
			    	}
				}
				else {
					break;
				}
			}
			
			Thread.sleep(1000);
			
			a.moveToElement(move).build().perform();
			gpo.deletegraphbutton().click();
		}
		System.out.println("Closing browser");
		driver.quit();
		
		Calendar cal1 = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
        System.out.println( sdf1.format(cal1.getTime()) );
	}
	public static void deleteExistingElements() throws InterruptedException {
		graphPageOR gpo = new graphPageOR(driver);
		
		Actions a = new Actions(driver);
		WebElement move = gpo.symbolarea();
		WebElement move1 = gpo.symbolinput();
		
		int allElements = gpo.allExistingElements().size();
		System.out.println("No. of existing elements"+allElements);
		for(int i=0;i<allElements; i++) {
			a.moveToElement(move).build().perform();
			a.moveToElement(move1).build().perform();
			Thread.sleep(1000);
			a.moveToElement(move).build().perform();
			gpo.deletegraphbutton().click();
		}
	}
	public static boolean isAlertPresent(){
	    boolean foundAlert = false;
	    WebDriverWait wait = new WebDriverWait(driver, 0 /*timeout in seconds*/);
	    try {
	        wait.until(ExpectedConditions.alertIsPresent());
	        foundAlert = true;
	    } catch (TimeoutException eTO) {
	        foundAlert = false;
	    }
	    return foundAlert;
	}
	public static String securityAnswers(String question) throws IOException {
		prop = new Properties();
		fip = new FileInputStream("./data/securityquestions.properties");
		prop.load(fip);
		
		String sq1 = prop.getProperty("sq1");
		String ans1 = prop.getProperty("ans1");
		String sq2 = prop.getProperty("sq2");
		String ans2 = prop.getProperty("ans2");
		String sq3 = prop.getProperty("sq3");
		String ans3 = prop.getProperty("ans3");
		String sq4 = prop.getProperty("sq4");
		String ans4 = prop.getProperty("ans4");
		String sq5 = prop.getProperty("sq5");
		String ans5 = prop.getProperty("ans5");
		
		if(question.equals(sq1)) {
			return ans1;
		}
		else if(question.equals(sq2)) {
			return ans2;
		}
		else if(question.equals(sq3)) {
			return ans3;
		}
		else if(question.equals(sq4)) {
			return ans4;
		}
		else {
			return ans5;
		}
	}
}
