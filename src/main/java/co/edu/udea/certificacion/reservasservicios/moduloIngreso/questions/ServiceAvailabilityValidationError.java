package co.edu.udea.certificacion.reservasservicios.moduloIngreso.questions;

import static co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces.ServiceAvailabilityPage.*;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

public class ServiceAvailabilityValidationError implements Question<Boolean> {

    private final String OVERLAPPING_HOURS_MSG = "La franja propuesta se superpone con una disponibilidad existente";

    @Override
    public Boolean answeredBy(Actor actor) {
       if(!BrowseTheWeb.as(actor).find(SLOT_CREATION_RESULT_MSG).isVisible()){
            return false;
        }
        String message = BrowseTheWeb.as(actor).find(SLOT_CREATION_RESULT_MSG).getText();
        return message.equals(OVERLAPPING_HOURS_MSG);
    }

    public static ServiceAvailabilityValidationError overlappingHours() {
        return new ServiceAvailabilityValidationError();
    }

}
