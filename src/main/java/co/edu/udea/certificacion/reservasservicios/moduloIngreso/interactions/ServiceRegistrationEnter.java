package co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.Service;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.Wait;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces.ServiceRegistrationObjects.*;

public class ServiceRegistrationEnter implements Interaction {

    private final Service service;
    private final String DEFAULT_DURATION = "45";

    public ServiceRegistrationEnter(Service service) {
        this.service = service;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Enter.theValue(service.getName()).into(INPUT_SERVICE_NAME));
        Wait.waitSomeMills(500);
        actor.attemptsTo(Enter.theValue(service.getDescription()).into(INPUT_SERVICE_DESCRIPTION));
        Wait.waitSomeMills(500);
        actor.attemptsTo(Enter.theValue(DEFAULT_DURATION).into(DURATION_INPUT));
        Wait.waitSomeMills(500);
        actor.attemptsTo(Click.on(BUTTON_SAVE));
    }

    public static ServiceRegistrationEnter information(Service service){
        return Tasks.instrumented(ServiceRegistrationEnter.class,service);
    }
}
