package co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ServiceActivationObjects {

    private ServiceActivationObjects() {}

    public static final Target BTN_ACTIVATE = Target.the("Activate service button")
            .located(By.cssSelector("button.btn-toggle-status.activate"));

    public static final Target BTN_DEACTIVATE = Target.the("Deactivate service button")
            .located(By.cssSelector("button.btn-toggle-status.deactivate"));

    public static final Target ALERT_SUCCESS = Target.the("Service status success alert")
            .located(By.cssSelector(".alert-message.success"));
}
