package com.oglcnarbc.repositoy;

import com.oglcnarbc.constant.FulfillmentType;
import com.oglcnarbc.constant.HealthState;
import com.oglcnarbc.entity.Delivery;
import lombok.*;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class InMemoryDeliveryRepository implements DeliveryRepository {

    private List<Delivery> deliveries;

    public InMemoryDeliveryRepository() {
        this.deliveries = Arrays.asList(

                Delivery.create(1, "PO473134", "Oğulcan", "Arabacı",
                        "Çamlık Caddesi, İstanbul", "Almıla", "Can",
                        "Kılıç Ali Paşa Mahallesi, Sakarya", "4513415", 0, FulfillmentType.FT.name(), HealthState.HEALTHY.name()),

                Delivery.create(2, "744131324", "Ali", "Arabacı",
                        "Çamlık Caddesi, İstanbul", "Alptigin", "Tuğrul",
                        "Lale Sokak, İstanbul", "4513416", 164, FulfillmentType.MP.name(), HealthState.DAMAGED.name()),

                Delivery.create(3, "PO689341", "Timur", "Kılıç",
                        "Çamlık Caddesi, İstanbul", "Aslan", "Demir",
                        "Hamidiye Mahallesi, Düzce", "4513417", 0, FulfillmentType.FT.name(), HealthState.FRAUD.name()),

                Delivery.create(4, "PO473134", "Zeynep", "Türk",
                        "Zümrütevler Caddesi, İstanbul", "Süleyman", "Birinci",
                        "Kırlangıç Sokak, Artvin", "4513418", 164, FulfillmentType.MP.name(), HealthState.HEALTHY.name())
        );
    }

    @Override
    public List<Delivery> findAll() {
        return deliveries;
    }

    @Override
    public Delivery findById(int id) {
        return deliveries.stream().filter(delivery -> id == delivery.getId()).findFirst().orElse(new Delivery());
    }
}
