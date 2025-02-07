package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ParcelStatusPage extends BasePage {
    @FindBy(xpath = "//input[@name='number']")
    private WebElement searchBox;

    @FindBy(xpath = "//form[@class='tracking-form']//button[@type='submit' and contains(@class, 'btn--primary')]")
    private WebElement searchButton;

    @FindBy(xpath = "//p[@class='paragraph--component -big -secondary']")
    private WebElement statusText;

    @FindBy(xpath = "//*[@id='onetrust-accept-btn-handler']")
    private WebElement acceptButton;

    public ParcelStatusPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void searchParcel(String parcelNumber) {
        searchBox.sendKeys(parcelNumber);
        searchButton.click();
    }

    public String getParcelStatus() {
        return statusText.getText();
    }

    public void acceptCookiesIfVisible() {
        try {
            if (acceptButton.isDisplayed()) {
                acceptButton.click();
            }
        } catch (NoSuchElementException ignored) {
        }
    }
}
