Feature: Instructor can see list of courses

  Scenario: Instructor tries to get courselist and has multiple courses
    Given Instructor is logged in
    When Instructor navigates to courses screen
    Then return list of Courses

  Scenario: Instructor tries to get courselist but has no current courses
    Given Instructor is logged in
    When Instructor navigates to courses screen
    Then return empty list
    And return "no courses" message