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

WebUI.openBrowser('https://www.dolarsi.com/')

/*declaración arreglo con id de sustición en TestObject para iteración*/
def id_selector = ['c1', 'c2']

/*declaración arreglo para guardar valores extraídos de la web*/
def valorWeb = new String[2]

/*iteración sobre valores id - testobject*/
for (int i = 0; i < 2; i++) {
	/*asignación valor a variable value_compra para sustitución en objeto*/
    value_compra = (id_selector[i])

	/*extracción de valor de la web en posición indicada*/
    (valorWeb[i]) = WebUI.getText(findTestObject('Page_Dolarsi/valor_dolar_compra', [('id') : value_compra]))
}

/*consulta API consumida por web evaluada para aplicación de assertion*/
response = WS.sendRequest(findTestObject('Api_DolarSi/DolarSi'))

/*Creación de variable result para manejo de respuesta API*/
def slurper = new JsonSlurper()
def result = slurper.parseText(response.getResponseText())

for (int i = 0; i < 2; i++) {
	
	String valueWeb = valorWeb[i].replace('$ ', "") /*eliminación de caracter especial $ de valor web para comparación*/
	String valueApi = result[i].casa.compra /*extracción de valor de respuesta api*/
	
	/*comparación de valores web - api*/
	if(valueWeb == valueApi) {
		println "Despliegue de valor correcto " + valueApi
	}else {
		keywordUtil.markFailed() 
	}

}

