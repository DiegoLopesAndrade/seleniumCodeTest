package uk.ct.diegoandrade.pages;

import static org.junit.Assert.assertEquals;
import static uk.ct.diegoandrade.core.DriverFactory.getDriver;

import org.junit.Test;
import org.openqa.selenium.By;

import uk.ct.diegoandrade.core.BasePage;
import uk.ct.diegoandrade.core.DriverFactory;

public class InfiniteScroll extends BasePage {
	
	@Test
	public void testInfiniteScroll() throws InterruptedException{
		
		// Scroll to the bottom to the page twice and scroll back to the top to validate text.
		DriverFactory.getDriver().get("http://the-internet.herokuapp.com/");
		clickLink("Infinite Scroll");
		Thread.sleep(2000);
		executeJS("scroll(0, 250)");
		Thread.sleep(2000);
		executeJS("scroll(0, -250)");
		assertEquals(getDriver().findElement(By.xpath("//*[@id='content']/div/h3")).getText(),"Infinite Scroll");
	}
}
