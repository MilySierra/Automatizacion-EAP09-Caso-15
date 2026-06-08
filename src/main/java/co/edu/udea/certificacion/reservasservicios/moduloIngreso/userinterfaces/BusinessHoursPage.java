package co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces;

import org.openqa.selenium.By;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class BusinessHoursPage extends PageObject{

    public static final Target BUSINESS_HOURS_TITLE = Target.the(
        "Title for the business hours page")
        .located(By.xpath("//*[@id=\"root\"]/div/main/section/div/div[1]/h2"));
  
    public static final Target DAY_ROW = Target.the(
        "Monday business hours row inputs")
        .locatedBy("//*[@id=\"root\"]/div/main/section/div/div[2]/div[{0}]");
    
    public static final Target TRANSACTION_RESULT_MESSAGE = Target.the(
        "Alert message for status notification")
        .locatedBy("//*[@id=\"root\"]/div/main/div/span");
    
    
}