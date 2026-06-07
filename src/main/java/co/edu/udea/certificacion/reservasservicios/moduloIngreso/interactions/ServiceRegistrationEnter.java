package co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.Service;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces.ServiceRegistrationObjects.*;

public class ServiceRegistrationEnter implements Interaction {

    private final Service service;

    public ServiceRegistrationEnter(Service service) {
        this.service = service;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Enter.theValue(service.getName()).into(INPUT_SERVICE_NAME));
        actor.attemptsTo(Enter.theValue(service.getDescription()).into(INPUT_SERVICE_DESCRIPTION));
        actor.attemptsTo(Click.on(BUTTON_SAVE));
    }

    public static ServiceRegistrationEnter information(Service service){
        return Tasks.instrumented(ServiceRegistrationEnter.class,service);
    }
}
