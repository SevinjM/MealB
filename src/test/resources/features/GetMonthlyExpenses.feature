Feature: Get Monthly Expenses

@monthlyExpense
Scenario: Validate Get Monthly Expenses shows 7 months
	
	Given User sends GET request to "GetMonthlyExpenses_URL" api
	When User validate status code is 200
	Then User validates results shows past seven months

