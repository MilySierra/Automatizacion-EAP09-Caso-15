package co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.BusinessHours;
import io.cucumber.datatable.DataTable;

import java.util.Map;

public class BusinessHoursMapping {

    private static final Map<String, Integer> DAYS_OF_WEEK = Map.of(
        "LUNES", 1,
        "MARTES", 2,
        "MIERCOLES", 3,
        "JUEVES", 4,
        "VIERNES", 5,
        "SABADO", 6,
        "DOMINGO", 7
    );

    public static BusinessHours getBusinessHours(DataTable businessHours){
        
        BusinessHours businessHoursEntity = new BusinessHours();
        Map<String, String> data = businessHours.asMap();

        businessHoursEntity.setDay(data.get("dayOfWeek"));
        businessHoursEntity.setDayOfWeek(DAYS_OF_WEEK.get(data.get("dayOfWeek")));
        businessHoursEntity.setOpeningTime(data.get("openingTime"));
        businessHoursEntity.setClosingTime(data.get("closingTime"));

        return businessHoursEntity;
    }

}
