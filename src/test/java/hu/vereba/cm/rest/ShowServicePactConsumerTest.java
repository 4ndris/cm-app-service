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
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@PactConsumerTest
@PactTestFor(providerName = "ShowProvider", pactVersion = PactSpecVersion.V3)
public class ShowServicePactConsumerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Pact(consumer="ShowConsumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) throws IOException {

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        PactDslJsonBody body = new PactDslJsonBody()
                .stringType("AbstractDesc")
                .numberType("AgeRating", 12)
                .datetime("AvailabilityFromUtcIso", "yyyy-MM-dd'T'HH:mm:ssXXX")
                .stringType("BackgroundUrl")
                .stringType("Cast")
                .stringType("Category", "SERIES")
                .stringType("Director")
                .stringType("EditedAbstract")
                .stringType("Genre")
                .stringType("Id")
                .stringType("Name", "Batwoman")
                .numberType("ProductionYear", 2019)
                .numberType("Seasons", 3);

        return builder
                .given("test state")
                .uponReceiving("ExampleJavaConsumerPactTest test interaction")
                    .path("/services/shows/tt8712204")
                    .method("GET")
                .willRespondWith()
                    .status(200)
                    .headers(headers)
                    .body(body)
                .toPact();
    }

    @Test
    void testOnMockServer(MockServer mockServer) throws JsonProcessingException {
        String actualJson = RestAssured.
            given()
                .baseUri(mockServer.getUrl())
            .when()
                .get("/services/shows/tt8712204")
            .then()
                .statusCode(200)
                .extract().asString();

        Show actual = objectMapper.readValue(actualJson, Show.class);
        Assertions.assertThat(actual)
                .extracting("Name", "ProductionYear", "Category")
                .containsExactly("Batwoman", 2019, Show.CategoryEnum.SERIES);

    }
}