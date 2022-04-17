Feature: View assigned attempt

  Background:
    Given a course C exisits
    Given a student S exists
    Given I have the authority to find an enrollment by course and student

  Scenario: show a list of "ASSIGNED" attempts
    Given an enrollment E for C and S exists
    Given E has some attempts which involves "ASSIGNED" attempts
    When request a list of "ASSIGNED" attempts
    Then show a list of "ASSIGNED" attempts

  Scenario: trying to show "ASSIGNED" attempts but it does not exist
    Given an enrollment E for C and S exists
    Given E has some attempts which DOES NOT involve "ASSIGNED" attempts
    When request a list of "ASSIGNED" attempts
    Then show nothing (or show an empty list of "ASSIGNED" attempts)

  Scenario: trying to show "ASSIGNED" attempts for invalid enrollment
    Given an enrollment for S and C does not exist
    When request a list of "ASSIGNED" attempts
    Then report an invalid enrollment error
