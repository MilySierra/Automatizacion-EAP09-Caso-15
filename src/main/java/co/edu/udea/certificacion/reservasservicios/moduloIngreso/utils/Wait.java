package co.edu.udea.certificacion.reservasservicios.moduloIngreso.utils;

import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;

public class Wait {
    private static Wait wait;

    private static final EnvironmentVariables ENV =
        SystemEnvironmentVariables
                .createEnvironmentVariables();

    private static final Integer DELAY_BETWEEN_STEPS =
        Integer.parseInt(
            ENV.getProperty(
                    "custom.debug.delayBetweenSteps",
                    "0"
            )
        );
    
    private static final Integer DELAY_AFTER_ENDPOINT =
        Integer.parseInt(
            ENV.getProperty(
                    "custom.debug.delayAfterEndpoint",
                    "0"
            )
        );

    private static final Integer DELAY_AFTER_REGISTER=
        Integer.parseInt(
            ENV.getProperty(
                    "custom.debug.delayAfterRegister",
                    "0"
            )
        );

    public Wait(Integer mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Wait waitSomeMills(Integer mills){
        wait = new Wait(mills);
        return wait;
    }

    public static Wait waitBetweenSteps(){
        wait = new Wait(DELAY_BETWEEN_STEPS);
        return wait;
    }

    public static Wait waitAfterEndpoint(){
        wait = new Wait(DELAY_AFTER_ENDPOINT);
        return wait;
    }

    public static Wait waitAfterRegister(){
        wait = new Wait(DELAY_AFTER_REGISTER);
        return wait;
    }
}
