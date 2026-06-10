package co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.SharedUserData;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.Wait;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces.ServiceRegistrationObjects.*;

public class CreateAvailabilityEnter implements Interaction {

    private void setReactInputValue(JavascriptExecutor js, WebElement element, String value) {
        js.executeScript(
            "var nativeInputValueSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
            "nativeInputValueSetter.call(arguments[0], arguments[1]);" +
            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
            "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
            element, value
        );
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        java.time.LocalDate nextDate = java.time.LocalDate.now().plusDays(2);
        while (nextDate.getDayOfWeek() == java.time.DayOfWeek.SATURDAY ||
            nextDate.getDayOfWeek() == java.time.DayOfWeek.SUNDAY) {
            nextDate = nextDate.plusDays(1);
        }
        String date = nextDate.toString();
        String serviceName = SharedUserData.getRegisteredService().getName();
        actor.attemptsTo(Click.on(BUTTON_SERVICE));
        Wait.waitSomeMills(2000);
        actor.attemptsTo(SelectFromOptions.byVisibleText(serviceName + " (ACTIVO)").from(SELECT_SERVICE_AVAILABILITY));
        Wait.waitSomeMills(500);

        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();

        WebElement dateInput = BrowseTheWeb.as(actor).find(INPUT_AVAILABILITY_DATE).getElement();
        setReactInputValue(js, dateInput, date);
        Wait.waitBetweenSteps();

        WebElement startInput = BrowseTheWeb.as(actor).find(INPUT_AVAILABILITY_START).getElement();
        setReactInputValue(js, startInput, "09:30");
        Wait.waitBetweenSteps();

        WebElement endInput = BrowseTheWeb.as(actor).find(INPUT_AVAILABILITY_END).getElement();
        setReactInputValue(js, endInput, "10:30");
        Wait.waitBetweenSteps();

        actor.attemptsTo(Click.on(BUTTON_CREATE_AVAILABILITY));
        Wait.waitAfterEndpoint();
    }

    public static CreateAvailabilityEnter information() {
        return Tasks.instrumented(CreateAvailabilityEnter.class);
    }
}