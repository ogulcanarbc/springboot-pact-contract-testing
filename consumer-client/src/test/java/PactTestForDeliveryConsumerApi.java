import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.dsl.PactDslJsonArray;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.model.RequestResponsePact;
import model.Delivery;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import service.DeliveryService;

import java.io.IOException;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;

@ExtendWith(PactConsumerTestExt.class)
public class PactTestForDeliveryConsumerApi {

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

        return builder
                .given("get one delivery by id")
                .uponReceiving("a request to get delivery")
                .path("/producer/api/v1/delivery/1")
                .method("GET")
                .willRespondWith()
                .headers(Collections.singletonMap("Content-Type", "application/json"))
                .status(200)
                .body(bodyResponse)
                .toPact();
    }

    @ExtendWith(PactConsumerTestExt.class)
    @PactTestFor(pactMethod = "getDeliveryByIdHasOneDelivery")
    @Test
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

    @Pact(consumer = CONSUMER, provider = PROVIDER)
    public RequestResponsePact getFraudDeliveries(PactDslWithProvider builder) {

        PactDslJsonBody pactDslJsonBody = PactDslJsonArray.arrayEachLike()
                .integerType("id")
                .stringType("deliveryNumber")
                .stringType("orderNumber")
                .integerType("supplierId")
                .stringType("fulfillmentType")
                .stringType("healthState");

        return builder
                .given("get fraud deliveries")
                .uponReceiving("a request to get fraud deliveries")
                .path("/producer/api/v1/delivery/fraud")
                .method("GET")
                .willRespondWith()
                .headers(Collections.singletonMap("Content-Type", "application/json"))
                .status(200)
                .body(pactDslJsonBody)
                .toPact();
    }

    @ExtendWith(PactConsumerTestExt.class)
    @PactTestFor(pactMethod = "getFraudDeliveries")
    @Test
    void testGetFraudDeliveryVerificationConsumer(MockServer mockServer) throws IOException {

        DeliveryService deliveryService = new DeliveryService(mockServer.getUrl());
        Delivery[] fraudDeliveries = deliveryService.getFraudDeliveries();

        Assertions.assertNotNull(fraudDeliveries);

        Delivery fraudDelivery = fraudDeliveries[0];

        assertThat(fraudDelivery.getId(), is(greaterThanOrEqualTo(0)));
        Assertions.assertNotNull(fraudDelivery.getDeliveryNumber());
        Assertions.assertNotNull(fraudDelivery.getOrderNumber());
        Assertions.assertNotNull(fraudDelivery.getHealthState());
        Assertions.assertNotNull(fraudDelivery.getFulfillmentType());
        assertThat(fraudDelivery.getSupplierId(), is(greaterThanOrEqualTo(0)));

    }

}
