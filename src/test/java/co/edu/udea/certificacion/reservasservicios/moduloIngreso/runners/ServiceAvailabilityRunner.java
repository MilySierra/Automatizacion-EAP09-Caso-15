package co.edu.udea.certificacion.reservasservicios.moduloIngreso.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources/features/service_availability.feature"},
        glue = "co.edu.udea.certificacion.reservasservicios.moduloIngreso.stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class ServiceAvailabilityRunner {}
