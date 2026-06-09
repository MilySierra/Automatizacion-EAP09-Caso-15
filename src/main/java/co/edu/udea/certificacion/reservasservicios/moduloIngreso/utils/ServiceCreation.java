package co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.Service;

public class ServiceCreation {

    public static Service randomService(){
        Service service = new Service();
        service.setName(RandomValues.randomServiceName());
        service.setDescription("Un servicio de " + service.getName());
        return service;
    }

}
