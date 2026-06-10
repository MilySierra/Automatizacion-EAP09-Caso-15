package co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CreateDates {

    public static String dateAfterNow(int daysAfter){
        LocalDate date = LocalDate.now().plusDays(daysAfter);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return date.format(formatter);

    }

}
