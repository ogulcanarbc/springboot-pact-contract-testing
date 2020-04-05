package com.oglcnarbc.service;

import com.oglcnarbc.entity.Delivery;
import com.oglcnarbc.mapper.DeliveryResponseMapper;
import com.oglcnarbc.model.response.CreateDeliveryResponse;
import com.oglcnarbc.repositoy.InMemoryDeliveryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DeliveryService {

    private final InMemoryDeliveryRepository deliveryRepository;
    private final DeliveryResponseMapper deliveryResponseMapper;

    DeliveryService(){ //coupling
        deliveryRepository = new InMemoryDeliveryRepository();
        deliveryResponseMapper = new DeliveryResponseMapper();
    }


    public List<CreateDeliveryResponse> getDeliveries() {
        List<Delivery> getDelivery = deliveryRepository.findAll();
        return deliveryResponseMapper.entity2CreateDeliveriesResponse(getDelivery);
    }

    public CreateDeliveryResponse getDeliveryById(int id) {
        Delivery getDelivery = deliveryRepository.getDeliveryById(id);
        return deliveryResponseMapper.entity2CreateDelivery(getDelivery);
    }
}
