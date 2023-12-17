package com.itwill.jpa.repository.cart;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.itwill.jpa.dto.cart.CartItemDto;
import com.itwill.jpa.entity.cart.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	@Query(value = "select * from cart where user_id=?1",nativeQuery = true)
	public Cart findByUserId(String userId);

	
	
}
