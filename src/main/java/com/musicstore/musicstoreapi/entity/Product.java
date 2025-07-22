package com.musicstore.musicstoreapi.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "products")
@Setter
@Getter
@Builder
public class Product extends AbstractEntity<Integer> {
    @Column(name = "name")
    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Long price;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    @Column(name = "sold_quantity")
    private Integer soldQuantity;

    @Column(name = "is_delete")
    private Boolean isDelete;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product")
    private Set<CartItem> cartItem;

    @OneToMany(mappedBy = "product")
    private Set<OrderDetail> orderDetail;
}
