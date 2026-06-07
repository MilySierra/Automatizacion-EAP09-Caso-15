package co.edu.udea.certificacion.reservasservicios.moduloIngreso.models;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String name;
    private String lastname;
    private String email;
    private String password;
    private String passwordVerification;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
