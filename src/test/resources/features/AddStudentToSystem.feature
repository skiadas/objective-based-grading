Feature: Admin can add student to system

  Scenario: Valid admin can add new student to system
    Given Student does not already exist
    And Admin is logged in
    And Admin is on add student screen
    When Admin enters and submits new student
    Then Student is added to database
    And success message is returned to Admin

  Scenario: Valid Admin is unable to add student when student already exists in system
    Given Admin is logged in
    And Admin is on add student screen
    When Admin enters student with information matching an already existing student's information
    Then Existing Student error is returned
    And Student is not added to database
