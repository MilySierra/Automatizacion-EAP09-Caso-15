Feature: as a user I want to sign up with my basic information to access the services available based on my profile

  Scenario: successful customer registration
    Given that I am on the registration page
    When I enter my basic information as customer
    Then I can see the login page

  Scenario: successful provider registration
    Given that I am on the registration page
    When I enter my basic information as provider
    Then I can see the login page

  Scenario: registration with fields empty - exception
    Given that I am on the registration page
    When I enter my basic information but leave one field empty
    Then I can see a message indicating that this field is required

  Scenario: registration with a password that does not meet the password policy - exception
    Given that I am on the registration page
    When I enter my basic information but with an invalid password
    Then I can see a message indicating that this password is invalid

  Scenario: registration with an invalid email - exception
    Given that I am on the registration page
    When I enter my basic information but with an invalid email
    Then I can see a message indicating that this email is invalid



