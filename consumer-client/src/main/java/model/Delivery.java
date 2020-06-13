package model;

import lombok.*;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Delivery {

        private int id;
        private String deliveryNumber;
        private String orderNumber;
        private int supplierId;
        private String fulfillmentType;
        private String healthState;

}
