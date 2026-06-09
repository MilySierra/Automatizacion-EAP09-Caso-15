package co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces;

import org.openqa.selenium.By;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class DashboardPage extends PageObject{

    public static final Target BUSINESS_HOURS_BUTTON = Target.the(
        "Business hours definition button")
        .located(By.xpath("//*[@id=\"root\"]/div/aside/nav/button[2]"));

    public static final Target SERVICE_AVAILABILITY_BUTTON = Target.the(
        "set up of service availability time slots button")
        .locatedBy("//*[@id=\"root\"]/div/aside/nav/button[3]");
        
}
