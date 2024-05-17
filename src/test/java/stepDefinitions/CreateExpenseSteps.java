package stepDefinitions;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloadBuilder.GiftExpenseRequestBuilder;
import payloadBuilder.OtherExpenseRequestBuilder;
import utils.ReusableMethods;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import com.jayway.jsonpath.JsonPath;

public class CreateExpenseSteps extends ReusableMethods {
	
	String payload;
	static Response response;
	Scenario scenario;
	String flag="";
	
	@Before
	public void keepScenario(Scenario scenario){
		this.scenario=scenario;
	}
	
	@Given("User set {string}")
	public void user_set(String flag) {
	    this.flag=flag;
	}
	
	
	@Given("User creates request body {string},{string},{string},{string},{string} for expense API")
	public void user_creates_request_body_for_expense_API(String name, String amount, String expenseDateTime, String expenseType, String otherExpenseNameId) {
		// create payload 
		 payload=createOtherExpensePayload(name, amount, expenseDateTime, expenseType, otherExpenseNameId);
		// write request data/payload to report
		 scenario.write(payload);
		 scenario.embed(payload.getBytes(),"application/json" );

		
	}

	@Given("User submits POST request to {string} api")
	public void user_submits_POST_request_to_api(String url) {
		
	     // Submit POST request to create expense
	   		response=submitCreateExpenseRequest(payload);
	   	// write response to cucumber report	
	   		scenario.write(response.asPrettyString());
	   	    scenario.embed(response.asPrettyString().getBytes(),"application/json" );
	   
		
	    
	}

	@Given("User validates the status code is {int}")
	public void user_validates_the_status_code_is(int expectedStatusCode) {
			
			int actualStatusCode=response.getStatusCode();
			scenario.write("Status code::: "+actualStatusCode);
			assertEquals(expectedStatusCode, actualStatusCode);
		
	}
	
	
	@Then("User validates {string} of {string} in response")
	public void user_validates_of_in_response(String value, String element) {
	   
		 String nameValue=JsonPath.read(response.asString(), "$.result.name");
		 scenario.write("name: "+nameValue);
		 assertEquals(value, nameValue);
		
	}
	
	@Given("User creates GIFT request body {string},{string},{string},{string} for GIFT expense API")
	public void user_creates_GIFT_request_body_for_GIFT_expense_API(String name, String amount, String expenseDateTime, String giftRecipient) {
		// create payload 
		  payload=createGIFTExpensePayload(name, amount, expenseDateTime, giftRecipient);
		
		// write request data/payload to report
		 scenario.write(payload);
		 scenario.embed(payload.getBytes(),"application/json" );
		
	}
	
	@Given("User creates TRAVEL request body {string},{string},{string} for TRAVEL expense API")
	public void user_creates_TRAVEL_request_body_for_TRAVEL_expense_API(String name, String amount, String expenseDateTime) {		
		// create payload 
		  payload=createTRAVELExpensePayload(name, amount, expenseDateTime);
		
		// write request data/payload to report
		 scenario.write(payload);
		 scenario.embed(payload.getBytes(),"application/json" );

	}



	@Given("User validate each {string} in response")
	public void user_validate_each_in_response(String element) {
		String jsonpath="$.result."+element;
		Object value=JsonPath.read(response.asString(), jsonpath);
		scenario.write(element+" value: "+value);
	}

}