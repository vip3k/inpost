package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import utils.WebDriverManager;

public class TestHooks {
    WebDriver driver;

    @Before
    public void setUp() {
        driver = WebDriverManager.getDriver();
    }

    @After
    public void tearDown() {
        WebDriverManager.closeDriver();
    }
}
