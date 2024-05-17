package stepDefinitions;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.util.List;

import com.jayway.jsonpath.JsonPath;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import utils.ReusableMethods;

public class GetMonthExpense extends ReusableMethods {
	Response response;
	Scenario scenario;
	

	@Before
	public void keepScenario(Scenario scenario){
		this.scenario=scenario;
	}
	
	@Given("User sends GET request to {string} api")
	public void user_sends_GET_request_to_api(String url) {
		
		        response=given()
							.header("Authorization", "Bearer "+getToken())
						  .when()
						    .get(property.getProperty(url));
		        response.prettyPrint();	
		        scenario.write(response.asPrettyString());
	   
	}

	@When("User validate status code is {int}")
	public void user_validate_status_code_is(int statusCode) {
				
		scenario.write("Expected status Code: "+statusCode);
		scenario.write("Actual status Code: "+response.getStatusCode());
		
		assertEquals(statusCode,response.getStatusCode() );

		
	}

	@Then("User validates results shows past seven months")
	public void user_validates_results_shows_past_seven_months() {
	    
		List<String>months=JsonPath.read(response.asString(),"$.months[*]");
		scenario.write("Count of months: "+months.size());	
		assertEquals(7, months.size());
		
	}

}
