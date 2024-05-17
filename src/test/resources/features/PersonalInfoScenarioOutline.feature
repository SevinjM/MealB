Feature: Personal Info API

@PersonalInfoOutLine    @apiRegression  @APISmoke 
Scenario Outline: Get Personal Info

When User submit GET request to "PersonalInfo_URL"
And User validates if the status code is 200
Then I validates "<elements>" in response

 Examples:	
		|elements           |
		|name               |
		|surname 		        |
		|middleName         |
		|companyName        |
		|userName           |
		|emailAddress       | 
		|phoneNumber        | 
		|address 		        |
		|address2           |
		|countryState       |
		|city               |
		|postalCode         | 
		|tenantName         | 
		|accountType        | 
		|birthDate          | 
		|businessType       |
		|profileImageUrl    |
		|numberOfEmployees  |
		|registrationNumber |
		|approximateAGI     | 
		|businessTypeId     | 
		|countryStateId     | 