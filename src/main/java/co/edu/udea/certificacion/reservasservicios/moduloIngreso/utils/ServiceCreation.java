package co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.Service;
import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.User;

public class ServiceCreation {

    public static Service randomService(User provider){
        Service service = new Service();
        service.setName(RandomValues.randomServiceName() + "_provider_" + provider.getName());
        service.setDescription("Un servicio de " + service.getName());
        return service;
    }

}
