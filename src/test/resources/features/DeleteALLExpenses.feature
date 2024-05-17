Feature: Delete all expenses

@deleteExpense
Scenario: Delete ALL expense
Given User gets all expenses
When User retrieves expense IDs
Then User deletes expenses one-by-one with expense ID