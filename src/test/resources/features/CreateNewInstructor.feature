Feature: Create new instructor

  Background:
    Given I have the authority to add instructors
    Given I have the authority to find instructors by id

  Scenario: successfully creating/registering a new instructor
    Given new instructor's information
    When register new instructor
    Then the instructor will be registered

  Scenario: try to create new instructor but an instructor with the same id exists so the attempt will fail
    Given existing instructor A in DB
    Given new instructor B's information with the same id as above
    When register new instructor B
    Then report and invalid instructor error
    And do not register B

