package com.musicstore.musicstoreapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Date;

@MappedSuperclass
@Setter
@Getter
public abstract class AbstractEntity<T> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private T id;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;
}
