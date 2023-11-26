import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;

public class TestDemoqaChrome {
    WebDriver chromeDriver = new ChromeDriver();

    TestDemoqa testDemoqa = new TestDemoqa();

    @BeforeEach
    public void configureLogger(){
        testDemoqa.logger.setLevel(Level.FINE);
        Handler handler;
        try {
            handler = new FileHandler("reports/Demoqa_Chrome.xml");
            testDemoqa.logger.addHandler(handler);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @BeforeEach
    public void openWebApp(){
        testDemoqa.logger.info("Opened Chrome Browser");
        testDemoqa.openWebApp(chromeDriver);
    }

    @Test
    public void shouldProperlyClickCheckboxTree(){
        testDemoqa.shouldProperlyClickCheckboxTree(chromeDriver);
    }

    @Test
    public void shouldFillTheFormWithSuccess(){
        testDemoqa.shouldFillTheFormWithSuccess(chromeDriver);
    }

    @Test
    public void shouldFillTheFormWithWrongData(){
        testDemoqa.shouldFillTheFormWithWrongData(chromeDriver);
    }

    @AfterEach
    public void quitDriver(){
        chromeDriver.quit();
        testDemoqa.logger.info("Closed Chrome Browser");
    }
}
