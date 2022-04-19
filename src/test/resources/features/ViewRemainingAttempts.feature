# Created by iangulyas at 4/18/22
Feature: ViewRemainingAttempts
  lets one view the number of attempts remaining

  Scenario: Student can view number of remaining attempts
    Given I Am a student
    Given I am logged in
    Given I Am at the student veiw of my enrolment
    When the page was loaded
    Then the number of remaing attempts should be there

  Scenario: Student makes a valid attempt request
    Given A Valid AttemptRequest
    Given A number of Remaining Attemps greater than 0
    When The Request is made
    Then the number of remaining attempts should decrease by one

  Scenario: Student makes a valid attempt request but has 0 attempts remaining
    Given I have made a Valid AttemptRequest
    Given I have 0 Remaining Attemps
    When The Request is made
    Then A error Message that says Invalid Attempt Number

  Scenario: Instructor Deletes an Attempt Request
    Given existing course A
    Given An existing attempt that belongs to an enrollment for course A
    Given I am an existing instructor that does teach course A
    Given I am on the instructor view of attempts page
    When I try to delete the existing attempt
    Then The The number of Remaining Attemps is Increased by one.

