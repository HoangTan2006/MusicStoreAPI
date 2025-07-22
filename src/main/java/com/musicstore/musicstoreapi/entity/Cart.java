package com.musicstore.musicstoreapi.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "carts")
@Setter
@Getter
@Builder
public class Cart extends AbstractEntity<Long> {
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "total_amount")
    private Long totalAmount;

    @OneToMany(mappedBy = "cart")
    private Set<CartItem> cartItems;
}
