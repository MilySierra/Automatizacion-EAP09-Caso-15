package co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils;

public class ValidationMessages {

    public static final String LOGIN_TITLE = "Hola,\nBienvenido";

    public static final String NOT_VALID_PASSWORD = "La contraseña debe tener entre 8 y 64 caracteres, incluir al menos una letra" +
                        " mayúscula, una letra minúscula, un número y un carácter especial.";

    public static final String EMPTY_FIELD_SPANISH = "Completa este campo";

    public static final String EMPTY_FIELD_ENGLISH = "Please fill out this field.";

    public static final String AT_SIGN_MISSING_SPANISH = "Incluye un signo \"@\" en la dirección de correo electrónico";

    public static final String AT_SIGN_MISSING_ENGLISH = "Please include an '@' in the email address";

    // HU-10: Service activation/deactivation
    public static final String SERVICE_STATUS_UPDATED = "Estado del servicio actualizado correctamente.";

    // HU-17: Booking cancellation
    public static final String BOOKING_CANCELLED = "Reserva cancelada correctamente.";

    // HU-19: My bookings empty state
    public static final String NO_BOOKINGS = "Aún no has hecho ninguna reserva.";

}
