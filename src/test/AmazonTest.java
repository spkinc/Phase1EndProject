package test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonTest {

	public static void main(String[] args) throws IOException {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);

		// Search for samsung mobile on amazon.in web page
		WebElement searchBox = driver.findElement(By.name("field-keywords"));
		WebElement searchButton = driver.findElement(By.id("nav-search-submit-button"));

		searchBox.sendKeys("samsung mobile");
		searchButton.click();

		// Print the results on the console
		List<WebElement> prodList = driver.findElements(By.xpath("//div[@class='a-section']//h2//span"));
		List<WebElement> prodPrice = driver.findElements(By.xpath(
				"//div[@data-component-type='s-search-result']//div[contains(@class,'price')]//span[@class='a-price']"));

		for (int i = 0; i < prodList.size(); i++) {
			System.out.println("Product List " + i + ": " + prodList.get(i).getText());
			System.out.println("Product " + i + " Price: " + prodPrice.get(i).getText());
		}

		// Taking screen shot of the page
		TakesScreenshot obj = (TakesScreenshot) driver;
		File fileObj = obj.getScreenshotAs(OutputType.FILE);
		File screenObj = new File("image.png");
		FileUtils.copyFile(fileObj, screenObj);

		driver.close();
	}
}
