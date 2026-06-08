package co.edu.udea.certificacion.reservasservicios.moduloIngreso.questions;

import static co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces.BusinessHoursPage.*;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

public class BusinessHourDefinitionErrorValidation implements Question<Boolean> {

    private final String NOT_VALID_HOUR_SETTING_MSG = "La hora de fin debe ser posterior a la hora de inicio"; 
    @Override
    public Boolean answeredBy(Actor actor) {
        if(!BrowseTheWeb.as(actor).find(TRANSACTION_RESULT_MESSAGE).isVisible()){
            return false;
        }
        String message = BrowseTheWeb.as(actor).find(TRANSACTION_RESULT_MESSAGE).getText();
        return message.equals(NOT_VALID_HOUR_SETTING_MSG);
    }

    public static BusinessHourDefinitionErrorValidation notValidHours(){
        return new BusinessHourDefinitionErrorValidation();
    }

}
