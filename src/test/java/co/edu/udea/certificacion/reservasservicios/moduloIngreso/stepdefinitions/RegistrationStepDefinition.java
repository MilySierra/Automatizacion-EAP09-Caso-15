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
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;

import java.util.concurrent.ThreadLocalRandom;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class RegistrationStepDefinition {

    private Actor user() {
        return OnStage.theActorInTheSpotlight();
    }

    User userData = new User();
    //public int numero = ThreadLocalRandom.current().nextInt(1, 1000);
    public long numero = System.currentTimeMillis();


    @Given("that I am on the registration page")
    public void thatIAmOnTheRegistrationPage() {
        user().attemptsTo(RegistrationOpenThe.browser());
    }

    @When("I enter my basic information as customer")
    public void iEnterMyBasicInformationAsCustomer(){
        userData.setName("Valentina");
        userData.setLastname("Suarez");
        userData.setEmail("valentina.suarez"+numero+"@udea.edu.co");
        userData.setPassword("Valentina123#");
        userData.setPasswordVerification("Valentina123#");
        user().attemptsTo(CustomerRegistrationEnterThe.information(userData));
        SharedUserData.setRegisteredCustomer(userData);
    }

    @Then("I can see the login page")
    public void iCanSeeTheLoginPage(){
        GivenWhenThen.then(user()).should(seeThat(RegistrationValidation.successful(), equalTo("Hola,\nBienvenido")));
    }

    @When("I enter my basic information as provider")
    public void iEnterMyBasicInformationAsProvider(){
        userData.setName("Luciana");
        userData.setLastname("Restrepo");
        userData.setEmail("luciana.restrepo"+numero+"@udea.edu.co");
        userData.setPassword("Luciana234#");
        userData.setPasswordVerification("Luciana234#");
        user().attemptsTo(ProviderRegistrationEnterThe.information(userData));
        user().remember("registeredProvider", userData);
        SharedUserData.setRegisteredProvider(userData);
    }

    @When("I enter my basic information but leave one field empty")
    public void iEnterMyBasicInformationButLeaveOneFieldEmpty(){
        userData.setName("Geraldine");
        userData.setLastname("Restrepo");
        userData.setEmail("");
        userData.setPassword("Geraldine5647!");
        userData.setPasswordVerification("Geraldine5647!");
        user().attemptsTo(CustomerRegistrationEnterThe.information(userData));
    }

    @Then("I can see a message indicating that this field is required")
    public void iCanSeeAMessageIndicatingThatThisFieldIsRequired(){
        GivenWhenThen.then(user()).should(seeThat(RegistrationIncompleteValidation.successful(), equalTo("Completa este campo")));
    }

    @When("I enter my basic information but with an invalid password")
    public void iEnterMyBasicInformationButWithAnInvalidPassword(){
        userData.setName("Julian");
        userData.setLastname("Tabares");
        userData.setEmail("julian.tabares@udea.edu.co");
        userData.setPassword("Julian");
        userData.setPasswordVerification("Julian");
        user().attemptsTo(ProviderRegistrationEnterThe.information(userData));
    }

    @Then("I can see a message indicating that this password is invalid")
    public void iCanSeeAMessageIndicatingThatThisPasswordIsInvalid(){
        GivenWhenThen.then(user()).should(seeThat(RegistrationPasswordValidation.successful(),
                equalTo("La contraseña debe tener entre 8 y 64 caracteres, incluir al menos una letra" +
                        " mayúscula, una letra minúscula, un número y un carácter especial.")));
    }

    @When("I enter my basic information but with an invalid email")
    public void iEnterMyBasicInformationButWithAnInvalidEmail(){
        userData.setName("Emiliano");
        userData.setLastname("Berrio");
        userData.setEmail("emiliano.Berrio");
        userData.setPassword("Emiliano234#");
        userData.setPasswordVerification("Emiliano234#");
        user().attemptsTo(ProviderRegistrationEnterThe.information(userData));
    }

    @Then("I can see a message indicating that this email is invalid")
    public void iCanSeeAMessageIndicatingThatThisEmailIsInvalid(){
        GivenWhenThen.then(user()).should(seeThat(RegistrationEmailValidation.successful(),
                containsString("Incluye un signo \"@\" en la dirección de correo electrónico")));
    }

}
