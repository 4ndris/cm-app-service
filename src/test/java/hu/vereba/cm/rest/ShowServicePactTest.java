package hu.vereba.cm.rest;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTest;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import io.restassured.RestAssured;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@PactConsumerTest
@PactTestFor(providerName = "ShowProvider", pactVersion = PactSpecVersion.V3)
public class ShowServicePactTest {

    @Pact(provider="ShowProvider", consumer="test_consumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) throws IOException {

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        return builder
                .given("test state")
                .uponReceiving("ExampleJavaConsumerPactTest test interaction")
                    .path("/services/shows/tt8712204")
                    .method("GET")
                .willRespondWith()
                    .status(200)
                    .headers(headers)
                    .body(new String(getContent("series/show_controller_get_tt8712204.json"), StandardCharsets.UTF_8))
                .toPact();
    }

    @Test
    void testOnMockServer(MockServer mockServer) throws JSONException {
        String expectedJson = getExpected();
        String actualJson = RestAssured.
            given()
                .baseUri(mockServer.getUrl())
            .when()
                .get("/services/shows/tt8712204")
            .then()
                .statusCode(200)
                .extract().asString();

        JSONAssert.assertEquals(expectedJson, actualJson, true);
    }

    private byte[] getContent(String file) throws IOException {
        Path resourceDirectory = Paths.get("src","test","resources", "test_requests", file);
        return Files.readAllBytes(resourceDirectory);
    }

    private String getExpected() {
        return """
                {
                  "AbstractDesc": "Ryan Wilder, armed with a passion for justice and a flair for speaking her mind, soars onto the streets of Gotham as Batwoman, a highly trained street fighter primed to snuff out the failing city's criminal resurgence. But don't call her a hero yet. In a city desperate for a savior, Ryan must overcome her own demons before embracing the call to be Gotham's symbol of hope.",
                  "AgeRating": 12,
                  "AvailabilityFromUtcIso": "2019-12-21T23:00:00+01:00",
                  "BackgroundUrl": "https://m.media-amazon.com/images/M/MV5BNWE4MGVhZjUtMjEwNy00NzJlLWE3ZmEtNWMyYmFlZmUzZTA0XkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_SX300.jpg",
                  "Cast": "Ruby Rose, Rachel Skarsten, Meagan Tandy, Camrus Johnson, Nicole Kang",
                  "Category": "SERIES",
                  "Director": "Marcos Siega",
                  "EditedAbstract": "Ryan Wilder, armed with a passion for justice and a flair for speaking her mind, soars onto the streets of Gotham as Batwoman, a highly trained street fighter primed to snuff out the failing city's criminal resurgence. But don't call her a hero yet. In a city desperate for a savior, Ryan must overcome her own demons before embracing the call to be Gotham's symbol of hope.",
                  "Genre": "action",
                  "Id": "tt8712204",
                  "Name": "Batwoman",
                  "ProductionYear": 2019,
                  "Seasons": 3
                }
                """;
    }
}