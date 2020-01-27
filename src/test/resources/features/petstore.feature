Feature: Validating petstore service

  @smoke
  Scenario: Add a pet
    Given User is in petstore swagger page
    When User create a new pet
    Then The pet is added in store successfully

  @smoke
  Scenario: Find a pet by ID
    Given User is in petstore swagger page
    When User search a pet which id is 102
    Then The pet is found and search result returned successfully

  @smoke
  Scenario: Add a pet then find the new added pet
    Given User is in petstore swagger page
    When User create a new pet
    Then The pet is added in store successfully
    When User search a pet which id is 103
    Then The pet is found and search result returned successfully
    And The pet id are same


