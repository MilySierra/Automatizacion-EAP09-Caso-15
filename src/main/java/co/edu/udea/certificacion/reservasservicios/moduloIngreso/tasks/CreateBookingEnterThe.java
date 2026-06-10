package co.edu.udea.certificacion.reservasservicios.moduloIngreso.tasks;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions.ConfirmBookingSlot;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions.OpenBookingModal;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions.SelectBookingDate;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.Wait;
import lombok.RequiredArgsConstructor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

@RequiredArgsConstructor
public class CreateBookingEnterThe implements Task {

    private final String date;

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(OpenBookingModal.forFirstOffer());
        Wait.waitSomeMills(2000);
        actor.attemptsTo(SelectBookingDate.withDate(date));
        Wait.waitSomeMills(8000);  
        actor.attemptsTo(ConfirmBookingSlot.available());
        Wait.waitSomeMills(3000);
    }

    public static CreateBookingEnterThe forDate(String date) {
        return Tasks.instrumented(CreateBookingEnterThe.class, date);
    }
}