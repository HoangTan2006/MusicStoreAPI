package com.musicstore.musicstoreapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payments")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends AbstractEntity<Long> {
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "transaction_code")
    private String transactionCode;

    @Column(name = "status")
    private String status;
}
