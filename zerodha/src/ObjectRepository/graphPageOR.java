package ObjectRepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class graphPageOR {
	WebDriver driver;
	public graphPageOR(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement fifthtab() {
		By fifthtab = By.xpath("//div[@class='container-left']/div/ul/li[5]");
		return driver.findElement(fifthtab);
	}
	
	//return the existing number of elements in 5th tab
	public List<WebElement> allExistingElements() {
		By allExistingElements = By.xpath("//div[@class='instruments']/div/div");
		return driver.findElements(allExistingElements);
	}

	public WebElement symbolinput() {
		By symbolinput = By.xpath("//input[@id='search-input']");
		return driver.findElement(symbolinput);
	}
	
	public WebElement symbolarea() {
		By symbolarea = By.xpath("//span[@class='nice-name']");
		return driver.findElement(symbolarea);
	}
	
	public By symbolareaxpath() {
		By symbolareaxpath = By.xpath("//span[@class='nice-name']");
		return symbolareaxpath;
	}
	
	public WebElement opengraphbutton() {
		By opengraphbutton = By.xpath("//span[@class='actions']/button[4]");
		return driver.findElement(opengraphbutton);
	}
	
	public WebElement deletegraphbutton() {
		By deletegraphbutton = By.xpath("//span[@class='actions']/button[5]");
		return driver.findElement(deletegraphbutton);
	}
}