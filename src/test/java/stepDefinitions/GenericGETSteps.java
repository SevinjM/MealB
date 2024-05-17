package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jayway.jsonpath.JsonPath;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utils.ReusableMethods;

public class GenericGETSteps extends ReusableMethods{
    static Response response;
	    Scenario scenario;

	@Before
	public void keepScenario(Scenario scenario){
		this.scenario=scenario;
	}
	
	@When("User submit GET request to {string}")
	public void user_submit_GET_request_to(String url) {
			  // Submit GET request
		      response=given()
							.header("Authorization", "Bearer "+getToken())
						  .when()
						     .get(property.getProperty(url));
		      response.prettyPrint();
		      //write value to cucumber report
		      scenario.write(response.asPrettyString());
	   
	}
	
	
	@When("User validates if the status code is {int}")
	public void user_validates_if_the_status_code_is(int expectedStatusCode) {
		int actualStatusCode=response.getStatusCode();
		scenario.write("Expected Status Code: "+expectedStatusCode+"---  Actual Status Code: "+actualStatusCode);
		assertEquals(expectedStatusCode, actualStatusCode);
		  
		
	}
	
	@Then("User validates elements in response")
	public void user_validates_elements_in_response(List<String>elements) {
	    // get all elements under "$.result" from response and assign them to MAP
	   Map<String,Object> respElements=JsonPath.read(response.asString(), "$.result");

	    // from Map get the elements name
	   Set<String>keys=respElements.keySet();
	   
	   for (String eachElement : elements) {	   
		 assertTrue(keys.contains(eachElement));
		 scenario.write(eachElement +"   ---- element validated");
	   }
	   
	   
	   
	}

}
