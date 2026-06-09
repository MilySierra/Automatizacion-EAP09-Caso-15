package co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces;

import org.openqa.selenium.By;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class ServiceAvailabilityPage extends PageObject {

    public static final Target SERVICE_SELECTOR = Target.the(
        "Selector list for registered services")
        .locatedBy("//*[@id=\"root\"]/div/main/section/div/div[1]/div[2]/form/div[1]/select");

    public static final Target DATE_INPUT = Target.the(
        "The date input for availability definition")
        .located(By.xpath("//*[@id=\"root\"]/div/main/section/div/div[1]/div[2]/form/div[2]/input"));
    
    public static final Target START_TIME_INPUT = Target.the(
        "The start time input for the slot time definition")
        .locatedBy("//*[@id=\"root\"]/div/main/section/div/div[1]/div[2]/form/div[3]/div[1]/input");

    public static final Target END_TIME_INPUT = Target.the(
        "The end time input for the slot time definition")
        .locatedBy("//*[@id=\"root\"]/div/main/section/div/div[1]/div[2]/form/div[3]/div[2]/input");

    public static final Target CREATE_SLOT_BUTTON = Target.the(
        "The create slot time button")
        .locatedBy("//*[@id=\"root\"]/div/main/section/div/div[1]/div[2]/form/button");
    
    public static final Target SLOT_CREATION_RESULT_MSG = Target.the(
        "Status message for result of the slot time definition")
        .locatedBy("//*[@id=\"root\"]/div/main/div/span");
        
}
