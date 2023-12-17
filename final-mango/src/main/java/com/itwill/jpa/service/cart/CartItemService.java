package com.itwill.jpa.service.cart;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.itwill.jpa.dto.cart.CartItemDto;
import com.itwill.jpa.dto.product.ProductDto;
import com.itwill.jpa.entity.cart.CartItem;
import com.itwill.jpa.repository.cart.CartRepository;

public interface CartItemService {
	
	
	//상품 추가
	CartItem insert(CartItemDto cartItemDto);
	//수량 수정
	public CartItemDto update(Long cartItemId, int qty) throws Exception;
	
	//상품 삭제
	public void deleteByCartItemId(Long cartItemId) throws Exception;
	
	public void deleteByCartItemIds(List<Long> cartItemIds) throws Exception;
	
	//장바구니에 담긴 모든 상품
	public List<CartItemDto> findAllByCartId(Long cartId) throws Exception;
	
	//카트아이이템의 수량별 가격 업데이트
	public int calculateTotalByCartItemId(Long cartItemId) throws Exception;
	
	
	public Optional<ProductDto> getProductByProductId(Long productId) throws Exception;

	
		
	
}
