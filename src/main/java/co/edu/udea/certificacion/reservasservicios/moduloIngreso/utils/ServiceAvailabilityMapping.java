package co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.ServiceAvailability;
import io.cucumber.datatable.DataTable;

public class ServiceAvailabilityMapping {

    public static ServiceAvailability getServiceAvailability(DataTable serviceAvailability){
        
        ServiceAvailability serviceHours = new ServiceAvailability();
        Map<String, String> data = serviceAvailability.asMap();

        serviceHours.setStartTime(data.get("startTime"));
        serviceHours.setEndTime(data.get("endTime"));

        LocalDate date = LocalDate.now().plusDays(RandomValues.randomIntegerBetween(7,15));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formatedDate= date.format(formatter);

        serviceHours.setDate(formatedDate);

        return serviceHours;
    }

    public static ServiceAvailability getExistingAvailability(DataTable serviceAvailability){
        
        ServiceAvailability serviceHours = new ServiceAvailability();
        Map<String, String> data = serviceAvailability.asMap();

        serviceHours.setStartTime(data.get("existingStartTime"));
        serviceHours.setEndTime(data.get("existingEndTime"));

        LocalDate date = LocalDate.now().plusDays(RandomValues.randomIntegerBetween(7,15));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formatedDate= date.format(formatter);

        serviceHours.setDate(formatedDate);

        return serviceHours;
    }
    
    
}
