package uk.ct.diegoandrade.core;

import static uk.ct.diegoandrade.core.DriverFactory.getDriver;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BasePage {

/********* TextField and TextArea ************/
	
	public void type(By by, String texto){
		getDriver().findElement(by).clear();
		getDriver().findElement(by).sendKeys(texto);
	}

	public void type(String id_campo, String texto){
		type(By.id(id_campo), texto);
	}
	
	public String getFieldValue(String id_campo) {
		return getDriver().findElement(By.id(id_campo)).getAttribute("value");
	}
	
	/********* Radio e Check ************/
	
	public void clickRadio(By by) {
		getDriver().findElement(by).click();
	}
	
	public void clickRadio(String id) {
		clickRadio(By.id(id));
	}
	
	public boolean isRadioSelected(String id){
		return getDriver().findElement(By.id(id)).isSelected();
	}
	
	public void clickCheck(String id) {
		getDriver().findElement(By.id(id)).click();
	}
	
	public boolean isCheckSelected(String id){
		return getDriver().findElement(By.id(id)).isSelected();
	}
	
	/********* Combo ************/
	
	public void selectCombo(String id, String value) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		combo.selectByVisibleText(value);
	}
	
	public void deselectCombo(String id, String value) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		combo.deselectByVisibleText(value);
	}

	public String getValueCombo(String id) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}
	
	public List<String> getValuesCombo(String id) {
		WebElement element = getDriver().findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		List<String> values = new ArrayList<String>();
		for(WebElement option: allSelectedOptions) {
			values.add(option.getText());
		}
		return values;
	}
	
	public int getQtdOptionsCombo(String id){
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		return options.size();
	}
	
	public boolean verifyOptionCombo(String id, String opcao){
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		for(WebElement option: options) {
			if(option.getText().equals(opcao)){
				return true;
			}
		}
		return false;
	}
	
	public void selecionarComboPrime(String radical, String value) {
		clickRadio(By.xpath("//*[@id='"+radical+"_input']/../..//span"));
		clickRadio(By.xpath("//*[@id='"+radical+"_items']//li[.='"+value+"']"));
	}
	
	/********* Button ************/
	
	public void clickButton(By by) {
		getDriver().findElement(by).click();
	}
	public void clickButton(String id) {
		clickButton(By.id(id));
	}
	
	public void clickButtonText(String text){
		clickButton(By.xpath("//button[.='"+text+"']"));
	}
	
	public String getValueElement(String id) {
		return getDriver().findElement(By.id(id)).getAttribute("value");
	}
	
	/********* Link ************/
	
	public void clickLink(String link) {
		getDriver().findElement(By.linkText(link)).click();
	}
	
	/********* Texts ************/
	
	public String getTexts(By by) {
		return getDriver().findElement(by).getText();
	}
	
	public String getTexts(String id) {
		return getTexts(By.id(id));
	}
	
	/********* Alerts ************/
	
	public String getAlertText(){
		Alert alert = getDriver().switchTo().alert();
		return alert.getText();
	}
	
	public String getAlertTextAccept(){
		Alert alert = getDriver().switchTo().alert();
		String value = alert.getText();
		alert.accept();
		return value;
		
	}
	
	public String getAlertTextIcorrect(){
		Alert alert = getDriver().switchTo().alert();
		String value = alert.getText();
		alert.dismiss();
		return value;
		
	}
	
	public void typeAlert(String value) {
		Alert alert = getDriver().switchTo().alert();
		alert.sendKeys(value);
		alert.accept();
	}
	
	/********* Frames and Windows ************/
	
	public void accessFrame(String id) {
		getDriver().switchTo().frame(id);
	}
	
	public void exitFrame(){
		getDriver().switchTo().defaultContent();
	}
	
	public void changeWindow(String id) {
		getDriver().switchTo().window(id);
	}
	
	/************** JS *********************/
	
	public Object executeJS(String cmd, Object... param) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		return js.executeScript(cmd, param);
	}
	
	/************** Table *********************/
	
	public WebElement getCell(String findColumn, String value, String buttonColumn, String idTable){
		WebElement table = getDriver().findElement(By.xpath("//*[@id='"+idTable+"']"));
		int idColumn = getIndexColumn(findColumn, table);
		
		//find the record line
		int idLinha = getIndexLine(value, table, idColumn);
		
		//find column of the button
		int idColumnButton = getIndexColumn(buttonColumn, table);
		
		//click on button of the cell found
		WebElement cell = table.findElement(By.xpath(".//tr["+idLinha+"]/td["+idColumnButton+"]"));
		return cell;
	}
	
	public void clickButtonTable(String findColumn, String value, String buttonColumn, String idTable){
		WebElement celll = getCell(findColumn, value, buttonColumn, idTable);
		celll.findElement(By.xpath(".//input")).click();
		
	}

	protected int getIndexLine(String value, WebElement table, int idColumn) {
		List<WebElement> lines = table.findElements(By.xpath("./tbody/tr/td["+idColumn+"]"));
		int idLine = -1;
		for(int i = 0; i < lines.size(); i++) {
			if(lines.get(i).getText().equals(value)) {
				idLine = i+1;
				break;
			}
		}
		return idLine;
	}

	protected int getIndexColumn(String column, WebElement table) {
		List<WebElement> columns = table.findElements(By.xpath(".//th"));
		int idColumn = -1;
		for(int i = 0; i < columns.size(); i++) {
			if(columns.get(i).getText().equals(column)) {
				idColumn = i+1;
				break;
			}
		}
		return idColumn;
	}
}
