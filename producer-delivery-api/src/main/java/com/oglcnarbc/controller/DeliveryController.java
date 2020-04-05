package com.oglcnarbc;

import com.oglcnarbc.model.response.CreateDeliveryResponse;
import com.oglcnarbc.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producer/api/v1")
@RequiredArgsConstructor
@PersonalApi
public class DeliveryController {

    private final DeliveryService deliveryService;


    @GetMapping("/deliveries")
    @ResponseStatus(HttpStatus.OK)
    public List<CreateDeliveryResponse> getDeliveries() {
        return deliveryService.getDeliveries();
    }


    @GetMapping("/delivery/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CreateDeliveryResponse getDeliveriesById(@PathVariable("id") int id) {
        return deliveryService.getDeliveryById(id);
    }

}
