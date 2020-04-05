package com.oglcnarbc.entity;


import lombok.*;

import java.io.Serializable;

/**
 * @author Oğulcan Arabacı <oglcnarbc@gmail.com>
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Delivery implements Serializable {

    private int id;
    private String deliveryNumber;
    private String senderFirstName;
    private String senderLastName;
    private String senderAddress;
    private String recipientFirstName;
    private String recipientLastName;
    private String recipientAddress;
    private String orderNumber;
    private int supplierId;
    private String fulfillmentType;
    private String healthState;


    public static Delivery create(int id, String deliveryNumber, String senderFirstName, String senderLastName, String senderAddress,
                                  String recipientFirstName, String recipientLastName, String recipientAddress, String orderNumber, int supplierId, String fulfillmentType, String healthState) {
        Delivery delivery = Delivery.builder()
                .id(id)
                .deliveryNumber(deliveryNumber)
                .senderFirstName(senderFirstName)
                .senderLastName(senderLastName)
                .senderAddress(senderAddress)
                .recipientFirstName(recipientFirstName)
                .recipientLastName(recipientLastName)
                .recipientAddress(recipientAddress)
                .orderNumber(orderNumber)
                .supplierId(supplierId)
                .fulfillmentType(fulfillmentType)
                .healthState(healthState)
                .build();

        return delivery;
    }
}
