package StepDef;

import Utility.ExcelHandler;
import Utility.PropertiesFileReader;
import Utility.TestDataHandler;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Map;
import java.util.Properties;



public class TestDataReadingStepDef 
{
	PropertiesFileReader obj= new PropertiesFileReader();
	
	TestDataHandler testdata=new TestDataHandler();
	
	@Given("^Read test data for testcase_(\\d+)$")
	public void read_test_data_for_testcase_(int arg1) throws Throwable 
	{
		Properties properties=obj.getProperty();  
		
		Map<String,String> TestDataInMap= ExcelHandler.getTestDataInMap(properties.getProperty("testdatafilepath"), properties.getProperty("sheetname"), "TestCase_001");
		System.out.println(TestDataInMap.get("Skill_7"));
		
		testdata.setTestDataInMap(TestDataInMap);	
		
	}
	
	@When("^Read test data for skill two$")
	public void read_test_data_for_skill_two() throws Throwable
	{
		Map<String,String> testDataInMap=testdata.getTestDataInMap();
		System.out.println(testDataInMap.get("Skill_2"));
	}

	@Then("^Read test data for skill Three$")
	public void read_test_data_for_skill_Three() throws Throwable 
	{
		Map<String,String> testDataInMap=testdata.getTestDataInMap();
		System.out.println(testDataInMap.get("Skill_3"));
		
	}
	
	
	

}
