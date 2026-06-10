package co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions.CancelBooking;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions.NavigateToMyBookings;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.Wait;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class CancelBookingEnterThe implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(NavigateToMyBookings.page());
        Wait.waitSomeMills(2000);
        actor.attemptsTo(CancelBooking.active());
        // waitAfterEndpoint = 2000ms no es suficiente para la API de Vercel en cold start.
        // Agregamos 25000ms adicionales igual que en el hook de HU-10.
        Wait.waitAfterEndpoint();
        Wait.waitSomeMills(25000);
    }

    public static CancelBookingEnterThe booking() {
        return Tasks.instrumented(CancelBookingEnterThe.class);
    }
}
