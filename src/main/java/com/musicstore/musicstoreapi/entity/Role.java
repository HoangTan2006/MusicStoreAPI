package com.musicstore.musicstoreapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "roles")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role extends AbstractEntity<Long> {
    @Column(name = "name")
    private String name;
}
