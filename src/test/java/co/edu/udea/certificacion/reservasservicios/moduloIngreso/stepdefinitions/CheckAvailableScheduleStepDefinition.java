package co.edu.udea.certificacion.reservasservicios.moduloIngreso.stepdefinitions;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.questions.AvailableSlotsValidation;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.questions.NoSlotsValidation;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.ConsultAvailabilityEnterThe;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.CreateDates;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.containsString;

public class CheckAvailableScheduleStepDefinition {

    private Actor user() {
        return OnStage.theActorInTheSpotlight();
    }

    @Given("that I am on the offers page")
    public void thatIAmOnTheOffersPage() {
        // El dashboard ya está abierto gracias al @requiresLoginCustomer del Hook
    }

    @When("I select a valid offer and a valid date")
    public void iSelectAValidOfferAndAValidDate() {
        String date = CreateDates.dateAfterNow(2);
        user().attemptsTo(ConsultAvailabilityEnterThe.forDate(date));
    }

    @Then("I can see the available slots for the selected date")
    public void iCanSeeTheAvailableSlotsForTheSelectedDate() {
        GivenWhenThen.then(user()).should(
            seeThat(AvailableSlotsValidation.successful(), containsString("cupos"))
        );
    }

    @When("I select a valid offer and a date with no slots")
    public void iSelectAValidOfferAndADateWithNoSlots() {
        String date = CreateDates.dateAfterNow(5);
        user().attemptsTo(ConsultAvailabilityEnterThe.forDate(date));
    }

    @Then("I can see a message indicating that there are no slots available")
    public void iCanSeeAMessageIndicatingThatThereAreNoSlotsAvailable() {
        GivenWhenThen.then(user()).should(
            seeThat(NoSlotsValidation.successful(),
                containsString("No hay cupos definidos para esta fecha"))
        );
    }
}