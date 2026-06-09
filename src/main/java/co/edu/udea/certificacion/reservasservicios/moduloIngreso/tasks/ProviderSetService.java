package co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions.SetService;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.ServiceAvailability;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class ProviderSetService implements Task {

    private final ServiceAvailability serviceHours;

    public ProviderSetService(ServiceAvailability serviceHours){
        this.serviceHours = serviceHours;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(SetService.availability(serviceHours));
    }

    public static ProviderSetService availability(ServiceAvailability serviceHours){
        return Tasks.instrumented(ProviderSetService.class,serviceHours);
    }

}
