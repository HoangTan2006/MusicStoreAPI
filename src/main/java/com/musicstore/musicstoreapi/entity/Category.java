package com.musicstore.musicstoreapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "categories")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category extends AbstractEntity<Integer> {
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
