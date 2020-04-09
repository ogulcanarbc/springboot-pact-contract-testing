package com.oglcnarbc.repositoy;

import com.oglcnarbc.entity.Delivery;

import java.util.List;

public interface DeliveryRepository {

    List<Delivery> findAll();

    Delivery findById(int id);
}
