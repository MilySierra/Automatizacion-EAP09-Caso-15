package co.edu.udea.certificacion.reservasservicios.moduloIngreso.stepdefinitions;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.BusinessHours;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.User;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.questions.BusinessHourDefinitionErrorValidation;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.questions.BusinessHoursDefinitionValidation;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.LoginThe;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.ProviderSetThe;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.BusinessHoursMapping;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.UserCreation;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import net.serenitybdd.screenplay.actors.OnStage;

public class BusinessHourStepDefinition {

    private String dayOfWeek;

    private Actor user() {
        return OnStage.theActorInTheSpotlight();
    }

    @Given("I am an authenticated provider at the general business hours page")
    public void iAmAnAuthenticatedProviderAtTheGeneralBusinessHoursPage() {
        User provider = UserCreation.randomProvider();
        user().attemptsTo(LoginThe.providerAtBussinesHours(provider));
    }

    @When("I define a valid set of business hours:")
    public void iDefineAValidSetOfBusinessHours(DataTable dataTable) {
        BusinessHours businessHours = BusinessHoursMapping.getBusinessHours(dataTable);
        dayOfWeek = businessHours.getDay();
        user().attemptsTo(ProviderSetThe.businessHours(businessHours));
    }

    @Then("I can see a message of correct definition")
    public void iCanSeeAMessageOfCorrectDefinition() {
        GivenWhenThen.then(user())
        .should(seeThat(BusinessHoursDefinitionValidation.sucessful(dayOfWeek)));
     
    }

    @When("I update the set of business hours for one day:")
    public void iUpdateTheSetOfBusinessHoursForOneDay(io.cucumber.datatable.DataTable dataTable) {
        BusinessHours businessHours = BusinessHoursMapping.getBusinessHours(dataTable);
        dayOfWeek = businessHours.getDay();
        user().attemptsTo(ProviderSetThe.businessHours(businessHours));
    }

    @Then("I can see a message of correct update")
    public void iCanSeeAMessageOfCorrectUpdate() {
        GivenWhenThen.then(user())
        .should(seeThat(BusinessHoursDefinitionValidation.sucessful(dayOfWeek)));
    }

    @When("I define the opening time after the closing time:")
    public void iDefineTheOpeningTimeAfterTheClosingTime(io.cucumber.datatable.DataTable dataTable) {
        BusinessHours businessHours = BusinessHoursMapping.getBusinessHours(dataTable);
        user().attemptsTo(ProviderSetThe.businessHours(businessHours));
    }

    @Then("I can see an error message related with incompatible hours")
    public void iCanSeeAnErrorMessageRelatedWithIncompatibleHours() {
        GivenWhenThen.then(user())
        .should(seeThat(BusinessHourDefinitionErrorValidation.notValidHours()));
    }

}
