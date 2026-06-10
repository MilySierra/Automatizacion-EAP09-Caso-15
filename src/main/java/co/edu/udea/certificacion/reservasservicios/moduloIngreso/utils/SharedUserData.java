package co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.User;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.Service;
import lombok.Getter;
import lombok.Setter;

public class SharedUserData {

    @Setter
    @Getter
    private static User RegisteredCustomer = UserCreation.registeredCustomer();
    @Setter
    @Getter
    private static User RegisteredProvider = UserCreation.registeredProvider();
    @Setter
    @Getter
    private static Service RegisteredService;

}
