package co.edu.udea.certificacion.reservasservicios.moduloIngreso.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ServiceAvailability {

    private String date;
    private String startTime;
    private String endTime;

}
