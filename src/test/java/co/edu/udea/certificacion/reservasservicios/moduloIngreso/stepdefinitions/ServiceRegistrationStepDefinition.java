package co.edu.udea.certificacion.reservasservicios.moduloIngreso.stepdefinitions;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.Service;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.questions.ServiceRegistrationIncompleteValidation;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.questions.ServiceRegistrationValidation;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.ServiceRegistrationEnterThe;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.ServiceRegistrationOpenThe;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.questions.AvailabilityCreationValidation;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.CreateAvailabilityEnterThe;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.SharedUserData;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.ValidationMessages;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;

public class ServiceRegistrationStepDefinition {

    private Actor user() {
        return OnStage.theActorInTheSpotlight();
    }

    Service service = new Service();

    @Given("that I am on the service registration page")
    public void thatIAmOnTheServiceRegistrationPage() {
        user().attemptsTo(ServiceRegistrationOpenThe.browser());
    }

    @When("I enter the {string} and the {string} about the service")
    public void iEnterTheAndTheAboutTheService(String string1, String string2) {
        int numero = new java.util.Random().nextInt(999) + 1;
        service.setName(string1 + " " + numero);
        service.setDescription(string2);
        user().attemptsTo(ServiceRegistrationEnterThe.information(service));
        SharedUserData.setRegisteredService(service);
    }

    @Then("I can see a message indicating that the service was successfully registered")
    public void iCanSeeAMessageIndicatingThatTheServiceWasSuccessfullyRegistered() {
        GivenWhenThen.then(user()).should(seeThat(ServiceRegistrationValidation.successful(), equalTo("Servicio registrado correctamente.")));
    }

    @When("I enter the name of the service but leave the description empty")
    public void iEnterTheNameOfTheServiceButLeaveTheDescriptionEmpty() {
        service.setName("Amapolas");
        service.setDescription("");
        user().attemptsTo(ServiceRegistrationEnterThe.information(service));
    }

    @Then("I can see a message indicating that the description is required")
    public void iCanSeeAMessageIndicatingThatTheDescriptionIsRequired() {
        GivenWhenThen.then(user()).should(seeThat(ServiceRegistrationIncompleteValidation.successful(),
            anyOf(
                equalTo(ValidationMessages.EMPTY_FIELD_SPANISH),
                equalTo(ValidationMessages.EMPTY_FIELD_ENGLISH)
            )));
    }

    @When("I create an availability slot for the registered service")
    public void iCreateAnAvailabilitySlotForTheRegisteredService() {
        user().attemptsTo(CreateAvailabilityEnterThe.information());
    }

    @Then("I can see a message indicating that the availability was successfully created")
    public void iCanSeeAMessageIndicatingThatTheAvailabilityWasSuccessfullyCreated() {
        GivenWhenThen.then(user()).should(
            seeThat(AvailabilityCreationValidation.successful(),
                equalTo("Franja de disponibilidad creada correctamente."))
        );
    }
}
