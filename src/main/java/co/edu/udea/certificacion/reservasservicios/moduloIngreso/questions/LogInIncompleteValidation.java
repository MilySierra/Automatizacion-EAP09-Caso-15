package co.edu.udea.certificacion.reservasservicios.moduloIngreso.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces.LogInObjects.INPUT_PASSWORD_LOG_IN;

public class LogInIncompleteValidation implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return INPUT_PASSWORD_LOG_IN.resolveFor(actor).getAttribute("validationMessage");
    }

    public static LogInIncompleteValidation successful(){
        return new LogInIncompleteValidation();
    }
}
