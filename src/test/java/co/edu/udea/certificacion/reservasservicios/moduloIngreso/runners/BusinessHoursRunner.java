package co.edu.udea.certificacion.reservasservicios.moduloIngreso.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources/features/business_hours.feature"},
        glue = "co.edu.udea.certificacion.reservasservicios.moduloIngreso.stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class BusinessHoursRunner {}
