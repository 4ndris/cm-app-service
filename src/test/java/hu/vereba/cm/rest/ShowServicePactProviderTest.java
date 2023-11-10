package hu.vereba.cm.rest;


import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.StateChangeAction;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Provider("ShowProvider")
@PactBroker(scheme="http", host = "localhost", port = "9292")
public class ShowServicePactProviderTest {
    
    @LocalServerPort
    public int port;

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
      context.verifyInteraction();
    }

    @BeforeEach
    public void setup(PactVerificationContext context) {

        context.setTarget(new HttpTestTarget("localhost", port));
    }

    @State(value = "initial state", action = StateChangeAction.SETUP)
    public void titleExists() {}

    @State(value = "initial state", action = StateChangeAction.TEARDOWN)
    public void titleExistsTearDown() {}

    @State(value = "show not exist", action = StateChangeAction.SETUP)
    public void titleNotExists() {}

    @State(value = "show not exist", action = StateChangeAction.TEARDOWN)
    public void titleNotExistsTearDown() {}
}
