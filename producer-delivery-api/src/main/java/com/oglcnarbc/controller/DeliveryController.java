package com.oglcnarbc.controller;

import com.oglcnarbc.DeliveryApi;
import com.oglcnarbc.model.response.CreateDeliveryResponse;
import com.oglcnarbc.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producer/api/v1")
@RequiredArgsConstructor
@DeliveryApi
public class DeliveryController {

    private final DeliveryService deliveryService;


    @GetMapping("/delivery")
    @ResponseStatus(HttpStatus.OK)
    public List<CreateDeliveryResponse> getDeliveries() {
        return deliveryService.getDeliveries();
    }


    @GetMapping("/delivery/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CreateDeliveryResponse getDeliveryById(@PathVariable("id") int id) {
        return deliveryService.getDeliveryById(id);
    }

    @GetMapping("/delivery/fraud")
    @ResponseStatus(HttpStatus.OK)
    public List<CreateDeliveryResponse> getFraudDeliveries() {
        return deliveryService.getFraudDelivery();
    }

}
