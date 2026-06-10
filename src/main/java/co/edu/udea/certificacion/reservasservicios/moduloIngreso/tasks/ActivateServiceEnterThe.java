package co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions.ToggleServiceStatus;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.Wait;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class ActivateServiceEnterThe implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(ToggleServiceStatus.activate());
        Wait.waitAfterEndpoint();
    }

    public static ActivateServiceEnterThe service() {
        return Tasks.instrumented(ActivateServiceEnterThe.class);
    }
}
