import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class TestDemoqa {
        Logger logger = Logger.getLogger("");

        public void openWebApp(WebDriver webDriver){
            logger.info("I am opening demoqa Web App");
            webDriver.get("https://demoqa.com/");
            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
        }

        public void shouldProperlyClickCheckboxTree(WebDriver webDriver){
            logger.info("I am starting test with clicking on checkbox tree");
            webDriver.findElement(By.className("card-up")).click();
            webDriver.findElement(By.id("item-1")).click();
            webDriver.findElement(By.className("rct-checkbox")).click();

            String message = webDriver.findElement(By.xpath("//div[@id=\"result\"]")).getText();

            try{
                assertTrue(message.contains("home"));
            } catch(Exception e){
                logger.severe("Assertion failed: " + e.getMessage());
            }
        }

        public void shouldFillTheFormWithSuccess(WebDriver webDriver){
            logger.info("I am starting test with filling form with valid data");
            webDriver.findElement(By.className("card-up")).click();
            webDriver.findElement(By.id("item-0")).click();
            webDriver.findElement(By.id("userName")).sendKeys("SeleniumTest");
            webDriver.findElement(By.id("userEmail")).sendKeys("SeleniumTest@gselenium.com");
            webDriver.findElement(By.id("currentAddress")).sendKeys("Selenium street 100");

            WebElement submitButton = webDriver.findElement(By.id("submit"));
            SeleniumTool.scrollToElementByDeltaY(submitButton, webDriver);
            submitButton.click();

            String output = webDriver.findElement(By.id("output")).getText();

            try{
                assertAll("Output data should be as submitted:",
                        () -> assertTrue(output.contains("SeleniumTest")),
                        () -> assertTrue(output.contains("SeleniumTest@gselenium.com")),
                        () -> assertTrue(output.contains("Selenium street 100")),
                        () -> assertFalse(output.contains("Permanent"))
                );
            } catch(Exception e){
                logger.severe("Assertion failed: " + e.getMessage());
            }
        }

        public void shouldFillTheFormWithWrongData(WebDriver webDriver){
            logger.info("I am starting test with filling form with invalid data");
            webDriver.findElement(By.className("card-up")).click();
            webDriver.findElement(By.id("item-0")).click();
            webDriver.findElement(By.id("userName")).sendKeys("SeleniumTest");
            webDriver.findElement(By.id("userEmail")).sendKeys("SeleniumTest");
            webDriver.findElement(By.id("currentAddress")).sendKeys("Selenium street 100");

            WebElement submitButton = webDriver.findElement(By.id("submit"));
            SeleniumTool.scrollToElementByDeltaY(submitButton, webDriver);
            submitButton.click();

            try{
                assertEquals("", webDriver.findElement(By.id("output")).getText());
            } catch(Exception e){
                logger.severe("Assertion failed: " + e.getMessage());
            }
        }
}
