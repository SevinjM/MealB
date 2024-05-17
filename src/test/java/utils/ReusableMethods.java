package utils;

import static io.restassured.RestAssured.given;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloadBuilder.GiftExpenseRequestBuilder;
import payloadBuilder.OtherExpenseRequestBuilder;
import payloadBuilder.TokenRequestBuilder;
import payloadBuilder.TravelExpenseRequestBuilder;

public class ReusableMethods extends Base{
	/**
	 * @author ElshanRasulov
	 * @return Response
	 * @see This method submit POST request to delete expenses 
	 */
	public Response submitDeleteExpenseRequest(Integer id) {
		Response response=given()
							.header("Authorization", "Bearer "+getToken())
						  .when()
						     .post(property.getProperty("Delete_URL")+id);
		return response;
	}
	
	/**
	 * @author ElshanRasulov
	 * @return String
	 * @see This method create payload for TRAVEL type expense and convert it to String 
	 */
	
	public String createTRAVELExpensePayload(String name,String amount, String expenseDateTime){
		TravelExpenseRequestBuilder payload=new TravelExpenseRequestBuilder();
			payload.setName(name);
			payload.setAmount(amount);
			payload.setExpenseDateTime(expenseDateTime);
			payload.setExpenseType("Travel");
			
		return convertObjectToString(payload);	
	}
	
	
	/**
	 * @author ElshanRasulov
	 * @return String
	 * @see This method create payload for GIFT type expense and convert it to String 
	 */
	
	public String createGIFTExpensePayload(String name,String amount, String expenseDateTime, String giftRecipient) {
		
		GiftExpenseRequestBuilder payload=new GiftExpenseRequestBuilder();
		payload.setName(name);
		payload.setAmount(amount);
		payload.setExpenseDateTime(expenseDateTime);
		payload.setExpenseType("Gift");
		payload.setGiftRecipient(giftRecipient);
	
	return convertObjectToString(payload);
	
	}
	
	
	/**
	 * @author ElshanRasulov
	 * @return String
	 * @see This method submits POST request to create expense 
	 */
	
	public Response submitCreateExpenseRequest(String payload) {

		Response response=given()
							.header("Authorization", "Bearer "+getToken())
							.body(payload)
							.contentType(ContentType.JSON)
						.when()
							.post(property.getProperty("CreateExpense_URL"));
		response.prettyPrint();
		
		return response;
	}
	
	
	
	
	/**
	 * @author ElshanRasulov
	 * @return String
	 * @see This method create payload for OTHER type expense and convert it to String 
	 */
	
	public String createOtherExpensePayload(String name,String amount, String expenseDateTime,String expenseType, String otherExpenseNameId) {
		
		OtherExpenseRequestBuilder payload=new OtherExpenseRequestBuilder();
		payload.setName(name);
		payload.setAmount(amount);
		payload.setExpenseType(expenseType);
		payload.setExpenseDateTime(expenseDateTime);
		payload.setOtherExpenseNameId(otherExpenseNameId);
		
	  return convertObjectToString(payload);
				
	}
	
	
	/**
	 * @author ElshanRasulov
	 * @return String
	 * @see This method submits POST request to generate new token 
	 */
	public String getToken() {
		
		Response tokenResp=given()
   							.contentType(ContentType.JSON)
   							.body(createTokenPayload())
   						  .when()
   						  	.post(property.getProperty("TOKEN_URL"));		
		tokenResp.prettyPrint();
		
		String token=JsonPath.read(tokenResp.asString(), "$.result.accessToken");
		
		return token;
	
	}
	
	
	

	public String createTokenPayload() {
		
	 TokenRequestBuilder tokenReqBuilder=new TokenRequestBuilder();
		tokenReqBuilder.setUsernameOrEmailAddress(property.getProperty("userID"));
		tokenReqBuilder.setPassword(property.getProperty("password"));	
		
		return convertObjectToString(tokenReqBuilder);
	}
	
	
	
	
	
	public String convertObjectToString(Object object) {
		String strPayload=null;   
		try {
			ObjectMapper objMapper=new ObjectMapper();
			 strPayload = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	return strPayload;	
	}


}
