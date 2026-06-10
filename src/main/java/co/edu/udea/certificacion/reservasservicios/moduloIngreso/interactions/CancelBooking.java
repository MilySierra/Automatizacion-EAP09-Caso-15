package co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces.MyBookingsObjects;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.Wait;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;

public class CancelBooking implements Interaction {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(MyBookingsObjects.BTN_CANCEL));
        // Al hacer click en el botón de cancelar, la app muestra un diálogo nativo del browser:
        // "¿Estás seguro de que deseas cancelar esta reserva?"
        // Hay que aceptarlo para que la cancelación proceda. 500ms asegura que el diálogo
        // haya terminado de aparecer antes de llamar switchTo().alert().
        Wait.waitSomeMills(500);
        BrowseTheWeb.as(actor).getDriver().switchTo().alert().accept();
    }

    public static CancelBooking active() {
        return Tasks.instrumented(CancelBooking.class);
    }
}
