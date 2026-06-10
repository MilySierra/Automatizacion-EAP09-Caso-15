package co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.ServiceAvailability;

public class CreateServiceAvailability {

    public static ServiceAvailability createServiceHours(){
        
        ServiceAvailability serviceHours = new ServiceAvailability();

        serviceHours.setStartTime("10:30AM");
        serviceHours.setEndTime("11:30AM");

        LocalDate date = LocalDate.now().plusDays(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formatedDate= date.format(formatter);

        serviceHours.setDate(formatedDate);

        return serviceHours;
    }

}
