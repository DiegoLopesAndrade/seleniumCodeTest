package uk.ct.diegoandrade.pages;

import static org.junit.Assert.assertEquals;
import static uk.ct.diegoandrade.core.DriverFactory.getDriver;

import org.junit.Test;
import org.openqa.selenium.By;

import uk.ct.diegoandrade.core.BasePage;
import uk.ct.diegoandrade.core.DriverFactory;

public class KeyPressPage extends BasePage {
	
	@Test
	public void testValidatingKeyPress(){
		
		// type 4 keys and validate the text after every key pressed.
		DriverFactory.getDriver().get("http://the-internet.herokuapp.com/");
		clickLink("Key Presses");
		getDriver().findElement(By.id("target")).sendKeys("4");
		assertEquals(getDriver().findElement(By.id("result")).getText(),"You entered: 4");
	}
}