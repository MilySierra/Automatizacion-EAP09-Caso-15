package co.edu.udea.certificacion.reservasservicios.moduloIngreso.stepdefinitions;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.ServiceAvailability;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.User;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.LogInEnterThe;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.LogInOpenThe;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.LogOutEnterThe;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.ProviderSetService;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.RegisterDefault;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.CreateServiceAvailability;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.SharedUserData;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.UserCreation;
import io.cucumber.java.Before;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowsingTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.openqa.selenium.WebDriver;

public class Hook {

    @Managed(driver = "chrome", uniqueSession = false)
    public WebDriver theDriver;

    @Before(order = 0)
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
        Actor user = OnStage.theActorCalled("Daniella");
        user.can(BrowsingTheWeb.with(theDriver));
    }

    @Before(value = "@requiresLoginProvider")
    public void logInProviderBeforeScenario() {
        User provider = SharedUserData.getRegisteredProvider();
        OnStage.theActorInTheSpotlight().attemptsTo(
                LogInOpenThe.browser(),
                LogInEnterThe.credentials(provider)
        );
    }

    @Before(value = "@requiresLoginCustomer", order=2)
    public void logInCustomerBeforeScenario() {
        User customer = SharedUserData.getRegisteredCustomer();
        OnStage.theActorInTheSpotlight().attemptsTo(
                LogInOpenThe.browser(),
                LogInEnterThe.credentials(customer)
        );
    }

    @Before(value = "@requiresProviderWithSomeService", order=1)
    public void providerOffersAServiceBeforeScenario() {
        User provider = UserCreation.randomProvider();
        ServiceAvailability serviceHours = CreateServiceAvailability.createServiceHours();

        OnStage.theActorInTheSpotlight().attemptsTo(
            RegisterDefault.provider(provider),
            ProviderSetService.availability(serviceHours),
            LogOutEnterThe.session()
        );
    }
}
