package co.edu.udea.certificacion.reservasservicios.moduloIngreso.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

import static co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces.ServiceActivationObjects.ALERT_SUCCESS;

public class ServiceToggleValidation implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return BrowseTheWeb.as(actor).find(ALERT_SUCCESS).getText();
    }

    public static ServiceToggleValidation successful() {
        return new ServiceToggleValidation();
    }
}
