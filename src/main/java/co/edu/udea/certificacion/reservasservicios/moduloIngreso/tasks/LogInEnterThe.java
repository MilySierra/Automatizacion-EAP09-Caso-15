package co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions.LogInEnter;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.User;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.Wait;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class LogInEnterThe implements Task {

    private final User user;

    public LogInEnterThe(User user) {
        this.user = user;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(LogInEnter.credentials(user));
        Wait.waitAfterEndpoint();
    }

    public static LogInEnterThe credentials(User user){
        return Tasks.instrumented(LogInEnterThe.class, user);
    }
}
