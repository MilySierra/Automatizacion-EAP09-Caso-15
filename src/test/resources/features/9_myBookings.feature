Feature: as a customer I want to consult my bookings to keep track of the services I have reserved on the platform

  @requiresSetupForHU19
  Scenario: customer with existing bookings sees them in the table
    Given that I am a customer with an existing booking
    When I navigate to my bookings page
    Then I can see all my bookings in the table

  Scenario: customer with no bookings sees an empty state message
    Given that I am a new customer with no bookings
    When I navigate to my bookings page
    Then I can see a message indicating that I have no bookings
