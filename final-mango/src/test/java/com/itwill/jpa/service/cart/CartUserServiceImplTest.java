package com.itwill.jpa.service.cart;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.itwill.jpa.TeamProjectMangoApplicationTest;
import com.itwill.jpa.dao.cart.CartDaoImpl;
import com.itwill.jpa.dto.cart.CartDto;
import com.itwill.jpa.dto.cart.CartItemDto;
import com.itwill.jpa.dto.user.UserDto;
import com.itwill.jpa.entity.cart.Cart;
import com.itwill.jpa.entity.cart.CartItem;
import com.itwill.jpa.entity.product.Product;
import com.itwill.jpa.entity.user.User;
import com.itwill.jpa.repository.cart.CartItemRepository;
import com.itwill.jpa.repository.cart.CartRepository;
import com.itwill.jpa.repository.product.ProductRepository;
import com.itwill.jpa.repository.user.UserRepository;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

class CartUserServiceImplTest extends TeamProjectMangoApplicationTest {
	@Autowired
	CartUserServiceImpl cartUserServiceImpl;

	
	
	@Test
	@Disabled
	@Transactional
	@Rollback(false)
	void cartUserDelete() throws Exception {
		cartUserServiceImpl.deleteByUserIdCart("ggg");
	}

}