package com.itwill.jpa.service.cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dao.cart.CartDao;
import com.itwill.jpa.dto.cart.CartDto;
import com.itwill.jpa.dto.cart.CartItemDto;
import com.itwill.jpa.dto.product.ProductDto;
import com.itwill.jpa.dto.user.UserDto;
import com.itwill.jpa.entity.cart.Cart;
import com.itwill.jpa.entity.cart.CartItem;
import com.itwill.jpa.entity.product.Product;
import com.itwill.jpa.entity.user.User;
import com.itwill.jpa.repository.cart.CartItemRepository;
import com.itwill.jpa.repository.cart.CartRepository;
import com.itwill.jpa.repository.product.ProductRepository;
import com.itwill.jpa.repository.user.UserRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CartServiceImpl implements CartService {
	@Autowired
	CartRepository cartRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CartItemRepository cartItemRepository;
	@Autowired
	CartItemService cartItemService;
	
	@Override
	public void createCart(String userId) throws Exception {
		Optional<User> findUser= userRepository.findById(userId);
		Cart cart = Cart.builder()
				.cartitems(null)
				.cartTotPrice(0)
				.user(findUser.get())
				.build();
		
		cartRepository.save(cart);
	}
	@Override
	public CartDto deleteAllByCartId(Long cartId) throws Exception {
		Cart cart = cartRepository.findById(cartId).orElse(null);
		if (cart != null) {
	        cart.getCartitems().clear(); 
	        cartRepository.save(cart);
	    }
		CartDto cartDto = CartDto.toDto(cart);
		return cartDto;
	}

	@Override
	public CartDto calculateTotalPrice(Long cartId) throws Exception {
        // 1. cartId를 이용하여 장바구니 정보를 불러온다.
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new Exception("장바구니를 찾을 수 없습니다."));

        // 2. 장바구니 내의 상품들의 총 가격을 계산한다.
        int totalPrice = 0;
        for (CartItem cartItem : cart.getCartitems()) {
        	
            totalPrice += cartItem.getProduct().getProductPrice() * cartItem.getCartItemQty();
        }

        // 3. CartDto 객체에 결과를 담아서 반환한다.
        CartDto cartDto = new CartDto();
        cartDto.setCartId(cart.getCartId());
        cartDto.setUserId(cart.getUser().getUserId());
        cartDto.setCartTotPrice(totalPrice);
        cart.setCartTotPrice(totalPrice);
        cartRepository.save(cart);
        return cartDto;
    }
	
	@Override
	public CartDto findCartByCartId(Long cartId) throws Exception {
		Optional<Cart> findCart = cartRepository.findById(cartId);
		if (findCart.isPresent()) {
			Cart cart = findCart.get();
			return CartDto.toDto(cart);
		} else { 
			throw new Exception("해당 카트를 찾을 수 없습니다.");
		}
	}
	
	// 회원아이디에 해당하는 카트 찾기
	@Override
	public Cart findCartByUserId(String userId) throws Exception {
		Cart cart = cartRepository.findByUserId(userId);
		return cart;
	}
	
	/*
	@Override
	public CartDto getCartItems(List<CartItemDto> cartItemDtos) throws Exception {
		Cart cart = new Cart();
		cart=cartRepository.save(cart);
		CartDto cartDto = new CartDto();
		List<CartItemDto> cartItems = new ArrayList<>();
		for (CartItemDto cartItemDto : cartItemDtos) {
			Long productId = cartItemDto.getProductId();
			int qty = cartItemDto.getCartItemQty();
			Long cartId = cartItemDto.getCartId();
			
			Product product = productRepository.findById(productId).orElse(null);
			
			if (product != null) {
				CartItem cartItem = new CartItem();
				cartItem.setProduct(product);
				cartItem.setCartItemQty(qty);
				cartItem.setCart(cart);;
				CartItem savedCartItem = cartItemRepository.save(cartItem);
				CartItemDto savedCartItemDto = CartItemDto.toDto(savedCartItem);
				cartItems.add(savedCartItemDto);
			}
		}
		return null;
	}
	*/

	/*
	// 장바구니 생성
	@Override
	public Cart insert(Cart cart) {
		return cartRepository.save(cart);
	}

	// 장바구니에 담긴 상품들 조회
	@Override
	public List<Cart> getCartItems(Cart cart) {
		return cartRepository.findAll();
	}
	// 카트번호를 이용하여 장바구니 아이템 전체삭제
	@Override
	public void deleteAllByCartId(Long cartId) {
		Cart cart = cartRepository.findById(cartId).orElse(null);
		if (cart != null) {
	        cart.getCartitems().clear(); // 카트 아이템 리스트를 비웁니다.
	        cartRepository.save(cart); // 변경사항을 저장합니다.
	    }
	}
	// 장바구니에 담긴 상품들 총액계산
	@Override
	public int calculateTotalPrice(List<CartItem> cartItems) {
		int totPrice = 0;
		for (CartItem cartItem : cartItems) {
			Product product = cartItem.getProduct();
			int qty = cartItem.getCartItemQty();

			if (product != null) {
				int productPrice = product.getProductPrice();
				totPrice += productPrice * qty;
			}
		}
		return totPrice;
	}
	*/
}
