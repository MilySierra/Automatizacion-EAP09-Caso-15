package co.edu.udea.certificacion.reservasservicios.moduloIngreso.stepdefinitions;

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
import static org.hamcrest.Matchers.equalTo;

public class MyBookingsStepDefinition {

    private Actor user() {
        return OnStage.theActorInTheSpotlight();
    }

    /**
     * Fecha laborable hoy+2 en formato dd/MM/yyyy.
     * La app interpreta el input del modal de reservas como DD/MM/YYYY,
     * y CreateAvailabilityEnter crea la disponibilidad para la misma fecha (lógica idéntica).
     */
    private String nextBookingDate() {
        LocalDate date = LocalDate.now().plusDays(2);
        while (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            date = date.plusDays(1);
        }
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Setup completo para HU-19 escenario 1 (reemplaza los hooks
     * @requiresProviderWithSomeService + @requiresLoginCustomer que no registran cliente).
     * Patrón probado en HU-17.
     */
    @Before(value = "@requiresSetupForHU19", order = 1)
    public void setUpForHU19() {
        User provider = UserCreation.randomProvider();
        Service service = ServiceCreation.randomService(provider);
        SharedUserData.setRegisteredProvider(provider);
        SharedUserData.setRegisteredService(service);

        // 1. Registrar proveedor
        user().attemptsTo(RegistrationOpenThe.browser());
        user().attemptsTo(ProviderRegistrationEnterThe.information(provider));

        // 2. Login proveedor
        user().attemptsTo(LogInOpenThe.browser());
        user().attemptsTo(LogInEnterThe.credentials(provider));
        Wait.waitSomeMills(20000); // cold-start Vercel

        // 3. Horarios de atención
        user().attemptsTo(Click.on(DashboardPage.BUSINESS_HOURS_BUTTON));
        Wait.waitSomeMills(2000);
        user().attemptsTo(ProviderSetDefault.businessHours());

        // 4. Crear servicio
        user().attemptsTo(ServiceRegistrationOpen.browser());
        Wait.waitSomeMills(2000);
        user().attemptsTo(ServiceRegistrationEnterThe.information(service));
        Wait.waitSomeMills(3000);

        // 5. Crear disponibilidad
        user().attemptsTo(CreateAvailabilityEnterThe.information());
        Wait.waitSomeMills(25000);

        // 6. Registrar cliente y hacer login
        User customer = UserCreation.randomUserWithValidPassword();
        SharedUserData.setRegisteredCustomer(customer);
        user().attemptsTo(LogOutEnterThe.session());
        user().attemptsTo(RegistrationOpenThe.browser());
        user().attemptsTo(CustomerRegistrationEnterThe.information(customer));
        user().attemptsTo(LogInOpenThe.browser());
        user().attemptsTo(LogInEnterThe.credentials(customer));
        Wait.waitSomeMills(15000); // esperar que el dashboard del cliente cargue completamente
    }

    @Given("that I am a customer with an existing booking")
    public void thatIAmACustomerWithAnExistingBooking() {
        // El @Before ya dejó al cliente logueado.
        // Usar DD/MM/YYYY para que coincida con la disponibilidad creada por CreateAvailabilityEnter.
        user().attemptsTo(CreateBookingEnterThe.forDate(nextBookingDate()));
        Wait.waitSomeMills(10000); // dar tiempo a Vercel para persistir la reserva antes de navegar
    }

    @Given("that I am a new customer with no bookings")
    public void thatIAmANewCustomerWithNoBookings() {
        User freshCustomer = UserCreation.randomUserWithValidPassword();
        user().attemptsTo(RegistrationOpenThe.browser());
        user().attemptsTo(CustomerRegistrationEnterThe.information(freshCustomer));
        user().attemptsTo(LogInOpenThe.browser());
        user().attemptsTo(LogInEnterThe.credentials(freshCustomer));
        Wait.waitSomeMills(5000);
    }

    @When("I navigate to my bookings page")
    public void iNavigateToMyBookingsPage() {
        user().attemptsTo(NavigateToMyBookings.page());
        Wait.waitSomeMills(2000);
    }

    @Then("I can see all my bookings in the table")
    public void iCanSeeAllMyBookingsInTheTable() {
        user().attemptsTo(
            WaitUntil.the(MyBookingsObjects.BOOKINGS_TABLE, WebElementStateMatchers.isVisible())
                .forNoMoreThan(15).seconds()
        );
        boolean visible = BrowseTheWeb.as(user()).find(MyBookingsObjects.BOOKINGS_TABLE).isVisible();
        assertThat(visible, equalTo(true));
    }

    @Then("I can see a message indicating that I have no bookings")
    public void iCanSeeAMessageIndicatingThatIHaveNoBookings() {
        user().attemptsTo(
            WaitUntil.the(MyBookingsObjects.EMPTY_STATE_MESSAGE, WebElementStateMatchers.isVisible())
                .forNoMoreThan(15).seconds()
        );
        String text = BrowseTheWeb.as(user()).find(MyBookingsObjects.EMPTY_STATE_MESSAGE).getText();
        assertThat(text, containsString(ValidationMessages.NO_BOOKINGS));
    }
}
