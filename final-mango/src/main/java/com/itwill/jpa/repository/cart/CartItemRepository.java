package com.itwill.jpa.repository.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.jpa.entity.cart.Cart;
import com.itwill.jpa.entity.cart.CartItem;
import com.itwill.jpa.entity.product.Product;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{
	 List<CartItem> findByProduct(Product product);
	 List<CartItem> findAllByCart_CartId(Long cartId);
	
}
