Feature: Create TRAVEL Expense

@travelExpense   @apiRegression
 Scenario Outline: Create TRAVEL Type Expense
	Given User creates TRAVEL request body "<name>","<amount>","<expenseDateTime>" for TRAVEL expense API
  And User submits POST request to "CreateExpense_URL" api
  And User validates the status code is 200

  
 Examples: 
    |name		  		 |amount  |expenseDateTime    |
    |Birthday GIFT |66.98   |04/16/2023 01:36:29|
  