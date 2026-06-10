package co.edu.udea.certificacion.reservasservicios.moduloIngreso.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

import static co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces.MyBookingsObjects.ALERT_SUCCESS;

public class BookingCancellationSuccessValidation implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return BrowseTheWeb.as(actor).find(ALERT_SUCCESS).getText();
    }

    public static BookingCancellationSuccessValidation successful() {
        return new BookingCancellationSuccessValidation();
    }
}
