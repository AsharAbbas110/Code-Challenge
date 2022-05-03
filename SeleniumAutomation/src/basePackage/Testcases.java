package basePackage;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Testcases {
	private static WebDriver driver;
	WebElement element;

	@BeforeClass
	public static void openBrowser(){
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\jars\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	} 
	
	@Before
	public void openApplication()
	{
		driver.get("http://automationpractice.com/index.php");	
		driver.manage().window().maximize();
	}

	@Test
	public void validatePresenceOfAllFieldsOnContactUsPage(){
		System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
		
		driver.findElement(By.xpath("//*[@id=\"contact-link\"]/a")).click();
		Assert.assertTrue("Contact Navigation Indicator is displayed", driver.findElement (By.xpath("//*[@id=\"columns\"]/div[1]/span[2]")).isDisplayed());
		Assert.assertEquals("CUSTOMER SERVICE - CONTACT US", driver.findElement (By.xpath("//*[@id=\"center_column\"]/h1")).getText().trim().toUpperCase());
		Assert.assertEquals("SEND A MESSAGE", driver.findElement (By.xpath("//*[@id=\"center_column\"]/form/fieldset/h3")).getText().trim().toUpperCase());
		Assert.assertEquals("Subject Heading", driver.findElement (By.xpath("//*[@id=\"center_column\"]/form/fieldset/div[1]/div[1]/div[1]/label")).getText().trim());
		Assert.assertEquals("Email address", driver.findElement (By.xpath("//*[@id=\"center_column\"]/form/fieldset/div[1]/div[1]/p[4]/label")).getText().trim());
		Assert.assertEquals("Order reference", driver.findElement (By.xpath("//*[@id=\"center_column\"]/form/fieldset/div[1]/div[1]/div[2]/label")).getText().trim());
		Assert.assertEquals("Attach File", driver.findElement (By.xpath("//*[@id=\"center_column\"]/form/fieldset/div[1]/div[1]/p[5]/label")).getText().trim());
		Assert.assertEquals("Message", driver.findElement (By.xpath("//*[@id=\"center_column\"]/form/fieldset/div[1]/div[2]/div/label")).getText().trim());
		Assert.assertTrue("Email Address text field is displayed", driver.findElement (By.xpath("//*[@id=\"email\"]")).isDisplayed());
		Assert.assertTrue("Order Reference text field is displayed", driver.findElement (By.xpath("//*[@id=\"id_order\"]")).isDisplayed());
		Assert.assertTrue("Attach File text field is displayed", driver.findElement (By.xpath("//*[@id=\"uniform-fileUpload\"]/span[1]")).isDisplayed());
		Assert.assertTrue("Message text area is displayed", driver.findElement (By.xpath("//*[@id=\"message\"]")).isDisplayed());
		Assert.assertTrue("Choose File button is displayed", driver.findElement (By.xpath("//*[@id=\"uniform-fileUpload\"]/span[2]")).isDisplayed());
		Assert.assertTrue("Send button is displayed", driver.findElement (By.xpath("//*[@id=\"submitMessage\"]")).isDisplayed());
		System.out.println("Ending test " + new Object(){}.getClass().getEnclosingMethod().getName());
	}
	
	@Test
	public void validateErrorMessageOnEmailAddress(){
		System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
		driver.findElement(By.xpath("//*[@id=\"contact-link\"]/a")).click();
		driver.findElement (By.xpath("//*[@id=\"submitMessage\"]")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"center_column\"]/div")).getText().contains("Invalid email address."));
		System.out.println("Ending test " + new Object(){}.getClass().getEnclosingMethod().getName());
	}
	
	@Test
	public void validateErrorMessageOnIncorrectEmailAddress(){
		System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
		driver.findElement(By.xpath("//*[@id=\"contact-link\"]/a")).click();
		driver.findElement (By.xpath("//*[@id=\"email\"]")).sendKeys("ashar");
		driver.findElement (By.xpath("//*[@id=\"submitMessage\"]")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"center_column\"]/div")).getText().contains("Invalid email address."));
		System.out.println("Ending test " + new Object(){}.getClass().getEnclosingMethod().getName());
	}

	@Test
	public void validateErrorMessageOnMessageField(){
		System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
		driver.findElement(By.xpath("//*[@id=\"contact-link\"]/a")).click();
		driver.findElement (By.xpath("//*[@id=\"email\"]")).sendKeys("ashar.abbas@gmail.com");
		driver.findElement (By.xpath("//*[@id=\"submitMessage\"]")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"center_column\"]/div")).getText().contains("The message cannot be blank."));
		System.out.println("Ending test " + new Object(){}.getClass().getEnclosingMethod().getName());
	}

	@Test
	public void validateErrorMessageOnSubjectHeadingField(){
		System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
		driver.findElement(By.xpath("//*[@id=\"contact-link\"]/a")).click();
		driver.findElement (By.xpath("//*[@id=\"email\"]")).sendKeys("ashar.abbas@gmail.com");
		driver.findElement (By.xpath("//*[@id=\"message\"]")).sendKeys("Hello");
		driver.findElement (By.xpath("//*[@id=\"submitMessage\"]")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"center_column\"]/div")).getText().contains("Please select a subject from the list provided."));
		System.out.println("Ending test " + new Object(){}.getClass().getEnclosingMethod().getName());
	}
	
	@Test
	public void validateSuccessMessage(){
		System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
		driver.findElement(By.xpath("//*[@id=\"contact-link\"]/a")).click();
		driver.findElement (By.xpath("//*[@id=\"email\"]")).sendKeys("ashar.abbas@gmail.com");
		driver.findElement (By.xpath("//*[@id=\"message\"]")).sendKeys("Hello");
		Select drpSubject = new Select(driver.findElement(By.id("id_contact")));
		drpSubject.selectByVisibleText("Webmaster");
		driver.findElement (By.xpath("//*[@id=\"id_contact\"]")).click();
		driver.findElement (By.xpath("//*[@id=\"submitMessage\"]")).click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"center_column\"]/p")).getText().contains("Your message has been successfully sent to our team."));
		Assert.assertTrue("Home button is displayed", driver.findElement (By.xpath("//*[@id=\"center_column\"]/ul/li/a/span")).isDisplayed());
		System.out.println("Ending test " + new Object(){}.getClass().getEnclosingMethod().getName());
	}
	
	@AfterClass
	public static void closeBrowser(){
		driver.quit();
	}
}