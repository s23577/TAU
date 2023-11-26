import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;

public class TheInternetFirefox {
    WebDriver firefoxDriver = new FirefoxDriver();
    TestTheInternet testTheInternet = new TestTheInternet();

    @BeforeEach
    public void configureLogger(){
        testTheInternet.logger.setLevel(Level.FINE);
        Handler handler;
        try {
            handler = new FileHandler("reports/TheInternet_Firefox.xml");
            testTheInternet.logger.addHandler(handler);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    public void openWebApp(){
        testTheInternet.logger.info("Opened Chrome Browser");
        testTheInternet.openWebApp(firefoxDriver);
    }

    @Test
    public void shouldLoginWithValidCredentials(){
        testTheInternet.shouldLoginWithCredentials(firefoxDriver, "tomsmith", "SuperSecretPassword!");
        testTheInternet.shouldBeSuccessLogin(firefoxDriver);
    }

    @Test
    public void shouldLoginWithInValidCredentials(){
        testTheInternet.shouldLoginWithCredentials(firefoxDriver, "Selenium", "Selenium");
        testTheInternet.shouldBeFailedLogin(firefoxDriver);
    }

    @AfterEach
    public void quitDriver(){
        firefoxDriver.quit();
        testTheInternet.logger.info("Closed Chrome Browser");
    }
}
