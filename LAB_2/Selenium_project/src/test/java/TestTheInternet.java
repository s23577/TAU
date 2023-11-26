import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTheInternet {
    Logger logger = Logger.getLogger("");

    public void openWebApp(WebDriver webDriver){
        logger.info("I am opening The Internet Web App");
        webDriver.get("https://the-internet.herokuapp.com/");
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
    }

    public void shouldLoginWithCredentials(WebDriver webDriver, String login, String password){
        webDriver.findElement(By.xpath("//div[@id=\"content\"]//li//a[@href='/login']")).click();

        webDriver.findElement(By.id("username")).sendKeys(login);
        webDriver.findElement(By.id("password")).sendKeys(password);
        webDriver.findElement(By.className("radius")).click();
    }

    public void shouldBeSuccessLogin(WebDriver webDriver){
        String message = webDriver.findElement(By.xpath("//h4[@class='subheader']")).getText();

        try{
            assertEquals("Welcome to the Secure Area. When you are done click logout below.", message);
        } catch (Exception e){
            logger.severe("Assertion failed: " + e.getMessage());
        }

    }

    public void shouldBeFailedLogin(WebDriver webDriver){
        String message = webDriver.findElement(By.id("flash-messages")).getText();

        try{
            assertTrue(message.contains("Your username is invalid!"));
        } catch (Exception e){
            logger.severe("Assertion failed: " + e.getMessage());
        }
    }


}
