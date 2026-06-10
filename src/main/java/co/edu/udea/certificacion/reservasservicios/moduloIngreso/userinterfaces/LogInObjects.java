package co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class LogInObjects {
    public static final Target INPUT_EMAIL_LOG_IN = Target.the("Email").locatedBy("//*[@id=\"root\"]/div/div[1]/div/main/form/div[1]/input");
    public static final Target INPUT_PASSWORD_LOG_IN = Target.the("Password").locatedBy("//*[@id=\"root\"]/div/div[1]/div/main/form/div[2]/input");
    public static final Target BUTTON_LOG_IN = Target.the("Button").locatedBy("//*[@id=\"root\"]/div/div[1]/div/main/form/button");
    public static final Target WELCOME_MESSAGE = Target.the("Welcome message").locatedBy("//*[@id=\"root\"]/div/main/header/div[1]/h1");
    public static final Target ERROR_MESSAGE_LOG_IN = Target.the("Error message").locatedBy("//*[@id=\"root\"]/div/div[1]/div/main/form/p");

}
