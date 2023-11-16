package hu.vereba.cm.rest;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTest;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import hu.vereba.cm.rest.model.Show;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@PactConsumerTest
@PactTestFor(providerName = "ShowProvider", pactVersion = PactSpecVersion.V3)
public class ShowServicePactConsumerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Pact(consumer="ShowConsumer")
    public RequestResponsePact specificTitle(PactDslWithProvider builder) throws IOException {

        Map<String, String> headers = new HashMap<>();
        //headers.put("Content-Type", "application/json");

        PactDslJsonBody body = new PactDslJsonBody()
                .stringType("AbstractDesc", "Sample abstract")
                .numberType("AgeRating")
                .datetime("AvailabilityFromUtcIso", "yyyy-MM-dd'T'HH:mm:ssXXX")
                .stringType("BackgroundUrl")
                .stringType("Cast")
                .stringMatcher("Category", "MOVIE|SERIES")
                .stringType("Director")
                .stringType("EditedAbstract", "Sample edited absract")
                .stringType("Genre")
                .stringType("Id")
                .stringType("Name", "Batwoman")
                .numberType("ProductionYear")
                .numberType("Seasons");

        return builder
                .given("initial state")
                .uponReceiving("retrieve specific show")
                    .path("/services/shows/tt8712204")
                    .method("GET")
                .willRespondWith()
                    .status(200)
                    .headers(headers)
                    .body(body)
                .toPact();
    }

    @Pact(consumer="ShowConsumer")
    public RequestResponsePact titleNotFound(PactDslWithProvider builder) throws IOException {

        Map<String, String> headers = new HashMap<>();
        //headers.put("Content-Type", "application/json");

        return builder
                .given("show not exist")
                .uponReceiving("retrieve specific show")
                    .path("/services/shows/tt8712205")
                    .method("GET")
                .willRespondWith()
                    .status(404)
                    .headers(headers)
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "specificTitle")
    void testSpecificTitle(MockServer mockServer) throws JsonProcessingException {
        String actualJson = RestAssured.
            given()
                .baseUri(mockServer.getUrl())
            .when()
                .get("/services/shows/tt8712204")
            .then()
                .statusCode(200)
                .extract().asString();

        Show actual = objectMapper.readValue(actualJson, Show.class);
        assertThat(actual.getName()).isEqualTo("Batwoman");
    }

    @Test
    @PactTestFor(pactMethod = "titleNotFound")
    void testTitleNotFound(MockServer mockServer) throws JsonProcessingException {
        String actualJson = RestAssured.
            given()
                .baseUri(mockServer.getUrl())
            .when()
                .get("/services/shows/tt8712205")
            .then()
                .statusCode(404)
                .extract().asString();

        assertThat(actualJson).isEqualTo("");
    }
}