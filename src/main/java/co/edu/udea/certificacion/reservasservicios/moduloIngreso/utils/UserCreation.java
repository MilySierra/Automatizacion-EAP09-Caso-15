package co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.User;

public class UserCreation {

    public static User randomProvider(){
        User provider = new User();
        String name = RandomValues.randomName();
        provider.setName(name);
        provider.setLastname(RandomValues.randomLastName());
        provider.setEmail(RandomValues.randomProviderEmail(name));
        String password = "P4ss" + name.toUpperCase() +"word!";
        provider.setPassword(password);
        provider.setPasswordVerification(password);
        return provider;
    }
}
