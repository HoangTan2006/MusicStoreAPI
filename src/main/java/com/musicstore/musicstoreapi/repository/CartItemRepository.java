package com.musicstore.musicstoreapi.repository;

import com.musicstore.musicstoreapi.dto.response.cartDTO.CartItemResponse;
import com.musicstore.musicstoreapi.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query("""
            SELECT new com.musicstore.musicstoreapi.dto.response.cartDTO.CartItemResponse(
                ci.id,
                p.id,
                p.name,
                ci.quantity,
                p.price * ci.quantity)
            FROM CartItem ci
            JOIN ci.product p
            WHERE ci.cart.user.id = :userId
            """)
    List<CartItemResponse> findAllCartItemWithProductPriceByUserId(Long userId);

    Optional<CartItem> findByIdAndCart_User_Id(Long cartItemId, Long userId);

    boolean existsByIdAndCart_User_Id(Long cartItemId, Long userId);

    void deleteAllByProduct_Id(Integer productId);
}