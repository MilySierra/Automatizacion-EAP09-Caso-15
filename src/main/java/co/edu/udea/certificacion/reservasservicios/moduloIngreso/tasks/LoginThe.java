package co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.User;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.Wait;

import static co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces.DashboardPage.*;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

public class LoginThe implements Task{

    private final User provider;

    public LoginThe(User provider){
        this.provider = provider;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(RegistrationOpenThe.browser());
        actor.attemptsTo(ProviderRegistrationEnterThe.information(provider));
        actor.attemptsTo(LogInEnterThe.credentials(provider));
        actor.attemptsTo(Click.on(BUSINESS_HOURS_BUTTON));
        Wait.waitSomeMills(500);
    }

    public static LoginThe provider(User provider){
        return Tasks.instrumented(LoginThe.class, provider);
    }

}
