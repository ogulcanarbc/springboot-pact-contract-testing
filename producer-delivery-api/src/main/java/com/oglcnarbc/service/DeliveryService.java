package com.oglcnarbc.service;

import com.oglcnarbc.common.exception.NoContentException;
import com.oglcnarbc.constant.HealthState;
import com.oglcnarbc.entity.Delivery;
import com.oglcnarbc.mapper.DeliveryResponseMapper;
import com.oglcnarbc.model.response.CreateDeliveryResponse;
import com.oglcnarbc.repositoy.InMemoryDeliveryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DeliveryService {

    private final InMemoryDeliveryRepository deliveryRepository;
    private final DeliveryResponseMapper deliveryResponseMapper;

    DeliveryService() { //coupling
        deliveryRepository = new InMemoryDeliveryRepository();
        deliveryResponseMapper = new DeliveryResponseMapper();
    }


    public List<CreateDeliveryResponse> getDeliveries() {
        List<Delivery> deliveries = deliveryRepository.findAll();

        if (deliveries.isEmpty()) {
            log.error("Deliveries not found");
            throw new NoContentException("Deliveries not found");
        }

        return deliveryResponseMapper.entity2CreateDeliveriesResponse(deliveries);
    }

    public CreateDeliveryResponse getDeliveryById(int id) {

        Delivery delivery = deliveryRepository.findById(id);

        if (delivery.getId() == 0) {
            log.error("No Delivery found by deliveryId: {}", id);
            throw new NoContentException("No Delivery found by deliveryId: " + id);
        }
        return deliveryResponseMapper.entity2CreateDelivery(delivery);
    }

    public List<CreateDeliveryResponse> getFraudDelivery() {

        List<Delivery> deliveries = deliveryRepository.findAll();
        if (deliveries.isEmpty()) {
            log.error("Deliveries not found");
            throw new NoContentException("Deliveries not found");
        }

        List<Delivery> fraudDeliveries = deliveries.stream()
                .filter(delivery -> delivery.getHealthState()
                        .equals(HealthState.FRAUD.name()))
                .collect(Collectors.toList());

        return deliveryResponseMapper.entity2CreateDeliveriesResponse(fraudDeliveries);
    }
}
