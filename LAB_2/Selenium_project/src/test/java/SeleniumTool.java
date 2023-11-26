import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SeleniumTool {
    public static void scrollToElementByDeltaY(WebElement element, WebDriver driver){
        int deltaY = element.getRect().y;
        new Actions(driver)
                .scrollByAmount(0, deltaY)
                .perform();
    }
}
