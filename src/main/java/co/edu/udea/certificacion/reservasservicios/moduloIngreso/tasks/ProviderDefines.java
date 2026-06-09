package co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions.ProviderSetDefault;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class ProviderDefines implements Task {


    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(ProviderSetDefault.businessHours());
    }

    public static ProviderDefines defaultBussinesHours(){
        return Tasks.instrumented(ProviderDefines.class);
    }

}
