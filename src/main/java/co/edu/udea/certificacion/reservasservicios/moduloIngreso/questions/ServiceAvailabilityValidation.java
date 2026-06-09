package co.edu.udea.certificacion.reservasservicios.moduloIngreso.questions;

import static co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces.ServiceAvailabilityPage.*;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

public class ServiceAvailabilityValidation implements Question<Boolean>{

    private final String CORRECT_AVAILABILITY_DEFINITION = "Franja de disponibilidad creada correctamente."; 

    @Override
    public Boolean answeredBy(Actor actor) {
       
        if(!BrowseTheWeb.as(actor).find(SLOT_CREATION_RESULT_MSG).isVisible()){
            return false;
        }
        String message = BrowseTheWeb.as(actor).find(SLOT_CREATION_RESULT_MSG).getText();
        return message.equals(CORRECT_AVAILABILITY_DEFINITION);

    }

    public static ServiceAvailabilityValidation sucessfulCreated(){
        return new ServiceAvailabilityValidation();
    }

}
