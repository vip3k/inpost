package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class WebDriverManager {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = ConfigReader.getProperty("browser").toLowerCase();

            switch (browser) {
                case "chrome" -> {
                    io.github.bonigarcia.wdm.WebDriverManager.chromiumdriver();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless");
                    driver = new ChromeDriver();
                }
                case "firefox" -> {
                    io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver();
                    driver = new FirefoxDriver();
                }
                case "edge" -> {
                    io.github.bonigarcia.wdm.WebDriverManager.edgedriver();
                    driver = new EdgeDriver();
                }
                default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
