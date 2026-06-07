package co.edu.udea.certificacion.reservasservicios.moduloIngreso.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces.ServiceRegistrationObjects.INPUT_SERVICE_DESCRIPTION;

public class ServiceRegistrationIncompleteValidation implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return INPUT_SERVICE_DESCRIPTION.resolveFor(actor).getAttribute("validationMessage");
    }

    public static ServiceRegistrationIncompleteValidation successful(){
        return new ServiceRegistrationIncompleteValidation();
    }
}
