package co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.User;
import lombok.Getter;
import lombok.Setter;

public class SharedUserData {

    @Setter
    @Getter
    private static User RegisteredCustomer;
    @Setter
    @Getter
    private static User RegisteredProvider;
}
