package co.edu.udea.certificacion.reservasservicios.moduloIngreso.questions;

import static co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces.BusinessHoursPage.*;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

public class BusinessHoursDefinitionValidation implements Question<Boolean> {

    private String correctHoursDefinition;

    public BusinessHoursDefinitionValidation(String day){
        this.correctHoursDefinition = "Horario de "+ day +" actualizado correctamente.";
    }
    @Override
    public Boolean answeredBy(Actor actor) {
        if(!BrowseTheWeb.as(actor).find(TRANSACTION_RESULT_MESSAGE).isVisible()){
            return false;
        }
        String message = BrowseTheWeb.as(actor).find(TRANSACTION_RESULT_MESSAGE).getText();
        return message.equals(correctHoursDefinition);
    }

    public static BusinessHoursDefinitionValidation sucessful(String day){
        return new BusinessHoursDefinitionValidation(day);
    }

}
