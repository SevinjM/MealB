Feature: Create TRAVEL Expense

@travelExpense   @apiRegression
 Scenario Outline: Create TRAVEL Type Expense
 	Given User set "<flag>"
	Given User creates TRAVEL request body "Birthday GIFT","66.98","04/16/2023 01:36:29" for TRAVEL expense API
  And User submits POST request to "CreateExpense_URL" api
  And User validates the status code is 200
  And User validate each "<element>" in response
  
 Examples: 
	|flag	|element|
	|true	|id|
	|false|name|
	|false|amount|
	|false|expenseType|
	|false|expenseDateTime|
	|false|businessPurpose|
	|false|natureOfGift|
	|false|giftRecipient|
	|false|vendorName|
	|false|destinationOfTravel|
	|false|company|
	|false|projectName|
	|false|placeAddress|
	|false|latitude|
	|false|travelExpenseTypeId|
	|false|otherRelationship|
	|false|receiptFile|
	|false|receiptUrl|
	|false|placeIcon|

	
	
	