package com.musicstore.musicstoreapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "users")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractEntity<Long> {
    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "is_lock")
    private Boolean isLock;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "role_id"})
    )
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    private Set<Address> addresses;

    @OneToMany(mappedBy = "user")
    private Set<Order> orders;

    @OneToOne(mappedBy = "user")
    private Cart cart;
}
