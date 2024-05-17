package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.Set;

import com.jayway.jsonpath.JsonPath;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import utils.ReusableMethods;

public class PersonalInfoFlag extends ReusableMethods{
static Response response;
	String flag;
	Scenario scenario;
	
	@Before
	public void setUp(Scenario scenario) {
		this.scenario=scenario;
	}
	
	
	
	@Given("I set flag is {string}")
	public void i_set_flag_is(String flag) {
	    this.flag=flag;
	    scenario.write("Flag is:  "+flag);
	}
	
	
	@When("I submit GET request to {string}")
	public void i_submit_GET_request_to(String url) {
		if(flag.equalsIgnoreCase("true")) {
			 response=given()
						.header("Authorization", "Bearer "+getToken())
					  .when()
					     .get(property.getProperty(url));
	        response.prettyPrint();
	        scenario.write(response.asPrettyString());
		}
	}

	@When("I validates if the status code is {int}")
	public void i_validates_if_the_status_code_is(int expectedStatusCode) {
		
		if(flag.equalsIgnoreCase("true")) {	
			int actualStatusCode=response.getStatusCode();
			//System.out.println("Expected Status Code: "+expectedStatusCode+"---  Actual Status Code: "+actualStatusCode);
			scenario.write("Expected Status Code: "+expectedStatusCode+"---  Actual Status Code: "+actualStatusCode);

			assertEquals(expectedStatusCode, actualStatusCode);
	   }
	}

	@Then("I validates {string} in response")
	public void i_validates_in_response(String element) {
		 Map<String,Double> respElements=JsonPath.read(response.asString(), "$.result");
		    // from Map get the elements name
		   Set<String>keys=respElements.keySet();
		   
		   
			 assertTrue(keys.contains(element));
			 scenario.write(element +":   validation done");
		   }
	    
	

}
