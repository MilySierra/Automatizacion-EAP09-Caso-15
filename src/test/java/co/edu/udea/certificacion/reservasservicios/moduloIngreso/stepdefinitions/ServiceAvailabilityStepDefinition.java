package co.edu.udea.certificacion.reservasservicios.moduloIngreso.stepdefinitions;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.ServiceAvailability;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.User;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.questions.ServiceAvailabilitySetValidation;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.questions.ServiceAvailabilityValidation;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.questions.ServiceAvailabilityValidationError;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.ProviderOpen;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.ProviderSetService;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.RegisterDefault;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.ServiceAvailabilityMapping;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.UserCreation;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import net.serenitybdd.screenplay.actors.OnStage;

public class ServiceAvailabilityStepDefinition {

    private String existingAvailabilityDate;

    private Actor user() {
        return OnStage.theActorInTheSpotlight();
    }

    @Given("I am registered provider with at least one registered service with general business hours defined")
    public void iAmAnAuthenticatedProviderAtTheServiceAvailabilityManagementPage() {
        User provider = UserCreation.randomProvider();
        user().attemptsTo(RegisterDefault.provider(provider));
    }

    @And("I am an authenticated at the service availability management page")
    public void iHaveAtLeastOneRegisteredServiceWithGeneralBusinessHoursDefined() {
        user().attemptsTo(ProviderOpen.serviceAvailabilityPage());
    }

    @When("I set up a valid time slot availability for my service:")
    public void iSetUpAValidTimeSlotAvailabilityForMyService(DataTable dataTable) {
        ServiceAvailability serviceHours = ServiceAvailabilityMapping.getServiceAvailability(dataTable);
        user().attemptsTo(ProviderSetService.availability(serviceHours));
    }

    @Then("I can see a message of correct definition of availability")
    public void iCanSeeAMessageOfCorrectDefinitionOfAvailability() {
        GivenWhenThen.then(user())
        .should(seeThat(ServiceAvailabilityValidation.sucessfulCreated()));
    }

    @When("I try to define a time slot outside the business hours:")
    public void iTryToDefineATimeSlotOutsideTheBusinessHours(DataTable dataTable) {
        ServiceAvailability serviceHours = ServiceAvailabilityMapping.getServiceAvailability(dataTable);
        user().attemptsTo(ProviderSetService.availability(serviceHours));
    }

    @Then("I see an error message of time slot outside the business hours")
    public void iSeeAnErrorMessageOfTimeSlotOutsideTheBusinessHours() {
        GivenWhenThen.then(user())
        .should(seeThat(ServiceAvailabilitySetValidation.outsideBusinessHours()));
    }

    @And("I already have a time slot defined for the service:")
    public void iAlreadyHaveATimeSlotDefinedForTheService(DataTable dataTable) {
        ServiceAvailability serviceHours = ServiceAvailabilityMapping.getExistingAvailability(dataTable);
        user().attemptsTo(ProviderSetService.availability(serviceHours));
        existingAvailabilityDate = serviceHours.getDate();
    }

    @When("I try to define an overlapping time slot:")
    public void iTryToDefineAnOverlappingTimeSlot(DataTable dataTable) {
        ServiceAvailability serviceHours = ServiceAvailabilityMapping.getServiceAvailability(dataTable);
        serviceHours.setDate(existingAvailabilityDate);
        user().attemptsTo(ProviderSetService.availability(serviceHours));
    }

    @Then("I can see an overlapping error message")
    public void iCanSeeAnOverlappingErrorMessage() {
        GivenWhenThen.then(user())
        .should(seeThat(ServiceAvailabilityValidationError.overlappingHours()));
    }

}
