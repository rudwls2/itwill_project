package com.itwill.jpa.controller.cart;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.dto.cart.CartDto;
import com.itwill.jpa.dto.cart.CartItemDto;
import com.itwill.jpa.entity.cart.Cart;
import com.itwill.jpa.entity.cart.CartItem;
import com.itwill.jpa.repository.cart.CartRepository;
import com.itwill.jpa.service.cart.CartItemServiceImpl;
import com.itwill.jpa.service.cart.CartService;
import com.itwill.jpa.service.cart.CartServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class CartRestController {

	@Autowired
	CartService cartService;
	@Autowired
	CartServiceImpl cartServiceImpl;
	@Autowired
	CartItemServiceImpl cartItemServiceImpl;
	@Autowired
	CartRepository cartRepository;
/*
	@Operation(summary = "장바구니생성[성공]")
	@PostMapping("/cart_main/create")
	public ResponseEntity<CartDto> createCart(@RequestBody CartDto dto) {
	    try {
	        CartDto createCart = cartServiceImpl.createCart(dto);
	        return ResponseEntity.ok(createCart);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}
	*/

	@Operation(summary = "장바구니 상품전체삭제[성공]")
	@DeleteMapping("/cart_main/delete/{cartId}")
	public void deleteAllItemsInCart(@PathVariable(value = "cartId") Long cartId) {
			try {
				cartServiceImpl.deleteAllByCartId(cartId);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	/*
	 * @Operation(summary = "총액 계산")
	 * 
	 * @PostMapping("/calculateTotalPrice/{cartId}") public ResponseEntity<CartDto>
	 * calculateTotalPrice(@RequestBody List<CartItemDto> cartItemDtos) { try {
	 * CartDto cartDto = cartService.calculateTotalPrice(cartItemDtos);
	 * cartDto.getCartId(); return ResponseEntity.ok(cartDto); } catch (Exception e)
	 * { return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); } }
	 */
	
	@Operation(summary = "총합계산[성공]")
	@GetMapping("/cart_main/total/{cartId}")
	public ResponseEntity<CartDto> calculateTotalPrice(@PathVariable(value = "cartId") Long cartId) {
		try {
			CartDto cartTotPrice = cartService.calculateTotalPrice(cartId);
			return ResponseEntity.ok(cartTotPrice);
		} catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
	}

	
}
