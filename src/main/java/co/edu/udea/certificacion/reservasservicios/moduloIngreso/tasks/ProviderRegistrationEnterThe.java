package co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions.ProviderRegistrationEnter;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.User;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.Wait;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class ProviderRegistrationEnterThe implements Task {

    private final User user;

    public ProviderRegistrationEnterThe(User user) {
        this.user = user;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(ProviderRegistrationEnter.information(user));
        Wait.waitSomeMills(10000);
    }
    
    public static ProviderRegistrationEnterThe information(User user){
        return Tasks.instrumented(ProviderRegistrationEnterThe.class, user);
    }
}
