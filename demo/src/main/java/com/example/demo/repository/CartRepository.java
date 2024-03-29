package com.example.demo.repository;

import com.example.demo.model.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {
    List<CartEntity> findAllByUserId(Long id);

    void deleteAllByUserId(Long id);

    CartEntity findAllByUserIdAndProductId(Long id, Long productId);

    @Query("SELECT SUM(c.total) FROM CartEntity c WHERE c.userId=?1")
    Long sumPrice(Long userId);

    @Query("SELECT SUM(c.quantity) FROM CartEntity c WHERE c.userId=?1")
    Integer sumQuantity(Long id);

    @Query("SELECT COUNT(c.productId) FROM CartEntity c WHERE c.userId = ?1")
    Integer countCart(Long id);

    @Query("Select addCart  FROM CartEntity addCart WHERE addCart.userId = :user_id")
    List<CartEntity> getCartByUserId(@Param("user_id") Long user_id);
}
