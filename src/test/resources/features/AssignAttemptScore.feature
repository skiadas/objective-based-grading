Feature: Instructor can assign a score to an attempt

  Scenario: Valid instructor can assign a score to a valid attempt
    Given existing course A
    Given An existing attempt that belongs to an enrollment for course A
    Given I am an existing instructor that does teach course A
    Given I am on the existing attempt's page
    When I try to add a score to the attempt
    Then Attempt is updated and displayed with new score

  Scenario: Valid instructor cannot score a valid attempt from a course they do not teach
    Given existing course B
    Given An existing attempt that belongs to an enrollment for course B
    Given I am an existing instructor that does not teach course B
    Given I am on the existing attempt's page
    When I try to add a score to the attempt
    Then Attempt is not updated with new score
    And An error message is displayed