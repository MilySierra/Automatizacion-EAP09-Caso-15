Feature: as a customer I want to consult available schedules and slots for a service to decide if I can make a reservation

  @requiresLoginCustomer
  Scenario: successful consultation of available schedules and slots
    Given that I am on the offers page
    When I select a valid offer and a valid date
    Then I can see the available slots for the selected date

  @requiresLoginCustomer
  Scenario: consultation with no slots available for the selected date
    Given that I am on the offers page
    When I select a valid offer and a date with no slots
    Then I can see a message indicating that there are no slots available