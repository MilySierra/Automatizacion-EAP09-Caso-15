package co.edu.udea.certificacion.reservasservicios.moduloIngreso.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

import static co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces.RegistrationObjects.ERROR_MESSAGE;


public class RegistrationPasswordValidation implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return BrowseTheWeb.as(actor).find(ERROR_MESSAGE).getText();
    }

    public static RegistrationPasswordValidation successful(){
        return new RegistrationPasswordValidation();
    }
}
