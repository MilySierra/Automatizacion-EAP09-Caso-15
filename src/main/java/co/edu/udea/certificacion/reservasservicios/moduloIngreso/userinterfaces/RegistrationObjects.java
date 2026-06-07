package co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class RegistrationObjects {
    public static final Target INPUT_NAME = Target.the("Name").located(By.id("nombres"));
    public static final Target INPUT_LASTNAME = Target.the("Lastname").located(By.id("apellidos"));
    public static final Target INPUT_EMAIL = Target.the("Email").located(By.id("correo"));
    public static final Target INPUT_PASSWORD = Target.the("Password").located(By.id("contrasena"));
    public static final Target INPUT_PASSWORD_VERIFICATION = Target.the("Password verification").located(By.id("confirmarContrasena"));
    public static final Target BUTTON = Target.the("Button").locatedBy("//*[@id=\"root\"]/div/div[1]/div/main/form/button");
    public static final Target BUTTON_ROL = Target.the("Button rol").locatedBy("//*[@id=\"root\"]/div/div[1]/div/main/div/button[2]");
    public static final Target MESSAGE = Target.the("Message").locatedBy(" //*[@id=\"root\"]/div/div[1]/div/main/h1");
    public static final Target ERROR_MESSAGE = Target.the("Error message").locatedBy("//*[@id=\"root\"]/div/div[1]/div/main/form/p");
}
