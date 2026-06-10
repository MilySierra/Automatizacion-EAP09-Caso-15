Feature: as a provider I want to activate or deactivate my services to control which ones are available for booking on the platform

  @requiresProviderForHU10
  Scenario: successful service activation
    Given that I am on the services page with an inactive service
    When I activate the service
    Then I can see a message confirming the service status was updated

  @requiresProviderForHU10
  Scenario: successful service deactivation
    Given that I am on the services page with an active service
    When I deactivate the service
    Then I can see a message confirming the service status was updated
