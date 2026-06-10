package co.edu.udea.certificacion.reservasservicios.moduloIngreso.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

import static co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces.MyBookingsObjects.EMPTY_STATE_MESSAGE;

public class MyBookingsEmptyValidation implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return BrowseTheWeb.as(actor).find(EMPTY_STATE_MESSAGE).getText();
    }

    public static MyBookingsEmptyValidation empty() {
        return new MyBookingsEmptyValidation();
    }
}
