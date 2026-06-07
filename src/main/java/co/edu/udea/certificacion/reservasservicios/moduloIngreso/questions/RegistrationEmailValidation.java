package co.edu.udea.certificacion.reservasservicios.moduloIngreso.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces.RegistrationObjects.INPUT_EMAIL;

public class RegistrationEmailValidation implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return INPUT_EMAIL.resolveFor(actor).getAttribute("validationMessage");
    }

    public static RegistrationEmailValidation successful(){
        return new RegistrationEmailValidation();
    }
}
