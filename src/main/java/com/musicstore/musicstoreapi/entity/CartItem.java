package com.musicstore.musicstoreapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "cart_items")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItem extends AbstractEntity<Long> {
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;
}
