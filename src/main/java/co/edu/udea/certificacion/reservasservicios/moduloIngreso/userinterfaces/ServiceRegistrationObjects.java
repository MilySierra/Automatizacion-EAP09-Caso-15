package co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ServiceRegistrationObjects {
    public static final Target BUTTON_SERVICE = Target.the("Service button").locatedBy("//*[@id=\"root\"]/div/aside/nav/button[3]/span");
    public static final Target INPUT_SERVICE_NAME = Target.the("Name").locatedBy("//*[@id=\"root\"]/div/main/section/div/div[1]/div[1]/form/div[1]/input");
    public static final Target INPUT_SERVICE_DESCRIPTION = Target.the("Description").locatedBy("//*[@id=\"root\"]/div/main/section/div/div[1]/div[1]/form/div[2]/textarea");
    public static final Target BUTTON_SAVE = Target.the("Save button").locatedBy("//*[@id=\"root\"]/div/main/section/div/div[1]/div[1]/form/button");
    public static final Target MESSAGE_SERVICE = Target.the("Message").locatedBy("//*[@id=\"root\"]/div/main/div/span");

    public static final Target SELECT_SERVICE_AVAILABILITY = Target.the("Service select for availability")
        .locatedBy("(//div[contains(@class,'card-panel')])[2]//select");

    public static final Target INPUT_AVAILABILITY_DATE = Target.the("Availability date input")
            .locatedBy("(//div[contains(@class,'card-panel')])[2]//input[@type='date']");

    public static final Target INPUT_AVAILABILITY_START = Target.the("Availability start time")
            .locatedBy("(//div[contains(@class,'card-panel')])[2]//input[@type='time'][1]");

    public static final Target INPUT_AVAILABILITY_END = Target.the("Availability end time")
        .locatedBy("(//input[@type='time'])[2]");

    public static final Target MESSAGE_AVAILABILITY = Target.the("Availability success message")
        .locatedBy("//*[@id=\"root\"]/div/main/div/span");

    public static final Target BUTTON_CREATE_AVAILABILITY = Target.the("Create availability button")
        .locatedBy("(//div[contains(@class,'card-panel')])[2]//button[contains(@class,'btn-secondary')]");

    public static final Target DURATION_INPUT = Target.the(
        "Duration of service for creation")
        .locatedBy("//*[@id=\"root\"]/div/main/section/div/div[1]/div[1]/form/div[3]/div[1]/input");
    
}
