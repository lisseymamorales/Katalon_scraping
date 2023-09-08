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
	

//}


String url = 'http://books.toscrape.com/catalogue/category/books/mystery_3/index.html'

count = CustomKeywords.'getElementList.getElementCount'(url)

println count

int results = (WebUI.getText(findTestObject('Object Repository/Books to Scrape/Category_page/numero_resultados'))).toInteger()
		
		
		def condicion = (results == count) ? 1 : 2
			
		switch (condicion) {
			case 1:
			println "Passed";
			WebUI.closeBrowser()
			break;
			
			case 2:
			if(findTestObject('Object Repository/Books to Scrape/Category_page/next')) {
											
				WebUI.click(findTestObject('Object Repository/Books to Scrape/Category_page/next'))
				page2 = WebUI.getUrl()
				count2 = CustomKeywords.'getElementList.getElementCount'(page2)
			
				count = count + count2
				
				println results
				println count2
				println count
				
				WebUI.verifyEqual(results, count)
			}
			WebUI.closeBrowser()
			break;
			
			default:
			println "fallido";
			 }
			 


