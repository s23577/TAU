package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTheInternet {
    public Logger logger = Logger.getLogger("");

    private String message;

    public void openWebApp(WebDriver webDriver) {
        logger.info("I am opening TheInternet Web App");
        webDriver.get("https://the-internet.herokuapp.com/");
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
    }

    public void openLinkFormAuthentication(WebDriver webDriver) {
        webDriver.findElement(By.xpath("//div[@id=\"content\"]//li//a[@href='/login']")).click();
    }

    public void fillLogin(WebDriver webDriver, String login) {
        webDriver.findElement(By.id("username")).sendKeys(login);
    }

    public void fillPassword(WebDriver webDriver, String password) {
        webDriver.findElement(By.id("password")).sendKeys(password);
    }

    public void submitLoginForm(WebDriver webDriver) {
        webDriver.findElement(By.className("radius")).click();
    }

    public void checkProperLogin(WebDriver webDriver) {
        message = webDriver.findElement(By.xpath("//h4[@class='subheader']")).getText();

    }

    public void checkInProperLogin(WebDriver webDriver) {
        message = webDriver.findElement(By.id("flash-messages")).getText();
    }

    public void verifyIfLogged() {
        try {
            assertEquals("Welcome to the Secure Area. When you are done click logout below.", message);
        } catch (Exception e) {
            logger.severe("Assertion failed: " + e.getMessage());
        }
    }

    public void verifyIfNotLogged() {
        try {
            assertTrue(message.contains("Your username is invalid!"));
        } catch (Exception e) {
            logger.severe("Assertion failed: " + e.getMessage());
        }
    }

}
