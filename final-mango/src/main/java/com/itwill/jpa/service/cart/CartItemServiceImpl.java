package com.itwill.jpa.service.cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dao.cart.CartDao;
import com.itwill.jpa.dao.product.ProductDao;
import com.itwill.jpa.dto.cart.CartItemDto;
import com.itwill.jpa.dto.product.ProductDto;
import com.itwill.jpa.entity.cart.Cart;
import com.itwill.jpa.entity.cart.CartItem;
import com.itwill.jpa.entity.product.Product;
import com.itwill.jpa.repository.cart.CartItemRepository;
import com.itwill.jpa.repository.cart.CartRepository;
import com.itwill.jpa.repository.product.ProductRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	CartItemRepository cartItemRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	ProductDao productDao;
	@Autowired
	CartDao cartDao;
	
	@Override
	public CartItem insert(CartItemDto cartItemDto) {
		Product product = productRepository.findById(cartItemDto.getProductId()).get();
		Cart cart = cartRepository.findById(cartItemDto.getCartId()).get();
		CartItem fCartItem = CartItem.builder()
							.product(product)
							.cart(cart)
							.cartItemId(cartItemDto.getCartItemId())
							.cartItemQty(cartItemDto.getCartItemQty())
							.build();
		cartItemRepository.save(fCartItem);
		return fCartItem;
		}

	@Override
	public CartItemDto update(Long cartItemId, int qty) throws Exception {
		Optional<CartItem> findCartItem = cartItemRepository.findById(cartItemId);
		CartItem updatedCart = null;
		if (findCartItem.isPresent()) {
			CartItem cartItem = findCartItem.get();
			cartItem.setCartItemQty(qty);
			updatedCart = cartItemRepository.save(cartItem);
			return CartItemDto.toDto(updatedCart);
		} else {
			throw new Exception("존재하지 않는 장바구니입니다.");
		}
	}

	@Override
	public void deleteByCartItemId(Long cartItemId) throws Exception {
		cartItemRepository.deleteById(cartItemId);
	}

	@Override
	public List<CartItemDto> findAllByCartId(Long cartId) {
		List<CartItem> cartItems = cartItemRepository.findAllByCart_CartId(cartId);
		List<CartItemDto> cartItemDtos = new ArrayList<>();

		for (CartItem cartItem : cartItems) {
			CartItemDto cartItemDto = new CartItemDto();
			cartItemDto.setCartItemId(cartItem.getCartItemId());
			cartItemDto.setCartItemQty(cartItem.getCartItemQty());
			cartItemDto.setCartId(cartItem.getCart().getCartId());
			cartItemDto.setProductId(ProductDto.toDto(cartItem.getProduct()).getProductNo());
			cartItemDtos.add(cartItemDto);
		}

		return cartItemDtos;
	}

	@Override
	public int calculateTotalByCartItemId(Long cartItemId) throws Exception {
		Optional<CartItem> findCartItem = cartItemRepository.findById(cartItemId);
		int totalPrice = 0;
		if (findCartItem.isPresent()) {
			CartItem cartItem = findCartItem.get();
			totalPrice = cartItem.getCartItemQty() * cartItem.getProduct().getProductPrice();
			cartItemRepository.save(cartItem);
			return totalPrice;
		} else {
			throw new Exception("해당 아이템을 찾을 수 없습니다.");
		}
	}

	@Override
	public Optional<ProductDto> getProductByProductId(Long productId) throws Exception {
		Optional<Product> productOptional = productRepository.findById(productId);
        return productOptional.map(ProductDto::toDto);
	}

	@Override
	public void deleteByCartItemIds(List<Long> cartItemIds) throws Exception {
		 for (Long cartItemId : cartItemIds) {
	            cartItemRepository.deleteById(cartItemId);
	        }
		
	}

	/*
	 * @Override public List<CartItem> insertAll(List<CartItem> cartItems) throws
	 * Exception { List<CartItem> insertedItems = new ArrayList<>(); for (CartItem
	 * cartItem : cartItems) { CartItem insertedCartItem =
	 * cartItemRepository.save(cartItem); insertedItems.add(insertedCartItem); }
	 * return insertedItems; }
	 * 
	 * @Override public CartItem update(Long cartItemId, int qty) throws Exception {
	 * Optional<CartItem> findCartItem = cartItemRepository.findById(cartItemId);
	 * CartItem updatedCart = null; if (findCartItem.isPresent()) { CartItem
	 * cartItem = findCartItem.get(); cartItem.setCartItemQty(qty); updatedCart =
	 * cartItemRepository.save(cartItem); }else { throw new
	 * Exception("존재하지 않는 장바구니입니다."); } return updatedCart; }
	 * 
	 * @Override public void deleteByCartItemId(Long cartItemId) {
	 * cartItemRepository.deleteById(cartItemId);; }
	 */
	
	
}
