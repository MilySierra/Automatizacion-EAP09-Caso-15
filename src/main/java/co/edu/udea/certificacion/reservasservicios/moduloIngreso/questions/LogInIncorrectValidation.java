package co.edu.udea.certificacion.reservasservicios.moduloIngreso.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

import static co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces.LogInObjects.ERROR_MESSAGE_LOG_IN;

public class LogInIncorrectValidation implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return BrowseTheWeb.as(actor).find(ERROR_MESSAGE_LOG_IN).getText();
    }

    public static LogInIncorrectValidation successful(){
        return new LogInIncorrectValidation();
    }
}
