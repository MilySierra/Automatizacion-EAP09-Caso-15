@requiresProviderForHU17
Feature: as a customer I want to cancel an active booking to free up my slot when I can no longer attend the reserved service

  @requiresLoginCustomer
  Scenario: successful booking cancellation
    Given that I have an active booking in my bookings list
    When I cancel the booking
    Then I can see a message indicating the booking was successfully cancelled

