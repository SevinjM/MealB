Feature: Personal Info API

@PersonalInfo1    @apiRegression  @APISmoke 
Scenario: Get Personal Info

When User submit GET request to "PersonalInfo_URL"
And User validates if the status code is 200
Then User validates elements in response        
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