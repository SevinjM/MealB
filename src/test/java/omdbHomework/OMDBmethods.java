package omdbHomework;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
public class OMDBmethods {
	
	String url="http://www.omdbapi.com/";
	String apiKey="283c4172";
	
	public String search(String text, int page) {
		Response resp=given()
						.param("s", text)
						.param("apikey", apiKey)
						.param("page",page)
					.when()
					     .get(url);
		 return resp.prettyPrint();
	}
	
	
	//getByID(Sting): returns the result based on "imdbID" matches with that input id(e.g. tt999999)
	// Query uses "i" parameters as specified here
	
	public String getByID(String id) {
		Response resp=given()
						.param("i", id)
						.param("apikey", apiKey)
					.when()
					     .get(url);
		 return resp.prettyPrint();
	}
	

	//getByTitle(Sting): returns the result based on input string where "Title" exactly matches
	// Query uses "t" parameters as specified here
	
	
	public String getByTitle(String title) {
		Response resp=given()
						.param("t", title)
						.param("apikey", apiKey)
					.when()
					     .get(url);
		 return resp.prettyPrint();
	}
}
