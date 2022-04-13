Feature: Instructor can delete Objective

  Scenario: Instructor can delete objective from Attempt
    Given I have an existing Attempt
    Given I have an existing objective "Objective 1"
    Given I am logged in as valid instructor
    Given I can access to this existing Attempt Information
    When Requesting to Delete "Objective 1" from Attempt
    Then "Objective 1" and the Attempt has been deleted from AttemptMap

  Scenario: Student cannot delete objective from Attempt
    Given I am Attempted an Objective for course
    Given I am logged in as valid student
    Given I can access to this existing Attempt Information
    When Requesting to Delete "Objective 1" from Attempt
    Then Error message appears that tells you that you are not a valid instructor or do not have permission to delete objective

  Scenario: Instructor cannot remove an Objective that does not exist
    Given I have an existing Attempt
    Given I have an existing objective "Objective 1"
    Given I am logged in as valid instructor
    Given I can access to this existing Attempt Information
    When Requesting to Delete "Objective 2" from Attempt
    Then Error message appears that tells you the objective is invalid or does not exist
