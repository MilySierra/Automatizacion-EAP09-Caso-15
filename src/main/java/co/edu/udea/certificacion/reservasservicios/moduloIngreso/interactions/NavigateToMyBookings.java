package co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces.MyBookingsObjects;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

public class NavigateToMyBookings implements Interaction {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(MyBookingsObjects.BTN_MY_BOOKINGS));
    }

    public static NavigateToMyBookings page() {
        return Tasks.instrumented(NavigateToMyBookings.class);
    }
}
