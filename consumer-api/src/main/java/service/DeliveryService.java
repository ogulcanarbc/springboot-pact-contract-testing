package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Delivery;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import java.io.IOException;

public class DeliveryService {

    private String host;
    private String endpoint = "/producer/api/v1/delivery";

    public DeliveryService(String host) {
        this.host = host;
    }

    public Delivery getDelivery(int id) throws IOException {
        String content = Request.Get(host + endpoint + "/" + id)
                .execute()
                .returnContent().asString();

        return new ObjectMapper().readValue(content, Delivery.class);
    }
}
