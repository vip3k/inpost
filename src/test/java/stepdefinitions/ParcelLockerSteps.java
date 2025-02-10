package stepdefinitions;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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

    @Then("I save the parcel lockers data to parcellockers.{}.json")
    public void iSaveTheParcelLockersDataToParcellockersJson(String city) throws IOException {
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


            String actualJson = Files.readString(outputFile.toPath());

            String expectedJson = """
        [
            {"name": "Locker1", "postal_code": "12345", "coordinates": "12.34,56.78"},
            {"name": "Locker2", "postal_code": "67890", "coordinates": "98.76,54.32"}
        ]
        """;

            JSONAssert.assertEquals(expectedJson, actualJson, false);
        }
    }

}
