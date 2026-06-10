package co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces.ServiceActivationObjects;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;

public class ToggleServiceStatus implements Interaction {

    private final Target button;

    public ToggleServiceStatus(Target button) {
        this.button = button;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(button));
    }

    public static ToggleServiceStatus activate() {
        return Tasks.instrumented(ToggleServiceStatus.class, ServiceActivationObjects.BTN_ACTIVATE);
    }

    public static ToggleServiceStatus deactivate() {
        return Tasks.instrumented(ToggleServiceStatus.class, ServiceActivationObjects.BTN_DEACTIVATE);
    }
}
