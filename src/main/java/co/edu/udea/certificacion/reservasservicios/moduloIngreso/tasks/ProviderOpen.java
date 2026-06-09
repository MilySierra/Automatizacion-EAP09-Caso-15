package co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks;

import static co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces.DashboardPage.*;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.Wait;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

public class ProviderOpen implements Task{

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(SERVICE_AVAILABILITY_BUTTON));
        Wait.waitSomeMills(3000);
    }

    public static ProviderOpen serviceAvailabilityPage(){
        return Tasks.instrumented(ProviderOpen.class);
    }

}
