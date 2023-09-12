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
import org.openqa.selenium.Point as Point


count = CustomKeywords.'getElementList.getElementCount'('https://books.toscrape.com/catalogue/category/books/mystery_3/index.html')

for (i = 1; i <= count; i++) {
    WebUI.openBrowser('https://books.toscrape.com/catalogue/category/books/mystery_3/index.html')

    WebUI.maximizeWindow()

    def precio_results = WebUI.getText(findTestObject('Books to Scrape/PRUEBA ARTICULO/priceResult', [('number') : i]))

    println(precio_results)

    WebUI.click(findTestObject('Books to Scrape/PRUEBA ARTICULO/articulo_image', [('item') : i]))

    def precio_detail = WebUI.getText(findTestObject('Object Repository/Books to Scrape/PRUEBA ARTICULO/precio_article_detail'))

    println(precio_detail)

    WebUI.verifyEqual(precio_results, precio_detail)

    WebUI.closeBrowser()
}

