import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.TestDemoqa;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;

public class StepDefinitionsDemoQA {

    WebDriver driver;

    TestDemoqa testDemoqa = new TestDemoqa();

    @Before
    public void configureLogger() {
        if (testDemoqa != null) {
            testDemoqa.logger.setLevel(Level.FINE);
            Handler handler;
            try {
                handler = new FileHandler("reports/Demoqa.xml");
                testDemoqa.logger.addHandler(handler);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Given("I open DEMOQA page using browser {string}")
    public void i_open_demoqa_page(String browserType) {
        driver = getWebDriver(browserType);
        testDemoqa.logger.info("Opened Browser: " + browserType);
        testDemoqa.openWebApp(driver);
    }

    @When("I click on elements module")
    public void i_click_on_elements_module() {
        testDemoqa.clickOnElementsModule(driver);
    }

    @When("I select Check Box")
    public void i_select_check_box() {
        testDemoqa.selectCheckBox(driver);
    }

    @When("I click on checkbox near Home option")
    public void i_click_on_checkbox_near_Home_option() {
        testDemoqa.checkHome(driver);
    }

    @Then("I should see {string} in the message")
    public void i_should_see_the_message(String message) {
        testDemoqa.shouldGetProperMessage(message);
    }

    @When("I select Text Box")
    public void i_select_text_box() {
        testDemoqa.selectTextBox(driver);
    }

    @When("I fill Name in form with {string}")
    public void i_fill_name(String name) {
        testDemoqa.fillName(driver, name);
    }

    @When("I fill Email in form with {string}")
    public void i_fill_email(String email) {
        testDemoqa.fillEmail(driver, email);
    }

    @When("I fill Address in form with {string}")
    public void i_fill_address(String address) {
        testDemoqa.fillAddress(driver, address);
    }

    @When("I click on button Submit")
    public void i_click_on_submit() {
        testDemoqa.submitForm(driver);
    }

    @Then("I should see the div below with provided data in form")
    public void i_should_see_the_div_with_provided_data_in_form() {
        testDemoqa.shouldSeeDivWithProvidedDataInForm(driver);
    }

    @Then("I should not see the div below with provided data in form")
    public void i_not_should_see_the_div_with_provided_data_in_form() {
        testDemoqa.shouldNotSeeDivWithProvidedDataInForm(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            System.out.println(driver.getCurrentUrl());
            driver.quit();
        }
        testDemoqa.logger.info("Closed Browser");
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

