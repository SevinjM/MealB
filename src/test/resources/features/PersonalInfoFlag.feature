Feature: Personal Info API FLAG

@PersonalInfoFlag    @apiRegression  @APISmoke 
Scenario Outline: Get Personal Info
Given I set flag is "<flags>"
When I submit GET request to "PersonalInfo_URL"
And I validates if the status code is 200
Then I validates "<elements>" in response

 Examples:		
	  |flags         |elements           |        
		|true          |name               |
		|false         |surname 		       |
		|false         |middleName         |
		|false         |companyName        |
		|false         |userName           |
		|false         |emailAddress       | 
		|false         |phoneNumber        | 
		|false         |address 		       |
		|false         |address2           |
		|false         |countryState       |
		|false         |city               |
		|false         |postalCode         | 
		|false         |tenantName         | 
		|false         |accountType        | 
		|false         |birthDate          | 
		|false         |businessType       |
		|false         |profileImageUrl    |
		|false         |numberOfEmployees  |
		|false         |registrationNumber |
		|false         |approximateAGI     | 
		|false         |businessTypeId     | 
		|false         |countryStateId     | 
