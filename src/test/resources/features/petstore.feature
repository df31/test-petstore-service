Feature: Validating petstore service

  @smoke
  Scenario Outline: Add a pet
    Given User is in petstore swagger page
    When User add a new <pet>
    Then The pet is added in store successfully
    Examples:
      | pet |
      | DOG |

  @smoke
  Scenario Outline: Find a pet by ID
    Given User is in petstore swagger page
    When User search a pet by <id>
    Then The pet is found and search result returned successfully
    Examples:
      | id  |
      | 201 |

  Scenario Outline: Add a pet then find the new added pet
    Given User is in petstore swagger page
    When User add a new <pet>
    Then The pet is added in store successfully
    When User search the newly added pet
    Then The pet is found and search result returned successfully
    And The pet id are same
    Examples:
      | pet  |
      | DOG  |
      | CAT  |
      | FISH |

  @negative
  Scenario Outline: add a invalid pet
    Given User is in petstore swagger page
    When User add a new <pet>
    Then User get error response with <error_code> and <error_message>
    Examples:
      | pet                | error_code | error_message          |
      | PET_HAS_INVALID_ID | 500        | something bad happened |


  @negative
  Scenario Outline: Search a non-existing pet by id
    Given User is in petstore swagger page
    When User search a pet by <id>
    Then User get error response with <error_code> and <error_message>
    Examples:
      | id                                                                          | error_code | error_message                                      |
      | 1000                                                                        | 404        | Pet not found                                      |
      | 100000000000000000000000000000000000000000000000000000000000000000000000000 | 404        | ava.lang.NumberFormatException: For input string:  |
      | abc                                                                         | 404        | java.lang.NumberFormatException: For input string: |
      | *                                                                           | 404        | java.lang.NumberFormatException: For input string: |
      | @                                                                           | 404        | java.lang.NumberFormatException: For input string: |

