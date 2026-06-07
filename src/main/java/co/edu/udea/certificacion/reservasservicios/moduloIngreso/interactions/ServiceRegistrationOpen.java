package co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

import static co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces.ServiceRegistrationObjects.BUTTON_SERVICE;

public class ServiceRegistrationOpen implements Interaction {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(BUTTON_SERVICE));
    }

    public static ServiceRegistrationOpen browser(){
        return Tasks.instrumented(ServiceRegistrationOpen.class);
    }
}
