package co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class RandomValues {
    public static final List<String> RANDOM_MALE_NAMES =  new ArrayList<>(Arrays.asList("Juan", "Pedro", "Lucas", "Ernesto", "Sergio", "Samuel"));
    public static final List<String> RANDOM_FEMALE_NAMES =  new ArrayList<>(Arrays.asList("Ana", "Maria", "Karen", "Luisa", "Camila", "Catalina"));
    public static final List<String> RANDOM_LAST_NAMES =  new ArrayList<>(Arrays.asList("Gomez", "Lopez", "Alvarez", "Perez", "Jimenez", "Castillo"));
    public static final List<String> CUSTOMER_RANDOM_DOMAINS = new ArrayList<>(Arrays.asList("udea.edu.co", "gmail.com", "hotmail.com", "entity.com"));
    public static final List<String> PROVIDER_RANDOM_DOMAINS = new ArrayList<>(Arrays.asList("gmail.com", "barbers.co", "avogados-sas.com", "tintoreros.com", "haircuts.com"));
    public static final List<String> RANDOM_SERVICES = new ArrayList<>(Arrays.asList("Corte cabello hombre", "Masaje capilar", "Corte cabello dama", "Manicure semipermante"));

    public static <T> T randomItem(List<T> list) {

        return list.get(
                ThreadLocalRandom.current()
                        .nextInt(list.size())
        );
    }

    public static int randomIntegerBetween(int lowerBound, int upperBound){
        return ThreadLocalRandom.current().nextInt(lowerBound, upperBound + 1);
    }

    public static String randomName(){

        if (Math.random() < 0.5){
            return randomItem(RANDOM_MALE_NAMES);
        }
        return randomItem(RANDOM_FEMALE_NAMES);
    }

    public static String randomServiceName(){
        return randomItem(RANDOM_SERVICES);
    }

    public static String randomLastName(){
        return randomItem(RANDOM_LAST_NAMES);
    }

    public static String randomCustomerDomain(){
        return randomItem(CUSTOMER_RANDOM_DOMAINS);
    }

    public static String randomProviderDomain(){
        return randomItem(PROVIDER_RANDOM_DOMAINS);
    }

    public static String randomCustomerEmail(String name) {
        return name
                + UUID.randomUUID()
                    .toString()
                    .substring(0,8)
                + "@" + randomCustomerDomain();
    }

     public static String randomProviderEmail(String name) {
        return name
                + UUID.randomUUID()
                    .toString()
                    .substring(0,8)
                + "@" + randomProviderDomain();
    }

    public static String randomPassword(){
        return UUID.randomUUID().toString().substring(0,8);
    }

}