package co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions.SaveGeneralScheduleEnter;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.Wait;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class SaveGeneralScheduleEnterThe implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(SaveGeneralScheduleEnter.defaultSchedule());
        Wait.waitAfterEndpoint();
    }

    public static SaveGeneralScheduleEnterThe information() {
        return Tasks.instrumented(SaveGeneralScheduleEnterThe.class);
    }
}