package ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class loginPageOR {
	WebDriver driver;
	public loginPageOR(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement lpusername() {
		By lpusername = By.xpath("//input[@type='text']");
		return driver.findElement(lpusername);
	}

	public WebElement lppassword() {
		By lppassword = By.xpath("//input[@type='password']");
		return driver.findElement(lppassword);
	}
	
	public WebElement lpsubmitbutton() {
		By lpsubmitbutton = By.xpath("//button[@type='submit']");
		return driver.findElement(lpsubmitbutton);
	}
}
