package co.edu.udea.certificacion.reservasservicios.moduloIngreso.stepdefinitions;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.User;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.questions.LogInIncompleteValidation;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.questions.LogInIncorrectValidation;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.questions.LogInValidation;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.CustomerRegistrationEnterThe;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.LogInEnterThe;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.LogInOpenThe;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.SharedUserData;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.UserCreation;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.ValidationMessages;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.questions.LogOutValidation;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.LogOutEnterThe;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.RegistrationOpenThe;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class LogInStepDefinition {

    private Actor user() {
        return OnStage.theActorInTheSpotlight();
    }

    User userData = new User();

    @Given("that I am a registered customer on the log-in page")
    public void iAmAReisteredCustomerOnTheLogInPage(){
        user().attemptsTo(RegistrationOpenThe.browser());
        User userData = UserCreation.randomUserWithValidPassword();
        SharedUserData.setRegisteredCustomer(userData);
        user().attemptsTo(CustomerRegistrationEnterThe.information(userData));
        user().attemptsTo(LogInOpenThe.browser());
    }

    @Given("that I am on the log-in page")
    public void thatIAmOnTheLogInPage() {
        user().attemptsTo(LogInOpenThe.browser());
    }

    @When("I enter my credentials")
    public void iEnterMyCredentials() {
        User registeredCustomer = SharedUserData.getRegisteredCustomer();
        userData.setEmail(registeredCustomer.getEmail());
        userData.setPassword(registeredCustomer.getPassword());
        user().attemptsTo(LogInEnterThe.credentials(userData));
    }

    @Then("I can see the home page")
    public void iCanSeeTheHomePage() {
        GivenWhenThen.then(user()).should(seeThat(LogInValidation.successful(), equalTo("Panel de Control")));
    }

    @When("I enter my credentials an unregistered {string}")
    public void iEnterMyCredentialsAnUnregistered(String string) {
        userData.setEmail(string);
        userData.setPassword("Salome345$");
        user().attemptsTo(LogInEnterThe.credentials(userData));
    }

    @Then("I can see a message indicating that this email is incorrect")
    public void iCanSeeAMessageIndicatingThatThisEmailIsIncorrect() {
        GivenWhenThen.then(user()).should(seeThat(LogInIncorrectValidation.successful(), equalTo("Credenciales no validas")));

    }

    @When("I enter my credentials but leave the password empty")
    public void iEnterMyCredentialsButLeaveThePasswordEmpty() {
        User registeredCustomer = SharedUserData.getRegisteredCustomer();
        userData.setEmail(registeredCustomer.getEmail());
        userData.setPassword("");
        user().attemptsTo(LogInEnterThe.credentials(userData));
    }

    @Then("I can see a message indicating that the password is required")
    public void iCanSeeAMessageIndicatingThatThePasswordIsRequired() {
        GivenWhenThen.then(user()).should(seeThat(LogInIncompleteValidation.successful(), 
        anyOf(
            equalTo(ValidationMessages.EMPTY_FIELD_SPANISH),
            equalTo(ValidationMessages.EMPTY_FIELD_ENGLISH)
        )));
    }

    @Given("that I am on the home page")
    public void thatIAmOnTheHomePage() {
    }

    @When("I close my session")
    public void iCloseMySession() {
        user().attemptsTo(LogOutEnterThe.session());
    }

    @Then("I can see the login page after logout")
    public void iCanSeeTheLoginPageAfterLogout() {
        GivenWhenThen.then(user()).should(
            seeThat(LogOutValidation.successful(), 
            containsString(ValidationMessages.LOGIN_TITLE))
        );
    }
}
