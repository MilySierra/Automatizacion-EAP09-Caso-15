package co.edu.udea.certificacion.reservasservicios.moduloIngreso.stepdefinitions;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions.CancelBooking;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions.NavigateToMyBookings;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions.ProviderSetDefault;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions.ServiceRegistrationOpen;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.Service;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.User;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.CreateAvailabilityEnterThe;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.CreateBookingEnterThe;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.CustomerRegistrationEnterThe;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.LogInEnterThe;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.LogInOpenThe;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.LogOutEnterThe;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.ProviderRegistrationEnterThe;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.RegistrationOpenThe;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks.ServiceRegistrationEnterThe;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces.DashboardPage;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces.MyBookingsObjects;
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
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class BookingCancellationStepDefinition {

    private Actor user() {
        return OnStage.theActorInTheSpotlight();
    }

    /**
     * Fecha laborable más cercana a partir de hoy+2 (formato dd/MM/yyyy).
     * La app interpreta el input como DD/MM/YYYY, y CreateAvailabilityEnter
     * crea la disponibilidad con la misma fecha en formato yyyy-MM-dd (ISO).
     */
    private String nextBookingDate() {
        LocalDate date = LocalDate.now().plusDays(2);
        while (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            date = date.plusDays(1);
        }
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Before(value = "@requiresProviderForHU17", order = 1)
    public void setUpProviderWithServiceForHU17() {
        User provider = UserCreation.randomProvider();
        Service service = ServiceCreation.randomService(provider);
        SharedUserData.setRegisteredProvider(provider);
        SharedUserData.setRegisteredService(service);

        // 1. Registrar proveedor
        user().attemptsTo(RegistrationOpenThe.browser());
        user().attemptsTo(ProviderRegistrationEnterThe.information(provider));

        // 2. Ir a /login (la app redirige al dashboard tras registrarse)
        user().attemptsTo(LogInOpenThe.browser());
        user().attemptsTo(LogInEnterThe.credentials(provider));
        Wait.waitSomeMills(20000); // cold-start de Vercel

        // 3. Horarios de atención
        user().attemptsTo(Click.on(DashboardPage.BUSINESS_HOURS_BUTTON));
        Wait.waitSomeMills(2000);
        user().attemptsTo(ProviderSetDefault.businessHours());

        // 4. Crear servicio
        user().attemptsTo(ServiceRegistrationOpen.browser());
        Wait.waitSomeMills(2000);
        user().attemptsTo(ServiceRegistrationEnterThe.information(service));
        Wait.waitSomeMills(3000);

        // 5. Crear disponibilidad (hoy+2 saltando fines de semana, 09:30-10:30)
        user().attemptsTo(CreateAvailabilityEnterThe.information());
        Wait.waitSomeMills(25000); // esperar que Vercel persista la disponibilidad

        // 6. Registrar cliente
        User customer = UserCreation.randomUserWithValidPassword();
        SharedUserData.setRegisteredCustomer(customer);
        user().attemptsTo(LogOutEnterThe.session());
        user().attemptsTo(RegistrationOpenThe.browser());
        user().attemptsTo(CustomerRegistrationEnterThe.information(customer));
    }

    @Given("that I have an active booking in my bookings list")
    public void thatIHaveAnActiveBookingInMyBookingsList() {
        user().attemptsTo(CreateBookingEnterThe.forDate(nextBookingDate()));
        user().attemptsTo(NavigateToMyBookings.page());
        Wait.waitSomeMills(2000);
    }

    @When("I cancel the booking")
    public void iCancelTheBooking() {
        user().attemptsTo(CancelBooking.active());
        // No se espera aquí: el @Then usa WaitUntil para capturar el banner
        // en cuanto el API responde, evitando que se auto-descarte.
    }

    @Then("I can see a message indicating the booking was successfully cancelled")
    public void iCanSeeAMessageIndicatingTheBookingWasSuccessfullyCancelled() {
        // Esperar hasta 30s a que el banner de éxito aparezca (variable según Vercel)
        user().attemptsTo(
            WaitUntil.the(MyBookingsObjects.ALERT_SUCCESS, WebElementStateMatchers.isVisible())
                .forNoMoreThan(30).seconds()
        );
        String text = BrowseTheWeb.as(user()).find(MyBookingsObjects.ALERT_SUCCESS).getText();
        assertThat(text, containsString(ValidationMessages.BOOKING_CANCELLED));
    }
}
