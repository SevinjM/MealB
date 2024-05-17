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

public class DeleteExpenseSteps extends ReusableMethods{
	List<Integer>expenseIDList;
	String payload;
	Response response;
	Scenario scenario;
	
	
	@Before
	public void keepScenario(Scenario scenario){
		this.scenario=scenario;
	}
	
	@Given("User gets all expenses")
	public void user_gets_all_expenses() {
		 // Submit GET request
		  response=given()
						.header("Authorization", "Bearer "+getToken())
					  .when()
					     .get(property.getProperty("GetAllExpenses_URL"));
	      response.prettyPrint();
	      //write value to cucumber report
	      scenario.write(response.asPrettyString());
	}

	@When("User retrieves expense IDs")
	public void user_retrieves_expense_IDs() {
		
		expenseIDList=JsonPath.read(response.asString(), "$.result[*].id");
	    System.out.println(expenseIDList);
	}

	@Then("User deletes expenses one-by-one with expense ID")
	public void user_deletes_expenses_one_by_one_with_expense_ID() {

		for (Integer eachID : expenseIDList) {
			response=submitDeleteExpenseRequest(eachID);
			assertEquals(200, response.getStatusCode());
			scenario.write("Expense with ID number "+eachID+" deleted");
		}
		
		
	}

}
