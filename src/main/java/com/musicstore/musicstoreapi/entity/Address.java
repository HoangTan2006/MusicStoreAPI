package com.musicstore.musicstoreapi.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@Setter
@Getter
@Builder
public class Address extends AbstractEntity<Long> {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "note")
    private String note;

    @Column(name = "house_number")
    private String houseNumber;

    @Column(name = "street")
    private String street;

    @Column(name = "ward")
    private String ward;

    @Column(name = "city")
    private String city;

    @Column(name = "is_default")
    private Boolean isDefault;
}
