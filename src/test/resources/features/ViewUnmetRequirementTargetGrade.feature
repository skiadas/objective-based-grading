Feature: ViewUnmetRequirementTargetGrade
  shows the unmet requirements for the target grade (if they are missing requirements inside the basic, core, or extra)

  Background:
    Given I am a student
    Given I am logged in

  Scenario: Student can see unmet requirements
    Given I am enrolled in class
    When I request for target grade unmet requirements
    Then I can see the unmet requirement

  Scenario: Student cans see one unmet requirement
    Given I am enrolled in class
    When I request for target grade unmet requirements
    Then I can see the one category that is missing

  Scenario: Student gets error when not enrolled in class
    When I request for target grade unmet requirements
    Then An error message of not enrolled in class

  Scenario: Student gets error when given wrong (invalid) letter grade
    Given I am enrolled in class
    When I request for "x" target grade
    Then An error message of invalid letter grade


