package assurance;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "All_adm_feature", 
    glue = "assurance",  
    plugin = { "html:target/cucumber/wikipedia.html"} ,
    		tags="not @fail")

public class TestAdmin {
  }