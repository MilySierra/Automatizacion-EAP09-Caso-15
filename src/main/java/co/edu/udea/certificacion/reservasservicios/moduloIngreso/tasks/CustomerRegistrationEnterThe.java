package co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions.CustomerRegistrationEnter;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.User;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.Wait;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class CustomerRegistrationEnterThe implements Task {

    private final User user;

    public CustomerRegistrationEnterThe(User user) {
        this.user = user;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(CustomerRegistrationEnter.information(user));
        Wait.waitAfterRegister();
    }

    public static CustomerRegistrationEnterThe information(User user){
        return Tasks.instrumented(CustomerRegistrationEnterThe.class, user);
    }

}
