package co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces.components;

import net.serenitybdd.core.pages.WebElementFacade;

public class DayRow {

    private final WebElementFacade row;

    public DayRow(WebElementFacade row) {
        this.row = row;
    }

    public WebElementFacade dayOfWeekName() {
        return row.thenFind("./div[1]");
    }

    public WebElementFacade activationSlider() {
        return row.thenFind("./div[2]/label/span");
    }

    public WebElementFacade activationSliderCheck() {
        return row.thenFind("./div[2]/label/input");
    }

    public WebElementFacade openingTimeInput() {
        return row.thenFind("./div[2]/div/input[1]");
    }

    public WebElementFacade closingTimeInput() {
        return row.thenFind("./div[2]/div/input[2]");
    }

    public WebElementFacade saveButton() {
        return row.thenFind("./button");
    }
    
}
