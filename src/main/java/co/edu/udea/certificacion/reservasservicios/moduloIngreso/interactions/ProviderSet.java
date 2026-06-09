package co.edu.udea.certificacion.reservasservicios.moduloIngreso.interactions;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.BusinessHours;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces.BusinessHoursPage;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces.components.DayRow;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils.Wait;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;

public class ProviderSet implements Interaction{

    private final BusinessHours businessHours;

    public ProviderSet(BusinessHours businessHours){
        this.businessHours = businessHours;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        Integer day = businessHours.getDayOfWeek();
        WebElementFacade dayRow = BusinessHoursPage.DAY_ROW.of(day.toString()).resolveFor(actor);
        DayRow row = new DayRow(dayRow);

        WebElementFacade slider = row.activationSlider();
        WebElementFacade sliderCheck = row.activationSliderCheck();

        if (!sliderCheck.isSelected()){
            slider.click();
        }

        Wait.waitSomeMills(500);
        WebElementFacade openingTimeInput = row.openingTimeInput();
        WebElementFacade closingTimeInput = row.closingTimeInput();
        WebElementFacade button = row.saveButton();

        openingTimeInput.clear();
        openingTimeInput.type(businessHours.getOpeningTime());
        
        Wait.waitSomeMills(500);
        closingTimeInput.clear();
        closingTimeInput.type(businessHours.getClosingTime());

        Wait.waitSomeMills(500);
        button.click();
        Wait.waitSomeMills(1000);
    }

    public static ProviderSet theBusinessHours(BusinessHours businessHours){
        return Tasks.instrumented(ProviderSet.class,businessHours);
    }

}
