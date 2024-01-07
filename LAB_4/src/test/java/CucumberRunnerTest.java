import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectDirectories;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectDirectories("src/test/resources/features")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "html:target/chrome/cucumberHtmlReport.html, pretty")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "")
public class CucumberRunnerTest {
}