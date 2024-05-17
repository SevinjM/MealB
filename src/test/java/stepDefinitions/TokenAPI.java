package stepDefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloadBuilder.TokenRequestBuilder;
import utils.Base;

public class TokenAPI {
	String strPayload;
	Response response;
	
	@Given("User creates request data with {string} and {string}")
	public void user_creates_request_data_with_and(String username, String password) throws JsonProcessingException {
		
		// Create payload
		TokenRequestBuilder payload=new TokenRequestBuilder();
			payload.setUsernameOrEmailAddress(username);
			payload.setPassword(password);
		// convert payload to string
		ObjectMapper objMap=new ObjectMapper();
		strPayload=objMap.writerWithDefaultPrettyPrinter().writeValueAsString(payload);
	    System.out.println(strPayload);
	}

	@Given("User submits request to TOKEN api")
	public void user_submits_request_to_TOKEN_api() {
		// read url from config file
		String url=Base.property.getProperty("TOKEN_URL");
		
		// send post request and generate token
		   response=given()
						.body(strPayload)
						.contentType(ContentType.JSON)
					 .when()
					     .post(url);
		  response.prettyPrint();
	}

	@Given("User validates if status code is {int}")
	public void user_validates_if_status_code_is(int expectedStatusCode) {
		
		  int actualStatusCode=response.getStatusCode();
		  
		  System.out.println("Status Code: "+actualStatusCode);
		  
		  assertEquals(expectedStatusCode, actualStatusCode);
		  
	}

	@Given("User validated token is generated")
	public void user_validated_token_is_generated() {
		
	  String token=JsonPath.read(response.asString(), "$.result.accessToken");
	  System.out.println(token);
	  assertNotNull(token);
	    
	}

	
	@Given("User validates response schema")
	public void user_validates_response_schema() {
		
		response.then().assertThat()
		.body(matchesJsonSchemaInClasspath("schema/TokenApiResponseSchema.json"));
		
	    
	}


}
