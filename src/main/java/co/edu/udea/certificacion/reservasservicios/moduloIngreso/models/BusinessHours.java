package co.edu.udea.certificacion.reservasservicios.moduloIngreso.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BusinessHours {

    private Integer dayOfWeek;
    private String day;
    private String openingTime;
    private String closingTime;
}
