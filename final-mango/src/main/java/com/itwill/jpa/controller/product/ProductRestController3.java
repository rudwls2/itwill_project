package com.itwill.jpa.controller.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.controller.user.LoginCheck;
import com.itwill.jpa.dto.cart.CartItemDto;
import com.itwill.jpa.entity.cart.Cart;
import com.itwill.jpa.service.cart.CartItemServiceImpl;
import com.itwill.jpa.service.cart.CartServiceImpl;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/product")
public class ProductRestController3 {
	
	@Autowired
	private CartServiceImpl cartServiceImpl;
	
	@Autowired
	CartItemServiceImpl cartItemServiceImpl;
	
	
	@LoginCheck
	@PostMapping("/product_goods_Cart_detail/{productNo}/{userId}/{productQty}")
	public ResponseEntity<CartItemDto> GoodsDetail(@PathVariable(name = "productNo") Long productNo,
	                                              @PathVariable(name = "userId") String userId,
	                                              @PathVariable(name = "productQty") int productQty,
	                                              HttpSession session) {
	    try {
	        // 아이디에 해당되는 카트 찾기
	        Cart cart = cartServiceImpl.findCartByUserId(userId);

	        /* 카트 아이템 생성 
	         --> 필요한 데이터_ 카트번호, 상품번호, 수량 넣어주기 */
	        CartItemDto cartItemDto = new CartItemDto();
	        // 카트번호
	        cartItemDto.setCartItemId(1L);
	        // 상품번호 넣음
	        cartItemDto.setProductId(productNo);

	        // 수량 넣어줘야함 _추후 웹에서 qty값 받아오게 설정 필요
	        cartItemDto.setCartItemQty(productQty);

	        // 카트번호 넣음_생성된 카트 아이템을 아이디에 해당되는 카트에 넣기
	        cartItemDto.setCartId(cart.getCartId());

	        cartItemServiceImpl.insert(cartItemDto);
	        
	        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemDto);
	    } catch (Exception e) {
	        // 예외 처리
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}


}
