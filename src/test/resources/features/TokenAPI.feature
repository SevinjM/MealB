Feature: Token API

  @apiRegression  @smoke  @tokenApi 
  Scenario: Get token
    Given User creates request data with "ElshanRasul" and "Elshan123"
    And User submits request to TOKEN api
    And User validates if status code is 200
    And User validated token is generated
    #And User validates response schema
    
    
    
   