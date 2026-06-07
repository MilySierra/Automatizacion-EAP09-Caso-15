package co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.User;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces.LogInObjects.*;

public class LogInEnter implements Interaction {

    private final User user;

    public LogInEnter(User user) {
        this.user = user;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Enter.theValue(user.getEmail()).into(INPUT_EMAIL_LOG_IN));
        actor.attemptsTo(Enter.theValue(user.getPassword()).into(INPUT_PASSWORD_LOG_IN));
        actor.attemptsTo(Click.on(BUTTON_LOG_IN));
    }

    public static LogInEnter credentials(User user){
        return Tasks.instrumented(LogInEnter.class, user);
    }
}
