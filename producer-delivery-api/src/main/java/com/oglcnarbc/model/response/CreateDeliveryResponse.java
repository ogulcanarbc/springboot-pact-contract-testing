package com.oglcnarbc.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CreateDeliveryResponse {

    private int id;
    private String deliveryNumber;
    private String orderNumber;
    private int supplierId;
    private String fulfillmentType;
    private String healthState;


}
