Feature: Instructor Can Delete Attempt

  Scenario: Valid instructor can delete valid attempt
    Given existing course A
    Given An existing attempt that belongs to an enrollment for course A
    Given I am an existing instructor that does teach course A
    Given I am on the instructor view of attempts page
    When I try to delete the existing attempt
    Then Attempt is removed from  database
    And Instructor view of attempts is updated

  Scenario: Invalid course instructor cannot delete attempts from a course they do not teach
    Given existing course B
    Given An existing attempt that belongs to an enrollment for course B
    Given I am an existing instructor that does not teach course B
    Given I am on the instructor view of attempts page
    When I try to delete the existing attempt
    Then Attempt is not removed
    And An error message is displayed