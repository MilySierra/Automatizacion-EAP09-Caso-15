package co.edu.udea.certificacion.reservasservicios.moduloIngreso.stepdefinitions;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions.ProviderSetDefault;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions.ServiceRegistrationOpen;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.Service;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.User;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.questions.ServiceToggleValidation;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.ActivateServiceEnterThe;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.DeactivateServiceEnterThe;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.LogInEnterThe;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.LogInOpenThe;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.ProviderRegistrationEnterThe;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.RegistrationOpenThe;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.ServiceRegistrationEnterThe;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces.DashboardPage;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.ServiceCreation;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.SharedUserData;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.UserCreation;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.ValidationMessages;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.Wait;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actors.OnStage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class ServiceActivationStepDefinition {

    private Actor user() {
        return OnStage.theActorInTheSpotlight();
    }

    /**
     * Hook exclusivo de HU-10. Crea un proveedor aleatorio desde cero, configura sus
     * horarios de atención (prerrequisito para acceder a Servicios y Cupos) y registra
     * un servicio. Al finalizar, el actor está en la página Servicios y Cupos con el
     * servicio recién creado (activo por defecto).
     *
     * Flujo detallado (order=1 → corre DESPUÉS de Hook.setTheStage() con order=0):
     *
     * 1. Abrir /register y registrar el proveedor
     * 2. Navegar EXPLÍCITAMENTE a /login (la app hace auto-login tras el registro y
     *    redirige al dashboard; sin esta navegación explícita, el formulario de login
     *    no está en pantalla y LogInEnterThe falla)
     * 3. Hacer login con las credenciales del proveedor
     * 4. Hacer click en "Horarios Atención" (button[2] del sidebar)
     * 5. Configurar los 7 días con ProviderSetDefault.businessHours()
     *    (SIN este paso, el botón "Servicios y Cupos" no aparece en el sidebar)
     * 6. Hacer click en "Servicios y Cupos" (button[3]/span del sidebar)
     * 7. Registrar el servicio
     */
    @Before(value = "@requiresProviderForHU10", order = 1)
    public void setUpProviderWithServiceForHU10() {
        User provider = UserCreation.randomProvider();
        Service service = ServiceCreation.randomService(provider);
        SharedUserData.setRegisteredProvider(provider);
        SharedUserData.setRegisteredService(service);

        // --- Paso 1: Registro del proveedor ---
        user().attemptsTo(RegistrationOpenThe.browser());
        user().attemptsTo(ProviderRegistrationEnterThe.information(provider));

        // --- Paso 2 y 3: Login explícito ---
        // La app hace auto-login y redirige al dashboard después del registro.
        // LogInOpenThe navega explícitamente a /login para que el formulario esté disponible.
        user().attemptsTo(LogInOpenThe.browser());
        user().attemptsTo(LogInEnterThe.credentials(provider));

        // --- Esperar que el login complete y el dashboard cargue ---
        // El screenshot de fallo confirmó que el API de login de Vercel tarda >12s en cold start:
        // el botón mostraba "Iniciando sesión..." después de waitAfterEndpoint(2000ms) + 5000ms extra.
        // waitAfterEndpoint() no es modificable (es de otro estudiante), por lo que agregamos
        // 20000ms de espera adicional para cubrir el cold start de Vercel (~15-17s en el peor caso).
        // Total desde el click de login: 2000ms + 20000ms = 22s → suficiente para cualquier cold start.
        Wait.waitSomeMills(20000);

        // --- Paso 4 y 5: Horarios de Atención (prerequisito de Servicios y Cupos) ---
        // Sin horarios configurados, el botón "Servicios y Cupos" (button[3]) no existe
        // en el sidebar y cualquier intento de click falla.
        user().attemptsTo(Click.on(DashboardPage.BUSINESS_HOURS_BUTTON));
        Wait.waitSomeMills(2000);
        user().attemptsTo(ProviderSetDefault.businessHours());

        // --- Paso 6 y 7: Crear el servicio ---
        // ServiceRegistrationOpen hace click en button[3]/span (ahora disponible).
        user().attemptsTo(ServiceRegistrationOpen.browser());
        Wait.waitSomeMills(2000);
        user().attemptsTo(ServiceRegistrationEnterThe.information(service));
        // Actor queda en Servicios y Cupos con el servicio activo (estado por defecto).
    }

    @Given("that I am on the services page with an active service")
    public void thatIAmOnTheServicesPageWithActiveService() {
        // El @Before dejó el servicio activo. Solo esperamos a que la UI esté lista.
        Wait.waitSomeMills(1000);
    }

    @Given("that I am on the services page with an inactive service")
    public void thatIAmOnTheServicesPageWithInactiveService() {
        // El @Before dejó el servicio activo; lo desactivamos aquí para preparar el escenario.
        user().attemptsTo(DeactivateServiceEnterThe.service());
        // waitAfterEndpoint = 2000ms; sumamos 3000ms más para que la alerta desaparezca
        // antes de que el paso When haga click en "Activar" y leamos la nueva alerta.
        Wait.waitSomeMills(3000);
    }

    @When("I activate the service")
    public void iActivateTheService() {
        user().attemptsTo(ActivateServiceEnterThe.service());
    }

    @When("I deactivate the service")
    public void iDeactivateTheService() {
        user().attemptsTo(DeactivateServiceEnterThe.service());
    }

    @Then("I can see a message confirming the service status was updated")
    public void iCanSeeAMessageConfirmingTheServiceStatusWasUpdated() {
        GivenWhenThen.then(user()).should(
                seeThat(ServiceToggleValidation.successful(),
                        equalTo(ValidationMessages.SERVICE_STATUS_UPDATED))
        );
    }
}
