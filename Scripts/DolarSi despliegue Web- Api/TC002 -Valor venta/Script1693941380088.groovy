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
import groovy.json.JsonOutput as JsonOutput
import groovy.json.JsonSlurper as JsonSlurper
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

WebUI.openBrowser('')

WebUI.navigateToUrl('https://www.dolarsi.com/')

def id_selector = ['v1', 'v2']

def valorWeb = new String[2]

for (int i = 0; i < 2; i++) {
    value_venta = (id_selector[i])

	(valorWeb[i]) = WebUI.getText(findTestObject('DolarSi/Page_Dolarsi/valor_dolar_venta', [('id_v') : value_venta]))
    
}


/*consulta API consumida por web evaluada para aplicación de assertion*/
response = WS.sendRequest(findTestObject('DolarSi/Api_DolarSi/DolarSi-Venta'))

/*Creación de variable result para manejo de respuesta API*/
def slurper = new JsonSlurper()
def result = slurper.parseText(response.getResponseText())

for (int i = 0; i < 2; i++) {
	
	String valueWeb = valorWeb[i].replace('$ ', "") /*eliminación de caracter especial $ de valor web para comparación*/
	String valueApi = result[i].casa.venta /*extracción de valor de respuesta api*/
	
	/*comparación de valores web - api*/
	if(valueWeb == valueApi) {
		println "Despliegue de valor correcto " + valueApi
	}else {
		keywordUtil.markFailed()
	}

}














