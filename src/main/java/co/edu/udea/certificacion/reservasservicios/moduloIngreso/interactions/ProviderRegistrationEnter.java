package co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.User;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces.RegistrationObjects.*;

public class ProviderRegistrationEnter implements Interaction {

    private final User user;

    public ProviderRegistrationEnter(User user) {
        this.user = user;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(BUTTON_ROL));
        actor.attemptsTo(Enter.theValue(user.getName()).into(INPUT_NAME));
        actor.attemptsTo(Enter.theValue(user.getLastname()).into(INPUT_LASTNAME));
        actor.attemptsTo(Enter.theValue(user.getEmail()).into(INPUT_EMAIL));
        actor.attemptsTo(Enter.theValue(user.getPassword()).into(INPUT_PASSWORD));
        actor.attemptsTo(Enter.theValue(user.getPasswordVerification()).into(INPUT_PASSWORD_VERIFICATION));
        actor.attemptsTo(Click.on(BUTTON));
    }

    public static ProviderRegistrationEnter information(User user){
        return Tasks.instrumented(ProviderRegistrationEnter.class, user);
    }
}
