Feature: as a customer I want to create a booking for an available service with a selected provider to access the service according to my schedule

  @requiresLoginCustomer
  Scenario: successful booking creation
    Given that I am on the offers page
    When I select an active offer and an available slot
    Then I can see a message indicating that the booking was successfully created

  @requiresLoginCustomer
  Scenario: booking creation without selecting required fields
    Given that I am on the offers page
    When I open the booking modal without selecting a slot
    Then I can see a message indicating that there are no slots available