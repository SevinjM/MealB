Feature: Dashboard API

@DashboardAPI    @apiRegression  @APISmoke  
Scenario: Get Dashboard API response

When User submit GET request to "Dashboard_URL"
And User validates if the status code is 200
Then User validates elements in response 
		|otherExpenses							|
		|mealExpenses								|
		|entertainmentExpenses			|
		|mealAndEntertainmentExpenses|
		|travelExpenses							|
		|giftExpenses|
		|deductibleGiftExpenses|
		|taxSaving|
		|numberOfEmployees|

		
		