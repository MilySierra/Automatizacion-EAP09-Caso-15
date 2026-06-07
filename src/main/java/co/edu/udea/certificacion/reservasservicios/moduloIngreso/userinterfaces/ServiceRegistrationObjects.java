package co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ServiceRegistrationObjects {
    public static final Target BUTTON_SERVICE = Target.the("Service button").locatedBy("//*[@id=\"root\"]/div/aside/nav/button[3]/span");
    public static final Target INPUT_SERVICE_NAME = Target.the("Name").locatedBy("//*[@id=\"root\"]/div/main/section/div/div[1]/div[1]/form/div[1]/input");
    public static final Target INPUT_SERVICE_DESCRIPTION = Target.the("Description").locatedBy("//*[@id=\"root\"]/div/main/section/div/div[1]/div[1]/form/div[2]/textarea");
    public static final Target BUTTON_SAVE = Target.the("Save button").locatedBy("//*[@id=\"root\"]/div/main/section/div/div[1]/div[1]/form/button");
    public static final Target MESSAGE_SERVICE = Target.the("Message").locatedBy("//*[@id=\"root\"]/div/main/div/span");

}
