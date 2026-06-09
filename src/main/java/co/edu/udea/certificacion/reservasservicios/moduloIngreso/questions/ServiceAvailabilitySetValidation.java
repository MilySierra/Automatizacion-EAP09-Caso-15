package co.edu.udea.certificacion.reservasservicios.moduloIngreso.questions;

import static co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces.ServiceAvailabilityPage.*;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

public class ServiceAvailabilitySetValidation implements Question<Boolean> {

    private final String OUTSIDE_BUSINESS_HOURS_MSG = "La franja no es valida dentro del horario general del proveedor"; 

    @Override
    public Boolean answeredBy(Actor actor) {
        if(!BrowseTheWeb.as(actor).find(SLOT_CREATION_RESULT_MSG).isVisible()){
            return false;
        }
        String message = BrowseTheWeb.as(actor).find(SLOT_CREATION_RESULT_MSG).getText();
        return message.equals(OUTSIDE_BUSINESS_HOURS_MSG);
    }

    public static ServiceAvailabilitySetValidation outsideBusinessHours() {
        return new ServiceAvailabilitySetValidation();
    }

}
