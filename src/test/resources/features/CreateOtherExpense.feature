Feature: Create Expense - Type Other

@otherExpenseee   @apiRegression
 Scenario Outline: Create Other Type Expense
  Given User creates request body "<name>","<amount>","<expenseDateTime>","<expenseType>","<otherExpenseNameId>" for expense API
  And User submits POST request to "CreateExpense_URL" api
  And User validates the status code is 200
  Then User validates "<value>" of "name" in response

   Examples: 
    |name		  	|amount  |expenseDateTime    |expenseType|otherExpenseNameId| value		 		|
    |Service Fee| 444.99 |07/06/2020 01:36:29|Other      |1									| Electricity |
    |Service Fee| 444.99 |07/06/2020 01:36:29|Other      |2									| Rent			  |
    |Service Fee| 444.99 |07/06/2020 01:36:29|Other      |3									| Gas				  |
    |Service Fee| 444.99 |07/06/2020 01:36:29|Other      |4									| Service Fee |
    

 