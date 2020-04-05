package com.oglcnarbc.mapper;


import com.oglcnarbc.entity.Delivery;
import com.oglcnarbc.model.response.CreateDeliveryResponse;

import java.util.List;
import java.util.stream.Collectors;

public class DeliveryResponseMapper {


    public CreateDeliveryResponse entity2CreateDelivery(Delivery delivery) {
        return CreateDeliveryResponse.builder()
                .id(delivery.getId())
                .deliveryNumber(delivery.getDeliveryNumber())
                .fulfillmentType(delivery.getFulfillmentType())
                .orderNumber(delivery.getOrderNumber())
                .supplierId(delivery.getSupplierId())
                .healthState(delivery.getHealthState())
                .build();
    }

    public List<CreateDeliveryResponse> entity2CreateDeliveriesResponse(List<Delivery> deliveries) {
        return deliveries
                .stream()
                .map(this::entity2CreateDelivery)
                .collect(Collectors.toList());
    }


}
