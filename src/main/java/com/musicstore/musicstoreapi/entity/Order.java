package com.musicstore.musicstoreapi.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "orders")
@Setter
@Getter
@Builder
public class Order extends AbstractEntity<Long> {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "total_amount")
    private Long totalAmount;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "order")
    private Set<OrderDetail> orderDetail;

    @OneToOne(mappedBy = "order")
    private Payment payment;
}
