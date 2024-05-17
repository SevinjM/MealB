package omdbHomework;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import com.jayway.jsonpath.JsonPath;

public class TestHomework extends OMDBmethods{
	String response;
	String imdbID;
	List<String>titleList=new ArrayList<String>();
	List<String>resultList=new ArrayList<String>();
	
	@Test
	public void homework() {
		//1. Using search method, search for all items that match the search string "stem"
		  response=search("stem", 1);
		//2. Assert that the result should contain at least 30 items
		  String totalnum=JsonPath.read(response, "$.totalResults");
		  System.out.println(totalnum);
		  int totalResults=Integer.valueOf(totalnum);
		  assertTrue(totalResults>30);
		
		  //3. Assert that the result contains items titled 
		//   The STEM Journals and Activision: STEM - in the Videogame Industry
		  int totalPage=(totalResults/10)+1; 
		 List<String> titleListTotal=new ArrayList<>();
		 List<String> resultListTotal=new ArrayList<>(); 
		 
		 for (int i = 1; i <=totalPage; i++) {
			 
			  response =search("stem", i);
			  titleList=JsonPath.read(response, "$.Search[*].Title");
			  titleListTotal.addAll(titleList);
			  
			  //titleList.addAll((Collection<? extends String>) JsonPath.read(response, "$.Search[*].Title"));
			  
			  resultList=JsonPath.read(response, "$.Search[*]");
			  resultListTotal.addAll(resultList);
			  
			  //resultList.addAll((Collection<? extends String>) JsonPath.read(response, "$.Search[*]"));

		}
		//3. Assert that the result contains items titled The STEM Journals and Activision: STEM - in the Videogame Industry

		assertTrue(titleListTotal.contains("The STEM Journals"));
		assertTrue(titleListTotal.contains("Activision: STEM - in the Videogame Industry"));
	
	
		//4. From the list returned by search above,
		//get imdbID for item titled Activision: STEM - in the Videogame Industry 

		for(Object eachResult: resultListTotal) {
			if(JsonPath.read(eachResult, "$.Title").equals("Activision: STEM - in the Videogame Industry")) {
				 imdbID=JsonPath.read(eachResult, "$.imdbID");
			}
		}
		
		//and use it to get details on this movie using get_by_id method.
		response =getByID(imdbID);
		
		//5. Assert that the movie was released on 23 Nov 2010 and was directed by Mike Feurstein
		String released=JsonPath.read(response, "$.Released");
		String directed=JsonPath.read(response, "$.Director");
		
		System.out.println(released);
		System.out.println(directed);
		
		assertEquals(released, "23 Nov 2010");
		assertEquals(directed, "Mike Feurstein");
		
		//6. Using get_by_title method, get item by title "The STEM Journals" and 
		//assert that the Plot contains the string Science, Technology, Engineering and Math 
		//and has a "Runtime" of 22 minutes
		response=getByTitle("The STEM Journals");
		
		String runTime=JsonPath.read(response, "$.Runtime");
		System.out.println("Runtime: "+runTime);		
		assertEquals("22 min", runTime);
		
		String plot=JsonPath.read(response, "$.Plot");
		System.out.println("Plot: "+plot);		
		assertTrue(plot.contains("science, technology, engineering and math"));
	
	
	}

}



