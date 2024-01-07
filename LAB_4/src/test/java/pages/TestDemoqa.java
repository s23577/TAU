package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import tools.SeleniumTool;

import java.time.Duration;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class TestDemoqa {
    public Logger logger = Logger.getLogger("");
    private String message;

    public void openWebApp(WebDriver webDriver) {
        logger.info("I am opening demoqa Web App");
        webDriver.get("https://demoqa.com/");
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
    }

    public void clickOnElementsModule(WebDriver webDriver) {
        logger.info("I am starting test with clicking on checkbox tree");
        webDriver.findElement(By.className("card-up")).click();
    }

    public void selectCheckBox(WebDriver webDriver) {
        webDriver.findElement(By.id("item-1")).click();
    }

    public void checkHome(WebDriver webDriver) {
        webDriver.findElement(By.className("rct-checkbox")).click();
        message = webDriver.findElement(By.xpath("//div[@id=\"result\"]")).getText();
    }

    public void shouldGetProperMessage(String message) {
        try {
            assertTrue(this.message.contains(message));
        } catch (Exception e) {
            logger.severe("Assertion failed: " + e.getMessage());
        }
    }

    public void selectTextBox(WebDriver webDriver) {
        webDriver.findElement(By.id("item-0")).click();
    }

    public void fillName(WebDriver webDriver, String name) {
        webDriver.findElement(By.id("userName")).sendKeys(name);
    }

    public void fillEmail(WebDriver webDriver, String email) {
        webDriver.findElement(By.id("userEmail")).sendKeys(email);
    }

    public void fillAddress(WebDriver webDriver, String address) {
        webDriver.findElement(By.id("currentAddress")).sendKeys(address);
    }

    public void submitForm(WebDriver webDriver) {
        WebElement submitButton = webDriver.findElement(By.id("submit"));
        SeleniumTool.scrollToElementByDeltaY(submitButton, webDriver);
        submitButton.click();
    }

    public void shouldSeeDivWithProvidedDataInForm(WebDriver webDriver) {
        String output = webDriver.findElement(By.id("output")).getText();

        try {
            assertAll("Output data should be as submitted:",
                    () -> assertTrue(output.contains("SeleniumTest")),
                    () -> assertTrue(output.contains("SeleniumTest@gselenium.com")),
                    () -> assertTrue(output.contains("Selenium street 100")),
                    () -> assertFalse(output.contains("Permanent"))
            );
        } catch (Exception e) {
            logger.severe("Assertion failed: " + e.getMessage());
        }
    }

    public void shouldNotSeeDivWithProvidedDataInForm(WebDriver webDriver) {
        try {
            assertEquals("", webDriver.findElement(By.id("output")).getText());
        } catch (Exception e) {
            logger.severe("Assertion failed: " + e.getMessage());
        }
    }
}
