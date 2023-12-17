package com.itwill.jpa.service.cart;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.itwill.jpa.TeamProjectMangoApplicationTest;
import com.itwill.jpa.dto.cart.CartItemDto;
import com.itwill.jpa.dto.product.ProductDto;
import com.itwill.jpa.entity.cart.Cart;
import com.itwill.jpa.entity.cart.CartItem;
import com.itwill.jpa.entity.product.Product;
import com.itwill.jpa.entity.user.User;
import com.itwill.jpa.repository.cart.CartItemRepository;
import com.itwill.jpa.repository.cart.CartRepository;
import com.itwill.jpa.repository.user.UserRepository;

import jakarta.transaction.Transactional;

class CartItemServiceImplTest extends TeamProjectMangoApplicationTest {

	@Autowired
	CartItemServiceImpl cartItemServiceImpl;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	CartItemRepository cartItemRepository;
	/*
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
		void cartItemInsert() throws Exception{
		Optional<Cart> cart = cartRepository.findById(1L);
		System.out.println("cart>>>>>>>>>>>"+cart);
	    CartItemDto cartItemDto1 = new CartItemDto();
	    cartItemDto1.setCartItemQty(10);
	    Product product1 = new Product();
	    product1.setProductNo(5L);
	    cartItemDto1.setProductId(5L);
	    cartItemDto1.setCartId(cart.get().getCartId());
	    cartItemServiceImpl.insert(cartItemDto1);
	    System.out.println("cartItemDto1>>>>>>>>>>>>>>>>"+cartItemDto1);
	}
	*/
	@Test
	@Disabled
	@Transactional
	@Rollback(false)
	void deleteByCartItemId() throws Exception {
		cartItemServiceImpl.deleteByCartItemId(25L);
	}
	
	@Test
	@Disabled
	@Transactional
	@Rollback(false)
	void updateCartItemsQty() throws Exception {
		Long cartItemId = 22L;
		int updateQty = 26;
		cartItemServiceImpl.update(cartItemId, updateQty);
		
	}
	@Test
	@Disabled
	@Transactional
	@Rollback(false)
	void findAllByCartId() throws Exception {
		Optional<Cart> findCart = cartRepository.findById(1L);
		if (findCart.isPresent()) {
			List<CartItem> cartItems = cartItemRepository.findAll();
			System.out.println(cartItems);
		}
		
	}
	
	@Test
	@Disabled
	@Transactional
	@Rollback(false)
	void totalPrice() throws Exception {
		int total =cartItemServiceImpl.calculateTotalByCartItemId(20L);
		System.out.println(">>>>>>>>>>>>>>>>>>"+total);
	}
	
	@Test
	@Disabled
	@Transactional
	@Rollback(false)
	void getProductByProductId() throws Exception {
		CartItemDto cartItemDto = new CartItemDto();
		cartItemDto.setProductId(1L);
		Optional<ProductDto> productDto = cartItemServiceImpl.getProductByProductId(cartItemDto.getProductId());
		System.out.println(">>>>>>>>>>>>>>>>>>>"+productDto);
			
	}
	
	@Test
	//@Disabled
	@Transactional
	@Rollback(false)
	void calculateCartItem() throws Exception {
		CartItem cartItem = new CartItem();
		cartItem.setCartItemId(21L);
		int result = cartItemServiceImpl.calculateTotalByCartItemId(cartItem.getCartItemId());
		System.out.println(">>>>>>>>>>>>>>>>>"+result);
		
	}
	
	


}
