import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

/*int count = GlobalVariable.path.size()*/

//for (int i = 0; i < count; i++) {
	
	//WebUI.openBrowser(GlobalVariable.path[i])
	
	WebUI.openBrowser('http://books.toscrape.com/catalogue/category/books/mystery_3/index.html')
	
	WebDriver driver = DriverFactory.getWebDriver()
	
	WebElement Table0 = driver.findElement(By.xpath("//div[1]/div/div/div/section/div[2]/ol")) /*xpath de la grilla*/
	
	/*Para localizar filas de la tabla, capturará todas las filas disponibles en la tabla.*/
	List<WebElement> rows_table0 = Table0.findElements(By.tagName('li'))
	
	/*Para calcular el número de filas en la tabla*/
	int rows_count = rows_table0.size()
		println rows_count
//}
		def results = WebUI.getText(findTestObject('Object Repository/Books to Scrape/Category_page/numero_resultados'))
		
		println results
		

if(rows_count == results ) {
	println "hacer algo"
}else if(findTestObject('Object Repository/Books to Scrape/Category_page/next')) {
	
	WebUI.click(findTestObject('Object Repository/Books to Scrape/Category_page/next'))
	
	WebElement Table = driver.findElement(By.xpath("//div[1]/div/div/div/section/div[2]/ol")) /*xpath de la grilla*/
	
	/*Para localizar filas de la tabla, capturará todas las filas disponibles en la tabla.*/
	List<WebElement> rows_table = Table.findElements(By.tagName('li'))
	
	/*Para calcular el número de filas en la tabla*/
	int rows_count2 = rows_table.size()
	
	rows_count = rows_count+ rows_count2
	
	println results
	println rows_count
	
	WebUI.verifyEqual(results, rows_count)
	
}else {
	println "marcar fallido"
}





