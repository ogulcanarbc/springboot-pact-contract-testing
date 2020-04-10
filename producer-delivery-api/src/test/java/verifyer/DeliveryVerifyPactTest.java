package verifyer;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.loader.PactBrokerAuth;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import au.com.dius.pact.provider.spring.target.SpringBootHttpTarget;
import org.junit.runner.RunWith;


@RunWith(SpringRestPactRunner.class)
@Provider("delivery_provider")
@PactBroker(host = "localhost", port = "80",
        authentication = @PactBrokerAuth(username = "${pactBroker.auth.user}", password = "${pactBroker.auth.password}"))
public class DeliveryVerifyPactTest {

    @TestTarget
    public final Target target = new SpringBootHttpTarget();


    @State(value = "get one delivery by id")
    public void getOneDeliveryByIdResponseState() {
    }

    @State(value = "get fraud deliveries")
    public void getFraudDeliveries() {
    }
}
