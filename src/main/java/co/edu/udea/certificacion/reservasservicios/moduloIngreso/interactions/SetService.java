package co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.ServiceAvailability;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.Wait;

import static co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces.ServiceAvailabilityPage.*;

import org.openqa.selenium.support.ui.Select;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

public class SetService implements Interaction {

    private final ServiceAvailability serviceHours;

    public SetService(ServiceAvailability serviceHours){
        this.serviceHours = serviceHours;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebElementFacade serviceSelect = SERVICE_SELECTOR.resolveFor(actor);
        Select select = new Select(serviceSelect);
        select.selectByIndex(1);

        Wait.waitSomeMills(500);

        actor.attemptsTo(Enter.theValue(serviceHours.getDate())
            .into(DATE_INPUT));  

        actor.attemptsTo(Enter.theValue(serviceHours.getStartTime())
            .into(START_TIME_INPUT));

        Wait.waitSomeMills(500);

        actor.attemptsTo(Enter.theValue(serviceHours.getEndTime())
            .into(END_TIME_INPUT));
        
        actor.attemptsTo(Click.on(CREATE_SLOT_BUTTON));
        Wait.waitSomeMills(10000);
    }

    public static SetService availability(ServiceAvailability serviceHours){
        return Tasks.instrumented(SetService.class, serviceHours);
    }

}
