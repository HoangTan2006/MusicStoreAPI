package com.musicstore.musicstoreapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "shipments")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shipment extends AbstractEntity<Long> {
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "carrier")
    private String carrier;

    @Column(name = "tracking_number")
    private String trackingNumber;

    @Column(name = "status")
    private String status;
}
