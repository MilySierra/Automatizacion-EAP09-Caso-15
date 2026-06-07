package co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions.ServiceRegistrationEnter;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.Service;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.Wait;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class ServiceRegistrationEnterThe implements Task {

    private final Service service;

    public ServiceRegistrationEnterThe(Service service) {
        this.service = service;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(ServiceRegistrationEnter.information(service));
        Wait.waitSomeMills(5000);
    }

    public static ServiceRegistrationEnterThe information(Service service){
        return Tasks.instrumented(ServiceRegistrationEnterThe.class, service);
    }
}
