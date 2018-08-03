package ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class securityQuestionsOR {
	WebDriver driver;
	public securityQuestionsOR(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement sqone() {
		By sqone = By.xpath("//div[@class='login-form']/form/div[2]/div/label");
		return driver.findElement(sqone);
	}

	public WebElement sqtwo() {
		By sqtwo = By.xpath("//div[@class='login-form']/form/div[3]/div/label");
		return driver.findElement(sqtwo);
	}
	
	public WebElement aone() {
		By aone = By.xpath("//div[@class='login-form']/form/div[2]/div/input");
		return driver.findElement(aone);
	}

	public WebElement atwo() {
		By atwo = By.xpath("//div[@class='login-form']/form/div[3]/div/input");
		return driver.findElement(atwo);
	}
	
	public WebElement sqpsubmitbutton() {
		By sqpsubmitbutton = By.xpath("//button[@type='submit']");
		return driver.findElement(sqpsubmitbutton);
	}
	
}