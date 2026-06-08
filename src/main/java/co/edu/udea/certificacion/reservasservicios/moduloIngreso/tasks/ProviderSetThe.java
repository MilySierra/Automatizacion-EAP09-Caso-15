package co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions.ProviderSet;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.BusinessHours;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class ProviderSetThe implements Task {

    private final BusinessHours businessHours;

    public ProviderSetThe(BusinessHours businessHours){
        this.businessHours = businessHours;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(ProviderSet.theBusinessHours(businessHours));
    }

    public static ProviderSetThe businessHours(BusinessHours businessHours){
        return Tasks.instrumented(ProviderSetThe.class, businessHours);
    }

}
