package co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;

import static co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces.BookingObjects.*;

public class ConfirmBookingSlot implements Interaction {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            WaitUntil.the(FIRST_SLOT_AVAILABLE, WebElementStateMatchers.isVisible())
                .forNoMoreThan(60).seconds(),
            Click.on(FIRST_SLOT_AVAILABLE)
        );
    }

    public static ConfirmBookingSlot available() {
        return Tasks.instrumented(ConfirmBookingSlot.class);
    }
}