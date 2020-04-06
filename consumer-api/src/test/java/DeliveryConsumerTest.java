import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.model.RequestResponsePact;
import model.Delivery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import service.DeliveryService;

import java.io.IOException;
import java.util.Collections;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "delivery_provider", port = "8091")
public class DeliveryConsumerTest {

    private final String CONSUMER = "delivery_consumer";
    private final String PROVIDER = "delivery_provider";


    @Pact(consumer = CONSUMER, provider = PROVIDER)
    public RequestResponsePact getDeliveryByIdHasOneDelivery(PactDslWithProvider builder) {

        PactDslJsonBody bodyResponse = new PactDslJsonBody()
                .integerType("id", 1)
                .stringType("deliveryNumber", "PO473134")
                .stringType("orderNumber", "4513415")
                .integerType("supplierId", 0)
                .stringType("fulfillmentType", "FT")
                .stringType("healthState", "HEALTHY");

        return builder.given("get one delivery by id")
                .uponReceiving("a request to get delivery")
                .path("/producer/api/v1/delivery/1")
                .method("GET")
                .willRespondWith()
                .headers(Collections.singletonMap("Content-Type", "application/json"))
                .status(200)
                .body(bodyResponse)
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "getDeliveryByIdHasOneDelivery")
    void testGetDeliveryVerificationConsumer(MockServer mockServer) throws IOException {

        DeliveryService deliveryService = new DeliveryService(mockServer.getUrl());

        Delivery delivery = deliveryService.getDelivery(1);

        Assertions.assertNotNull(delivery);
        Assertions.assertEquals(1, delivery.getId());
        Assertions.assertEquals("PO473134", delivery.getDeliveryNumber());
        Assertions.assertEquals("4513415", delivery.getOrderNumber());
        Assertions.assertEquals(0, delivery.getSupplierId());
        Assertions.assertEquals("FT", delivery.getFulfillmentType());
        Assertions.assertEquals("HEALTHY", delivery.getHealthState());


    }
}
