package com.itwill.jpa.controller.cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.jpa.dto.cart.CartDto;
import com.itwill.jpa.dto.cart.CartItemDto;
import com.itwill.jpa.dto.product.ProductDto;
import com.itwill.jpa.dto.user.UserDto;
import com.itwill.jpa.entity.cart.Cart;
import com.itwill.jpa.entity.cart.CartItem;
import com.itwill.jpa.entity.product.Product;
import com.itwill.jpa.service.cart.CartItemService;
import com.itwill.jpa.service.cart.CartService;
import com.itwill.jpa.service.product.ProductService;
import com.itwill.jpa.service.user.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class CartController {
	@Autowired
	CartItemService cartItemService;
	@Autowired
	CartService cartService;
	@Autowired
	UserService userService;
	@Autowired
	ProductService productService;
	
	
/*
	@GetMapping("/cart_main")
	public String cart(@RequestParam(name = "productNo",required = false)Long productNo,@RequestParam(name = "cartId",required = false)Long cartId, Model model, HttpSession session) {
	    try {
	    	
			
	    	 String loginUser = (String) session.getAttribute("sUserId");
	    	 //프로덕트의 손길 Cart
	    	
			  Cart fUserCart = cartService.findCartByUserId(loginUser);
			  // 프로덕트 번호를 이용하여 프로덕트를 가져오기 Product product =
			  Product product = productService.findByProductNo(productNo).orElse(null);
			  
			  CartItem newItem = new CartItem();
			  newItem.setProduct(product);
			  newItem.setCart(fUserCart);
			  //newItem.setCartQty // 예시로 수량 1로 설정, 필요에 따라 조절
			  fUserCart.getCartitems().add(newItem); 
			  CartItemDto newItemDto = CartItemDto.toDto(newItem);
			 
	        
	        
			UserDto user = null;

			if (loginUser != null) {
				user = userService.findUser(loginUser);
			}
			session.setAttribute("loginUser", user);
			String userIdString = (user != null) ? user.getUserId() : null;
			model.addAttribute("userIdString", userIdString);
			
	        CartDto cart = cartService.findCartByCartId(newItemDto.getCartId());
	        List<CartItemDto> cartItems = cartItemService.findAllByCartId(cart.getCartId());
	        System.out.println(">>>>>>>>>cartOT"+cartItems);
	        
	        if (cartItems.isEmpty()) {
	            return "cart_empty";
	        } else {
	    
	            List<ProductDto> products = new ArrayList<>();
	            for (CartItemDto cartItem : cartItems) {
	                Optional<ProductDto> productOptional = cartItemService.getProductByProductId(cartItem.getProductId());
	                productOptional.ifPresent(products::add);
	            }
	            model.addAttribute("sUserId", loginUser);
	            model.addAttribute("cart",cart);
	            model.addAttribute("cartItems", cartItems);
	            model.addAttribute("products", products);
	            return "cart_main"; 
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "index";
	    }
	}
*/
	@GetMapping("/cart_main")
	public String cart(Model model, HttpSession session) {
	    try {
	        String loginUser = (String) session.getAttribute("sUserId");
	        System.out.println(">>>>>>>>>>>>>>>>>>>>>" + loginUser);
	        UserDto user = null;

	        if (loginUser != null) {
	            user = userService.findUser(loginUser);
	            System.out.println(">>>>>>>>>>>>>" + user);
	        }

	        // 세션에 로그인한 사용자 정보 설정
	        session.setAttribute("loginUser", user);

	        // 로그인한 사용자의 userId 가져오기
	        String userId = (user != null) ? user.getUserId() : null;
	        model.addAttribute("userIdString", loginUser);
	        System.out.println(">>>>>>>>>>>>>>>>" + loginUser);

	        if (userId != null) {
	            // userId로 해당 사용자의 cart 정보 가져오기
	            Cart cart = cartService.findCartByUserId(userId);
	            CartDto cartDto = CartDto.toDto(cart);
	            
	            if (cartDto != null) {
	                List<CartItemDto> cartItems = cartItemService.findAllByCartId(cartDto.getCartId());
	                System.out.println(">>>>>>>>>>>>>>cartItems"+cartItems);

	                if (cartItems.isEmpty()) {
	                    return "cart_empty";
	                } else {
	                    List<ProductDto> products = new ArrayList<>();
	                    for (CartItemDto cartItem : cartItems) {
	                        Optional<ProductDto> productOptional = cartItemService.getProductByProductId(cartItem.getProductId());
	                        productOptional.ifPresent(products::add);
	                    }
	                    model.addAttribute("sUserId", loginUser);
	                    model.addAttribute("cart", cart);
	                    model.addAttribute("cartItems", cartItems);
	                    model.addAttribute("products", products);
	                    return "cart_main";
	                }
	            } else {
	                return "cart_empty";
	            }
	        } else {
	            return "cart_empty";
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "index";
	    }

	}



	
}