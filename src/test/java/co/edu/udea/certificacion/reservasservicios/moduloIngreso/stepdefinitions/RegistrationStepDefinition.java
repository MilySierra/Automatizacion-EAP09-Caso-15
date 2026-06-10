package co.edu.udea.certificacion.reservasservicios.moduloIngreso.stepdefinitions;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.User;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.questions.RegistrationEmailValidation;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.questions.RegistrationIncompleteValidation;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.questions.RegistrationPasswordValidation;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.questions.RegistrationValidation;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.CustomerRegistrationEnterThe;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.ProviderRegistrationEnterThe;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.RegistrationOpenThe;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.SharedUserData;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.UserCreation;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.ValidationMessages;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class RegistrationStepDefinition {

    private Actor user() {
        return OnStage.theActorInTheSpotlight();
    }

    @Given("that I am on the registration page")
    public void thatIAmOnTheRegistrationPage() {
        user().attemptsTo(RegistrationOpenThe.browser());
    }

    @When("I enter my basic information as customer")
    public void iEnterMyBasicInformationAsCustomer(){
        User userData = UserCreation.randomUserWithValidPassword();
        user().attemptsTo(CustomerRegistrationEnterThe.information(userData));
    }

    @Then("I can see the login page")
    public void iCanSeeTheLoginPage(){
        GivenWhenThen.then(user()).should(seeThat(RegistrationValidation.successful(), 
            equalTo(ValidationMessages.LOGIN_TITLE)));
    }

    @When("I enter my basic information as provider")
    public void iEnterMyBasicInformationAsProvider(){
        User userData = UserCreation.randomProvider();
        user().attemptsTo(ProviderRegistrationEnterThe.information(userData));
        user().remember("registeredProvider", userData);
        SharedUserData.setRegisteredProvider(userData);
    }

    @When("I enter my basic information but leave one field empty")
    public void iEnterMyBasicInformationButLeaveOneFieldEmpty(){
        User userData = UserCreation.randomBasicUserData();
        userData.setPassword("Geraldine5647!");
        userData.setPasswordVerification("Geraldine5647!");
        user().attemptsTo(CustomerRegistrationEnterThe.information(userData));
    }

    @Then("I can see a message indicating that this field is required")
    public void iCanSeeAMessageIndicatingThatThisFieldIsRequired(){
        GivenWhenThen.then(user()).should(seeThat(RegistrationIncompleteValidation.successful(),
         anyOf(
            equalTo(ValidationMessages.EMPTY_FIELD_ENGLISH),
            equalTo(ValidationMessages.EMPTY_FIELD_SPANISH)
        )));
    }

    @When("I enter my basic information but with an invalid password")
    public void iEnterMyBasicInformationButWithAnInvalidPassword(){
        User userData = UserCreation.randomUserWithNotValidPassword();
        user().attemptsTo(ProviderRegistrationEnterThe.information(userData));
    }

    @Then("I can see a message indicating that this password is invalid")
    public void iCanSeeAMessageIndicatingThatThisPasswordIsInvalid(){
        GivenWhenThen.then(user()).should(seeThat(RegistrationPasswordValidation.successful(),
                equalTo(ValidationMessages.NOT_VALID_PASSWORD)));
    }

    @When("I enter my basic information but with an invalid email")
    public void iEnterMyBasicInformationButWithAnInvalidEmail(){
        User userData = UserCreation.randomUserWithValidPassword();
        userData.setEmail(userData.getName().toLowerCase() + "." + userData.getLastname().toLowerCase());
        user().attemptsTo(ProviderRegistrationEnterThe.information(userData));
    }

    @Then("I can see a message indicating that this email is invalid")
    public void iCanSeeAMessageIndicatingThatThisEmailIsInvalid(){
        GivenWhenThen.then(user()).should(seeThat(
            RegistrationEmailValidation.successful(),
                anyOf(
                    containsString(ValidationMessages.AT_SIGN_MISSING_SPANISH),
                    containsString(ValidationMessages.AT_SIGN_MISSING_ENGLISH)
                )
            ));
                
    }

}
