package com.itwill.jpa.controller.order;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwill.jpa.controller.user.LoginCheck;
import com.itwill.jpa.dto.order.CouponDto;
import com.itwill.jpa.dto.order.OrderDto;
import com.itwill.jpa.dto.order.OrderItemDto;
import com.itwill.jpa.dto.user.UserDto;
import com.itwill.jpa.entity.product.Product;
import com.itwill.jpa.entity.user.User;
import com.itwill.jpa.service.order.CouponService;
import com.itwill.jpa.service.order.OrderItemService;
import com.itwill.jpa.service.order.OrderService;
import com.itwill.jpa.service.product.ProductService;
import com.itwill.jpa.service.user.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderItemService orderItemService;

	@Autowired
	private ProductService productService;

	@Autowired
	private CouponService couponService;
	
	@Autowired
	private UserService userService;
	
	
	@LoginCheck
	@GetMapping("/order_membership")
	public String orderMembershipPage(Model model, HttpSession session, RedirectAttributes redirectAttributes) {

		
		try {
			/*************** 유저정보 ***************/
			
			String userId = (String) session.getAttribute("sUserId");
			model.addAttribute("user_id", userId);
			
			//로그인정보없으면 로그인으로
			if (userId == null) {
				redirectAttributes.addAttribute("msg", "로그인이 필요합니다");
				return "redirect:/user_login_form";
			}
			
			
	        // 이미 멤버십을 구매한 경우 처리
			UserDto user = userService.findUser(userId);
			
			// 컨트롤러에서 상태 저장
			if (user.getMemberShip()) {
			    redirectAttributes.addFlashAttribute("membershipStatus", "alreadyHasMembership");
			    return "redirect:/index";
			}
	        
	        
			/*************** 주문아이템(멤버십) 수량 1픽스***************/
			
			//멤버십 디테일에서 상품 세션 담은거 받기
			Long productNo = (Long) session.getAttribute("productNo");
			model.addAttribute("productNo", productNo);
			
			// Product 엔티티의 정보를 저장할 변수
            Date membershipStartPeriod = null;
            int membershipPeriodOfUse = 0;
            String membershipName = null;
            String membershipImage = null;
            String membershipContent = null;
            Long membershipNo = 0L;
            int membershipPrice = 0;
            
            // 주문할 Product 정보 가져오기
            Product product = productService.getProduct(productNo);

			// Product 엔티티의 멤버십 시작일 정보 가져오기
			membershipStartPeriod = product.getStartPeriod();
			membershipPeriodOfUse = product.getPeriodOfUse();
			membershipName = product.getProductName();
			membershipImage = product.getProductImage();
			membershipContent = product.getProductContent();
			membershipNo = product.getProductNo();
			membershipPrice = product.getProductPrice();
        	
            model.addAttribute("membershipStartPeriod", membershipStartPeriod);
            model.addAttribute("membershipPeriodOfUse", membershipPeriodOfUse);
            model.addAttribute("membershipName", membershipName);
            model.addAttribute("membershipImage", membershipImage);
            model.addAttribute("membershipContent", membershipContent);
            model.addAttribute("membershipNo", membershipNo);
            model.addAttribute("membershipPrice", membershipPrice);
                
            /*************** 가격 ***************/
            //상품 가격
        	double orderPrice = membershipPrice;
        	
            //상품 가격 소수점 아래 절사
            int formattedOrderPrice = (int) orderPrice;
            model.addAttribute("formattedOrderPrice", formattedOrderPrice);
            
            /*************** 쿠폰 ***************/
            	            
            // 유저의 쿠폰정보 불러오기
            List<CouponDto> couponDtoList = couponService.couponsByUserId(userId);
            model.addAttribute("couponDtoList", couponDtoList);
            
            //쿠폰 할인 적용 메서드
            double salePrice = couponService.applyCouponDiscount(userId, formattedOrderPrice);
            
            //총 결제금액 소수점 아래 절사
            int endPrice = (int) salePrice;
            model.addAttribute("endPrice", endPrice);
           
            return "order_membership";
        	
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("주문이 존재하지 않습니다.", e.getMessage());
			return "index";
		}
	}
	
	
	@LoginCheck
	@GetMapping("/order_ticket")
	public String orderTicketPage(@RequestParam(name = "productNo") Long productNo, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		
		try {
			/*************** 유저정보 ***************/
			
			String userId = (String) session.getAttribute("sUserId");
			model.addAttribute("user_id", userId);
			
			//로그인정보없으면 로그인으로
			if (userId == null) {
				redirectAttributes.addAttribute("msg", "로그인이 필요합니다");
				return "redirect:/user_login_form";
			}
			
			/*************** 주문아이템(티켓) 수량 1 픽스***************/
			
			// Product 엔티티의 정보를 저장할 변수
			String ticketName = null;
			String ticketImage = null;
			String ticketContent = null;
			int ticketStar = 0;
			String ticketAddress = null;
			Date ticketDate = null;
			String ticketArtist = null;
			int ticketStock = 0;
			Long ticketNo = 0L;
			int ticketPrice = 0;
			
			// 주문 아이템별로 Product 정보 가져오기
			Product product = productService.getProduct(productNo);

			// Product 엔티티의 멤버십 시작일 정보 가져오기
			ticketName = product.getProductName();
			ticketImage = product.getProductImage();
			ticketContent = product.getProductContent();
			ticketStar = product.getProductStar();
			ticketAddress = product.getProductAddress();
			ticketDate = product.getProductDate();
			ticketArtist = product.getProductArtist();
			ticketStock = product.getProductStock();
			ticketNo = product.getProductNo();
			ticketPrice = product.getProductPrice();
			
			model.addAttribute("ticketName", ticketName);
			model.addAttribute("ticketImage", ticketImage);
			model.addAttribute("ticketContentt", ticketContent);
			model.addAttribute("ticketStar", ticketStar);
			model.addAttribute("ticketAddress", ticketAddress);
			model.addAttribute("ticketDate", ticketDate);
			model.addAttribute("ticketArtist", ticketArtist);
			model.addAttribute("ticketStock", ticketStock);
			model.addAttribute("ticketNo", ticketNo);
			model.addAttribute("ticketPrice", ticketPrice);

			/*************** 가격 ***************/

			double orderPrice = ticketPrice;
			//상품 가격 소수점 아래 절사
			int formattedOrderPrice = (int) orderPrice;
			model.addAttribute("formattedOrderPrice", formattedOrderPrice);

			/*************** 쿠폰 ***************/

			// 유저의 쿠폰정보 불러오기
			List<CouponDto> couponDtoList = couponService.couponsByUserId(userId);
			model.addAttribute("couponDtoList", couponDtoList);

			// 쿠폰 할인 적용 메서드
			double salePrice = couponService.applyCouponDiscount(userId, formattedOrderPrice);
			// 총 결제금액 소수점 아래 절사
			int endPrice = (int) salePrice;
			model.addAttribute("endPrice", endPrice);
			
			return "order_ticket";
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("주문이 존재하지 않습니다.", e.getMessage());
			return "index";
		}
	}
	
	
	@LoginCheck
	@GetMapping("/order_history")
	public String orderHistoryPage(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		try {

			
			String userId = (String) session.getAttribute("sUserId");
			
			if (userId == null) {
				redirectAttributes.addAttribute("msg", "로그인이 필요합니다");
				return "redirect:/user_login_form";
			}
			
			UserDto user = userService.findUser(userId);
			model.addAttribute("user", user);
			

			
			
			List<OrderDto> orderDtoList = orderService.ordersByUserId(userId);
			model.addAttribute("orderDtoList", orderDtoList);
			System.out.println("주문 내역:" + orderDtoList);
			List<OrderItemDto> orderItemDtoList = orderItemService.orderItemsByUserId(userId);
			model.addAttribute("orderItemDtoList", orderItemDtoList);

			System.out.println("orderItemDtoList:"+ orderItemDtoList);
			//원래 코드
			//orderdetail.html에 리스트명 orderDtoNewerList로 바꿈

			List<OrderDto> orderDtoNewerList = orderService.orderListByNewer(userId);


			model.addAttribute("orderDtoNewerList", orderDtoNewerList);
			System.out.println("주문 내역 최신순:" + orderDtoNewerList);
			

			return "order_history";

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("주문 내역이 없습니다.", e.getMessage());
			return  "index";
		}
	}

	
	
	
	
	
	
	
	
}
	            
			
	
	
			
			
			
			



