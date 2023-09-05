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

def id_selector = ['c1', 'c2']

def valorWeb = new String[2]

for (int i = 0; i < 2; i++) {
    value_compra = (id_selector[i])

    (valorWeb[i]) = WebUI.getText(findTestObject('Page_Dolarsi/valor_dolar_compra', [('id') : value_compra]))
}

response = WS.sendRequest(findTestObject('Api_DolarSi/DolarSi'))

def slurper = new JsonSlurper()

def result = slurper.parseText(response.getResponseText())

for (int i = 0; i < 2; i++) {
	
	String valueWeb = valorWeb[i].replace('$ ', "")
	String valueApi = result[i].casa.compra
	
	if(valueWeb == valueApi) {
		println "Despliegue de valor correcto " + valueApi
	}else {
		keywordUtil.markFailed()
	}

}

