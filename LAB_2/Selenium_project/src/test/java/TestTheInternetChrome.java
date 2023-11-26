import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;

public class TestTheInternetChrome {
    WebDriver chromeDriver = new ChromeDriver();
    TestTheInternet testTheInternet = new TestTheInternet();

    @BeforeEach
    public void configureLogger(){
        testTheInternet.logger.setLevel(Level.FINE);
        Handler handler;
        try {
            handler = new FileHandler("reports/TheInternet_Chrome.xml");
            testTheInternet.logger.addHandler(handler);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    public void openWebApp(){
        testTheInternet.logger.info("Opened Chrome Browser");
        testTheInternet.openWebApp(chromeDriver);
    }

    @Test
    public void shouldLoginWithValidCredentials(){
        testTheInternet.shouldLoginWithCredentials(chromeDriver, "tomsmith", "SuperSecretPassword!");
        testTheInternet.shouldBeSuccessLogin(chromeDriver);
    }

    @Test
    public void shouldLoginWithInValidCredentials(){
        testTheInternet.shouldLoginWithCredentials(chromeDriver, "Selenium", "Selenium");
        testTheInternet.shouldBeFailedLogin(chromeDriver);
    }

    @AfterEach
    public void quitDriver(){
        chromeDriver.quit();
        testTheInternet.logger.info("Closed Chrome Browser");
    }
}
