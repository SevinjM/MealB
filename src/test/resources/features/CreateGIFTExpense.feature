Feature: Create GIFT Expense

@giftExpense   @apiRegression
 Scenario Outline: Create GIFT Type Expense
	Given User creates GIFT request body "<name>","<amount>","<expenseDateTime>","<giftRecipient>" for GIFT expense API
  And User submits POST request to "CreateExpense_URL" api
  And User validates the status code is 200

  
 Examples: 
    |name		  		 |amount  |expenseDateTime    |giftRecipient   |
    |Birthday GIFT |66.98   |04/16/2023 01:36:29|Avanger Students|
  