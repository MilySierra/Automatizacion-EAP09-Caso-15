Feature: as a user, I want to log in with my credentials to access the features available to my profile

  Scenario: log in successful
    Given that I am on the log-in page
    When I enter my credentials
    Then I can see the home page

  Scenario Outline: log in with a nonexistent email
    Given that I am on the log-in page
    When I enter my credentials an unregistered <email>
    Then I can see a message indicating that this email is incorrect
    Examples:
    |email|
    |"william@udea.edu.co"|
    |"ocampo@udea.edu.co"|

  Scenario: log in with password empty
    Given that I am on the log-in page
    When I enter my credentials but leave the password empty
    Then I can see a message indicating that the password is required

  @requiresLoginCustomer
  Scenario: log out successful
    Given that I am on the home page
    When I close my session
    Then I can see the login page after logout
