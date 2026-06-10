package co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.Wait;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;

import static co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces.GeneralScheduleObjects.*;


public class SaveGeneralScheduleEnter implements Interaction {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(BUTTON_HORARIOS_ATENCION));
        Wait.waitBetweenSteps();
        actor.attemptsTo(Click.on(Target.the("Save LUNES").locatedBy("(//button[contains(@class,'btn-save-row')])[1]")));
        Wait.waitBetweenSteps();
        actor.attemptsTo(Click.on(Target.the("Save MARTES").locatedBy("(//button[contains(@class,'btn-save-row')])[2]")));
        Wait.waitBetweenSteps();
        actor.attemptsTo(Click.on(Target.the("Save MIERCOLES").locatedBy("(//button[contains(@class,'btn-save-row')])[3]")));
        Wait.waitBetweenSteps();
        actor.attemptsTo(Click.on(Target.the("Save JUEVES").locatedBy("(//button[contains(@class,'btn-save-row')])[4]")));
        Wait.waitBetweenSteps();
        actor.attemptsTo(Click.on(Target.the("Save VIERNES").locatedBy("(//button[contains(@class,'btn-save-row')])[5]")));
        Wait.waitBetweenSteps();
        actor.attemptsTo(Click.on(Target.the("Save SABADO").locatedBy("(//button[contains(@class,'btn-save-row')])[6]")));
        Wait.waitBetweenSteps();
    }

    public static SaveGeneralScheduleEnter defaultSchedule() {
        return Tasks.instrumented(SaveGeneralScheduleEnter.class);
    }
}