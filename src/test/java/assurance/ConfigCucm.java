package assurance;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
	    features = "use_cases", 
	    glue = "fitness", 
	    plugin = {"pretty", "html:target/cucumber-reports.html"},
	    monochrome = true
	)
public class ConfigCucm {

}
