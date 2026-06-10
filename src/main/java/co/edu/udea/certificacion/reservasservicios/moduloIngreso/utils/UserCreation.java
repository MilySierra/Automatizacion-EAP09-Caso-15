package co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils;

import co.edu.udea.certificacion.reservasservicios.moduloIngreso.models.User;

public class UserCreation {

    public static User randomBasicUserData(){
        User user = new User();
        user.setName(RandomValues.randomName());
        user.setLastname(RandomValues.randomLastName());
        return user;
    }

    public static User randomProvider(){
        User provider = randomBasicUserData();
        String name = provider.getName();
        provider.setEmail(RandomValues.randomProviderEmail(name));
        String password = "P4ss" + name.toUpperCase() +"word!";
        provider.setPassword(password);
        provider.setPasswordVerification(password);
        return provider;
    }

    public static User randomUserWithValidPassword(){
        User user = randomBasicUserData();
        String name = user.getName();
        user.setEmail(RandomValues.randomCustomerEmail(name));
        String password = "Password" + name.toLowerCase() + "485!";
        user.setPassword(password);
        user.setPasswordVerification(password);
        return user;
    }

    public static User randomUserWithNotValidPassword(){
        User user = randomBasicUserData();
        String name = user.getName();
        user.setEmail(RandomValues.randomCustomerEmail(name));
        user.setPassword(name.toLowerCase());
        user.setPasswordVerification(name.toLowerCase());
        return user;
    }

    public static User registeredCustomer(){
        User user = new User();
        user.setName("Ana");
        user.setLastname("Cordoba");
        user.setEmail("anita.cordoba@hotmail.com");
        user.setPassword("Anita!245");
        user.setPasswordVerification("Anita!245");
        return user;
    }

    public static User registeredProvider() {
        User provider = new User();
        provider.setName("AndresProvider");
        provider.setLastname("Barberia");
        provider.setEmail("andresbarber@lebarber.com");
        provider.setPassword("leB4rb3r#1duMond");
        provider.setPasswordVerification("leB4rb3r#1duMond");
        return provider;
    }
}
