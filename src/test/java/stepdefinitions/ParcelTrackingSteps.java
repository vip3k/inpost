package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.ParcelStatusPage;
import utils.WebDriverManager;

import static org.junit.Assert.assertEquals;

public class ParcelTrackingSteps {

    WebDriver driver = WebDriverManager.getDriver();
    ParcelStatusPage parcelStatusPage = new ParcelStatusPage(driver);

    @Given("I navigate to the InPost tracking page")
    public void iNavigateToTheInPostTrackingPage() {
        driver.get("https://inpost.pl/sledzenie-przesylek");
        parcelStatusPage.acceptCookiesIfVisible();
    }

    @When("I search for parcel {string}")
    public void iSearchForParcel(String parcelNumber) {
        parcelStatusPage.searchParcel(parcelNumber);
    }

    @Then("I should see the status {string}")
    public void iShouldSeeTheStatus(String expectedStatus) {
        assertEquals(expectedStatus, parcelStatusPage.getParcelStatus());
    }
}