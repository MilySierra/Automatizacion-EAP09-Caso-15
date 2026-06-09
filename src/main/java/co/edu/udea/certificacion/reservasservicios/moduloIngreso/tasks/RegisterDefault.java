package co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions.ProviderSetDefault;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions.ServiceRegistrationOpen;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.User;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.Service;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.ServiceCreation;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class RegisterDefault implements Task{

    private final User provider;

    public RegisterDefault(User provider){
        this.provider = provider;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(LoginThe.providerAtBussinesHours(provider));
        actor.attemptsTo(ProviderSetDefault.businessHours());

        Service service = ServiceCreation.randomService();
        actor.attemptsTo(ServiceRegistrationOpen.browser());
        actor.attemptsTo(ServiceRegistrationEnterThe.information(service));
    }

    public static RegisterDefault provider(User provider){
        return Tasks.instrumented(RegisterDefault.class, provider);
    }

}
