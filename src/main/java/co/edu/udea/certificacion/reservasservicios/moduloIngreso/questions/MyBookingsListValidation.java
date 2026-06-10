package co.edu.udea.certificacion.reservasservicios.moduloIngreso.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

import static co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces.MyBookingsObjects.BOOKINGS_TABLE;

public class MyBookingsListValidation implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {
        return BrowseTheWeb.as(actor).find(BOOKINGS_TABLE).isVisible();
    }

    public static MyBookingsListValidation visible() {
        return new MyBookingsListValidation();
    }
}
