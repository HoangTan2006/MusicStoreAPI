package com.musicstore.musicstoreapi.service;

import com.musicstore.musicstoreapi.entity.User;

public interface CartService {
    //Only create a default cart when the user has just registered an account
    void createCart(User user);
}
