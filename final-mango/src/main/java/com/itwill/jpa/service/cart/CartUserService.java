package com.itwill.jpa.service.cart;

import java.util.List;
import java.util.Optional;

import com.itwill.jpa.dto.cart.CartDto;
import com.itwill.jpa.dto.cart.CartItemDto;
import com.itwill.jpa.entity.cart.Cart;
import com.itwill.jpa.entity.cart.CartItem;
import com.itwill.jpa.entity.user.User;

public interface CartUserService {
	//장바구니 삭제
	public void deleteByUserIdCart(String userId) throws Exception;
	 
	


	
	
	
	
}
