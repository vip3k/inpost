package stepdefinitions;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ParcelLockerSteps {
    private Response response;

    @When("I send a request to search parcel lockers in {string}")
    public void iSendARequestToSearchParcelLockersIn(String city) {
        response = RestAssured.given()
                .param("city", city)
                .when()
                .get("https://api.inpost.pl/parcellockers")
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    @Then("I save the parcel lockers data to parcellockers.{string}.json")
    public void iSaveTheParcelLockersDataToParcellockersCityJson(String city) throws IOException {
        {
            List<Map<String, Object>> lockers = response.jsonPath().getList("data");

              List<Map<String, Object>> processedLockers = lockers.stream()
                    .map(locker -> Map.of(
                            "name", locker.get("name"),
                            "postal_code", locker.get("postal_code"),
                            "coordinates", locker.get("coordinates")
                    ))
                    .toList();

            File outputFile = new File("parcellockers." + city + ".json");

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(outputFile, processedLockers);

        }
    }
}
