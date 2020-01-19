package uk.ct.diegoandrade.pages;

import uk.ct.diegoandrade.core.DriverFactory;

import static org.junit.Assert.assertEquals;
import static uk.ct.diegoandrade.core.DriverFactory.getDriver;

import org.junit.Test;
import org.openqa.selenium.By;
import uk.ct.diegoandrade.core.BasePage;

public class LoginPage extends BasePage {
	
	@Test
	public void testAuthenticationLoginAndLogout(){
		
		// Validating login and Logout.
		DriverFactory.getDriver().get("http://the-internet.herokuapp.com/");
		clickLink("Form Authentication");
		getDriver().findElement(By.id("username")).sendKeys("tomsmith");
		getDriver().findElement(By.id("password")).sendKeys("SuperSecretPassword!");
		getDriver().findElement(By.xpath("//button[.=' Login']")).click();
		clickLink("Logout");
	}

	@Test
	public void testAuthenticationWithIncorrectPassword() throws InterruptedException{
		
		// Validating login with incorrect password.
		DriverFactory.getDriver().get("http://the-internet.herokuapp.com/");
		clickLink("Form Authentication");
		getDriver().findElement(By.id("username")).sendKeys("tomsmith");
		getDriver().findElement(By.id("password")).sendKeys("SuperSecretPassword1!");
		getDriver().findElement(By.xpath("//button[.=' Login']")).click();
		Thread.sleep(2000);
		assertEquals(getDriver().findElement(By.id("flash")).getText(),"Your password is invalid!\n" + 
				"×");
	}

	@Test
	public void testAuthenticationWithUsernameIncorrect() throws InterruptedException{
		
		// Validating login with incorrect username.
		DriverFactory.getDriver().get("http://the-internet.herokuapp.com/");
		clickLink("Form Authentication");
		getDriver().findElement(By.id("username")).sendKeys("tomsmith1");
		getDriver().findElement(By.id("password")).sendKeys("SuperSecretPassword!");
		getDriver().findElement(By.xpath("//button[.=' Login']")).click();
		Thread.sleep(2000);
		assertEquals(getDriver().findElement(By.id("flash")).getText(),"Your username is invalid!\n" + 
				"×");
	}
}