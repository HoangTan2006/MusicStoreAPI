package com.musicstore.musicstoreapi.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "addresses")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    public String toJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("note", this.note);
        map.put("houseNumber", this.houseNumber);
        map.put("street", this.street);
        map.put("ward", this.ward);
        map.put("city", this.city);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Convert Address to Json failure");
        }
    }
}
