import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.TestTheInternet;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;

public class StepDefinitionsTheInternet {
    WebDriver driver;

    TestTheInternet testTheInternet = new TestTheInternet();

    @Before
    public void configureLogger() {
        if (testTheInternet != null) {
            testTheInternet.logger.setLevel(Level.FINE);
            Handler handler;
            try {
                handler = new FileHandler("reports/TheInternet.xml");
                testTheInternet.logger.addHandler(handler);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Given("I open The Internet page using {string}")
    public void i_open_the_internet_page(String browserType) {
        driver = getWebDriver(browserType);
        testTheInternet.logger.info("Opened Browser: " + browserType);
        testTheInternet.openWebApp(driver);
    }

    @When("I open link Form Authentication")
    public void open_link_form_authentication() {
        testTheInternet.openLinkFormAuthentication(driver);
    }

    @When("I fill login in form with {string}")
    public void fill_form_login(String login) {
        testTheInternet.fillLogin(driver, login);
    }

    @When("I fill password in form with {string}")
    public void fill_form_password(String password) {
        testTheInternet.fillPassword(driver, password);
    }

    @When("I click on button Login")
    public void submit_login_form() {
        testTheInternet.submitLoginForm(driver);
    }

    @Then("I should properly login")
    public void should_properly_login() {
        testTheInternet.checkProperLogin(driver);
    }

    @Then("new page should be opened")
    public void new_page_should_be_opened() {
        testTheInternet.verifyIfLogged();
    }

    @Then("I should not login")
    public void should_not_login() {
        testTheInternet.checkInProperLogin(driver);
    }

    @Then("proper message should be displayed")
    public void proper_message_should_be_displayed() {
        testTheInternet.verifyIfNotLogged();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            System.out.println(driver.getCurrentUrl());
            driver.quit();
        }
        testTheInternet.logger.info("Closed Browser.");
    }

    private WebDriver getWebDriver(String browserType) {
        if ("chrome".equalsIgnoreCase(browserType)) {
            return new ChromeDriver();
        } else if ("firefox".equalsIgnoreCase(browserType)) {
            return new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser type");
        }
    }
}
