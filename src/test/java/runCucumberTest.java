import io.cucumber.junit.*;
import org.junit.runner.*;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"},
features = "src/test/resources/Cucumber",
glue = "com.Sakila.api.SakilaApp.CucumberTests")
public class runCucumberTest {
}
