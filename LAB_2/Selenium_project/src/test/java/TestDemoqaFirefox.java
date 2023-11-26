import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestDemoqaFirefox {
    FirefoxDriver firefoxDriver = new FirefoxDriver();
    Logger logger = Logger.getLogger("");

    TestDemoqa testDemoqa = new TestDemoqa();

    @BeforeEach
    public void configureLogger(){
        logger.setLevel(Level.FINE);
        Handler handler;
        try {
            handler = new FileHandler("reports/Demoqa_Firefox.xml");
            logger.addHandler(handler);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    public void openWebApp(){
        testDemoqa.logger.info("Opened Firefox Browser");
        testDemoqa.openWebApp(firefoxDriver);
    }

    @Test
    public void shouldProperlyClickCheckboxTree(){
        testDemoqa.shouldProperlyClickCheckboxTree(firefoxDriver);
    }

    @Test
    public void shouldFillTheFormWithSuccess(){
        testDemoqa.shouldFillTheFormWithSuccess(firefoxDriver);
    }

    @Test
    public void shouldFillTheFormWithWrongData(){
        testDemoqa.shouldFillTheFormWithWrongData(firefoxDriver);
    }

    @AfterEach
    public void quitDriver(){
        firefoxDriver.quit();
        testDemoqa.logger.info("Closed Firefox Browser");
    }
}
