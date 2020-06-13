import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.dsl.DslPart;
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
import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.*;

@ExtendWith(PactConsumerTestExt.class)
public class PactForDeliveryConsumer {

    private final String CONSUMER = "delivery_consumer";
    private final String PROVIDER = "delivery_provider";


    @Pact(consumer = CONSUMER, provider = PROVIDER)
    public RequestResponsePact should_get_one_delivery_by_delivery_id(PactDslWithProvider builder) {

        PactDslJsonBody bodyResponse = new PactDslJsonBody()
                .integerType("id", anyInt())
                .stringType("deliveryNumber", anyString()) //de
                .stringType("orderNumber", anyString())
                .integerType("supplierId", anyInt())
                .stringType("fulfillmentType", anyString())
                .stringType("healthState", anyString());

        return builder
                .given("it has one delivery and status code is 200")
                .uponReceiving("a request to retrieve one delivery by id")
                .path("/producer/api/v1/delivery/1")
                .method("GET")
                .willRespondWith()
                .headers(Collections.singletonMap("Content-Type", "application/json"))
                .status(200)
                .body(bodyResponse)
                .toPact();
    }


    @Pact(consumer = CONSUMER, provider = PROVIDER)
    public RequestResponsePact should_get_fraud_deliveries(PactDslWithProvider builder) {

        PactDslJsonBody pactDslJsonBody = PactDslJsonArray.arrayMinLike(1)
                .integerType("id", anyInt())
                .stringType("deliveryNumber", anyString())
                .stringType("orderNumber", anyString())
                .integerType("supplierId", anyInt())
                .stringType("fulfillmentType", anyString())
                .stringType("healthState", anyString());


        return builder
                .given("it has fraud delivery list and status code 200")
                .uponReceiving("a request to retrieve fraud delivery list")
                .path("/producer/api/v1/delivery/fraud")
                .method("GET")
                .willRespondWith()
                .headers(Collections.singletonMap("Content-Type", "application/json"))
                .status(200)
                .body(pactDslJsonBody)
                .toPact();
    }

    @ExtendWith(PactConsumerTestExt.class)
    @PactTestFor(pactMethod = "should_get_one_delivery_by_delivery_id")
    @Test
    void should_get_one_delivery_by_delivery_id(MockServer mockServer) throws IOException {
        DeliveryService deliveryService = new DeliveryService(mockServer.getUrl());

        Delivery delivery = deliveryService.getDelivery(1);

        assertThat(delivery.getId(), is(anyInt()));
        assertThat(delivery.getDeliveryNumber(),is(anyString()));
        assertThat(delivery.getOrderNumber(),is(anyString()));
        assertThat(delivery.getSupplierId(), is(anyInt()));
        assertThat(delivery.getFulfillmentType(),is(anyString()));
        assertThat(delivery.getHealthState(),is(anyString()));
    }


    @ExtendWith(PactConsumerTestExt.class)
    @PactTestFor(pactMethod = "should_get_fraud_deliveries")
    @Test
    void should_get_fraud_deliveries(MockServer mockServer) throws IOException {

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
