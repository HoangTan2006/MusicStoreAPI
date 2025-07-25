package com.musicstore.musicstoreapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends AbstractEntity<Integer> {
    @Column(name = "name")
    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    @Column(name = "sold_quantity")
    private Integer soldQuantity;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<CartItem> cartItem;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetail;
}
