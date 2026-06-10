package co.edu.udea.certificacion.reservasservicios.moduloIngreso.stepdefinitions;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.questions.BookingSuccessValidation;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.CreateBookingEnterThe;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions.OpenBookingModal;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.CreateDates;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.Wait;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.containsString;

public class CreateBookingStepDefinition {

    private Actor user() {
        return OnStage.theActorInTheSpotlight();
    }

    @When("I select an active offer and an available slot")
    public void iSelectAnActiveOfferAndAnAvailableSlot() {
        String date = CreateDates.dateAfterNow(2);
        user().attemptsTo(CreateBookingEnterThe.forDate(date));
    }

    @Then("I can see a message indicating that the booking was successfully created")
    public void iCanSeeAMessageIndicatingThatTheBookingWasSuccessfullyCreated() {
        GivenWhenThen.then(user()).should(
            seeThat(BookingSuccessValidation.successful(),
                containsString("Reserva creada con éxito"))
        );
    }

    @When("I open the booking modal without selecting a slot")
    public void iOpenTheBookingModalWithoutSelectingASlot() {
        user().attemptsTo(OpenBookingModal.forFirstOffer());
        Wait.waitSomeMills(5000);
    }
}