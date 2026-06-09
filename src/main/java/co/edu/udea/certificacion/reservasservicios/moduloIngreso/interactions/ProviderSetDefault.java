package co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.BusinessHours;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.Wait;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;

public class ProviderSetDefault implements Interaction{

    @Override
    public <T extends Actor> void performAs(T actor) {
        BusinessHours mondayToFridayHours = new BusinessHours();
        mondayToFridayHours.setOpeningTime("09:00AM");
        mondayToFridayHours.setClosingTime("05:00PM");

        BusinessHours sundayHours = new BusinessHours();
        sundayHours.setDayOfWeek(7);
        sundayHours.setOpeningTime("10:00AM");
        sundayHours.setClosingTime("05:00PM");

        for (int i=1; i<7; i++){
            mondayToFridayHours.setDayOfWeek(i);
            Wait.waitSomeMills(500);
            actor.attemptsTo(ProviderSet.theBusinessHours(mondayToFridayHours));
        }

        actor.attemptsTo(ProviderSet.theBusinessHours(sundayHours));

    }

    public static ProviderSetDefault businessHours(){
        return Tasks.instrumented(ProviderSetDefault.class);
    }

}
