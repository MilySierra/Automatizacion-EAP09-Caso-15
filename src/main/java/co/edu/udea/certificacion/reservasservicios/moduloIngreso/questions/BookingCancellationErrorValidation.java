package co.edu.udea.certificacion.reservasservicios.moduloIngreso.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

import static co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces.MyBookingsObjects.ALERT_ERROR;

public class BookingCancellationErrorValidation implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return BrowseTheWeb.as(actor).find(ALERT_ERROR).getText();
    }

    public static BookingCancellationErrorValidation error() {
        return new BookingCancellationErrorValidation();
    }
}
