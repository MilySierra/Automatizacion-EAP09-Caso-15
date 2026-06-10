package co.edu.udea.certificacion.reservasservicios.moduloIngreso.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class MyBookingsObjects {

    private MyBookingsObjects() {}

    /**
     * Botón "Mis Reservas" en la barra lateral del cliente.
     * Es el tercer botón del nav (igual que "Servicios y Cupos" para proveedor).
     */
    public static final Target BTN_MY_BOOKINGS = Target.the("Mis Reservas nav button")
            .locatedBy("//*[@id=\"root\"]/div/aside/nav/button[3]/span");

    /** Tabla de reservas del cliente. */
    public static final Target BOOKINGS_TABLE = Target.the("Bookings table")
            .located(By.cssSelector(".bookings-table"));

    /** Botón de cancelar reserva (solo visible para reservas en estado 'creada'). */
    public static final Target BTN_CANCEL = Target.the("Cancel booking button")
            .located(By.cssSelector("button.btn-table-action.danger"));

    /** Alerta de éxito tras una operación sobre reservas. */
    public static final Target ALERT_SUCCESS = Target.the("Booking success alert")
            .located(By.cssSelector(".alert-message.success"));

    /** Alerta de error (por ejemplo, al intentar cancelar una reserva ya cancelada). */
    public static final Target ALERT_ERROR = Target.the("Booking error alert")
            .located(By.cssSelector(".alert-message.error"));

    /** Mensaje de estado vacío cuando el cliente no tiene reservas. */
    public static final Target EMPTY_STATE_MESSAGE = Target.the("No bookings message")
            .located(By.cssSelector("td.text-center"));
}
