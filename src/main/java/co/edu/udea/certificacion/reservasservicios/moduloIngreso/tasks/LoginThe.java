package co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.User;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.Wait;

import static co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces.DashboardPage.*;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;

public class LoginThe implements Task{

    private final User provider;
    private static Target linkButton;

    public LoginThe(User provider){
        this.provider = provider;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(RegistrationOpenThe.browser());
        actor.attemptsTo(ProviderRegistrationEnterThe.information(provider));
        actor.attemptsTo(LogInEnterThe.credentials(provider));
        actor.attemptsTo(Click.on(linkButton));
        Wait.waitSomeMills(500);
    }

    public static LoginThe providerAtBussinesHours(User provider){
        linkButton = BUSINESS_HOURS_BUTTON;
        return Tasks.instrumented(LoginThe.class, provider);
    }

    public static LoginThe providerAtAvailabilityPage(User provider){
        linkButton = SERVICE_AVAILABILITY_BUTTON;
        return Tasks.instrumented(LoginThe.class, provider);
    }

}
