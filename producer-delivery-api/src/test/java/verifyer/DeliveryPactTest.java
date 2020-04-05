package verifyer;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.loader.PactBrokerAuth;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import com.oglcnarbc.DeliveryProducerApiApplication;
import com.oglcnarbc.model.response.CreateDeliveryResponse;
import com.oglcnarbc.service.DeliveryService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.client.RestClientException;

import java.net.URL;
import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

@RunWith(SpringRestPactRunner.class)
@Provider("delivery_provider")
@PactBroker(host = "localhost", port = "80",
        authentication = @PactBrokerAuth(username = "${pactBroker.auth.user}", password = "${pactBroker.auth.password}"))
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,classes =  DeliveryProducerApiApplication.class)
public class DeliveryPactTest {

    @MockBean
    private DeliveryService deliveryService;

    @TestTarget
    public final Target target = new HttpTarget(8089);


    @State(value = "get one delivery by id")
    public void getDelivery() {
        reset(deliveryService);

        //given
        CreateDeliveryResponse createDeliveryResponse = CreateDeliveryResponse.builder()
                .id(anyInt())
                .deliveryNumber(anyString())
                .orderNumber(anyString())
                .healthState(anyString())
                .fulfillmentType(anyString())
                .supplierId(anyInt())
                .build();

        //when
        when(deliveryService.getDeliveryById(anyInt())).thenReturn(createDeliveryResponse);

    }
}
