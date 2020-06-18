package verification;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.loader.PactBrokerAuth;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import org.junit.runner.RunWith;


//@RunWith(SpringRestPactRunner.class)
@RunWith(PactRunner.class)
@Provider("delivery_provider")
@PactBroker(host = "localhost", port = "80",
        authentication = @PactBrokerAuth(username = "pact", password = "1234"))
public class DeliveryVerifyForContract {

    @TestTarget
    public final Target target = new HttpTarget("http", "localhost", 8099);


    @State(value = "it has one delivery and status code is 200")
    public void getOneDeliveryByDeliveryIdState() {
    }

    @State(value = "it has fraud delivery list and status code 200")
    public void getFraudDeliveriesState() {
    }
}
