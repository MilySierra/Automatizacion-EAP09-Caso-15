package co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions.ServiceRegistrationOpen;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.Wait;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Open;

public class ServiceRegistrationOpenThe implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        Wait.waitSomeMills(5000);
        actor.attemptsTo(Open.browserOn().thePageNamed("pages.serviceRegistrationUrl"));
        Wait.waitSomeMills(2000);
        actor.attemptsTo(ServiceRegistrationOpen.browser());
        Wait.waitAfterEndpoint();
    }

    public static ServiceRegistrationOpenThe browser(){
        return Tasks.instrumented(ServiceRegistrationOpenThe.class);
    }
}
