package com.musicstore.musicstoreapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "carts")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart extends AbstractEntity<Long> {
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.PERSIST)
    private List<CartItem> cartItems;
}
