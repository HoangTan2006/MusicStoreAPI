package com.musicstore.musicstoreapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "categories")
@Setter
@Getter
@Builder
public class Category extends AbstractEntity<Integer> {
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;
}
