Feature: as a user, I want to register a service with its basic information so I can offer it

  @requiresLoginProvider
  Scenario Outline: service registration successful
    Given that I am on the service registration page
    When I enter the <name> and the <description> about the service
    Then I can see a message indicating that the service was successfully registered
    Examples:
    |name|description|
    |"Beautiful Nails"|"Uñas bonitas a precio bajo"|
    |"Hasta el amanecer"|"Cabello, uñas y todo lo que necesites aquí con nosotras"|

  Scenario: service registration with description empty - exception
    Given that I am on the service registration page
    When I enter the name of the service but leave the description empty
    Then I can see a message indicating that the description is required
