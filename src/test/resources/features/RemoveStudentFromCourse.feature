Feature: Instructor can remove student from Course

  Scenario: Instructor can remove student from course
    Given I have an assigned course
    Given I am logged in as valid instructor
    Given I am at the course roster page
    Given "Sam" is a student in the course
    When Requesting to remove "Sam" from course
    Then "Sam" has been removed from the course

  Scenario: Student cannot remove a student or themselves from course
    Given I am enrolled in course
    Given I am logged in as valid student
    Given I at the course roster page
    When Requesting to remove myself from course
    Then Error message appears that tells you that your are not a valid instructor for this course

  Scenario: Instructor cannot remove a student from a course they do not teach
    Given I have an assigned course
    Given I am logged in as valid instructor
    Given I am at a different course roster page
    Given "Sam" is a student in this other course
    When Requesting to remove "Sam" from other course
    Then Error message appears to you of not being valid instructor for this course