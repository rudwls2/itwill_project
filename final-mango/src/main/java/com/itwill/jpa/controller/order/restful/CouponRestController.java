
package com.itwill.jpa.controller.order.restful;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.dto.order.CouponDto;
import com.itwill.jpa.service.order.CouponService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/coupon")
@RequiredArgsConstructor//생성자를 자동으로 생성하여 멤버필드(DI)주입 해주는 어노테이션
public class CouponRestController {

	private final CouponService couponService;

	/* Restful Coupon */
	
	// 쿠폰생성
	@Operation(summary = "쿠폰생성[성공]")
	@PostMapping("/create")
	public ResponseEntity<?> createCoupon(@RequestBody CouponDto couponDto) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(couponService.saveCoupon(couponDto));
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
		
	}
	
	// 쿠폰 정보 수정(관리자권한)
	@Operation(summary = "쿠폰수정[성공]")
	@PutMapping("/update")
	public ResponseEntity<?> updateCoupon(@RequestBody CouponDto couponDto) throws Exception {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(couponService.updateCoupon(couponDto));
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}
	
	// 쿠폰 1개 삭제
	@Operation(summary = "쿠폰한개삭제[성공]")
	@DeleteMapping("/delete/{couponId}")
	public ResponseEntity<?> deleteCoupon(@PathVariable(value = "couponId") Long couponId) {
		try {
			couponService.deleteCoupon(couponId);
			return ResponseEntity.status(HttpStatus.OK).body("쿠폰번호" + couponId + "번이 삭제 되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}

	// 쿠폰 전체 삭제
	@Operation(summary = "쿠폰전체삭제[성공]")
	@DeleteMapping("/delete/all")
	public ResponseEntity<?> deleteAllCoupons() {
		try {
			couponService.deleteAllCoupons();
			return ResponseEntity.status(HttpStatus.OK).body("전체 쿠폰이 삭제 되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}
	
	// 유저의 쿠폰 전체 불러오기
	@Operation(summary = "로그인한 유저 쿠폰 불러오기[성공]")
	@GetMapping("/{userId}")
	public ResponseEntity<?> getCouponsByUserId(@PathVariable(value = "userId") String userId) {
		try {
			List<CouponDto> userCoupons = couponService.couponsByUserId(userId);
			return ResponseEntity.status(HttpStatus.OK).body(userCoupons);
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}

	}
	
	// 주문내역에서 해당 주문에 사용된 쿠폰 불러오기
	@Operation(summary = "주문내역에서 해당 주문에 사용된 쿠폰 불러오기([성공]")
	@GetMapping("/all/{orderId}") 
	public ResponseEntity<?> getCouponByOrderId(@PathVariable(value = "orderId") Long orderId) {
		try {
			CouponDto coupon = couponService.findCouponByOrderId(orderId);
			return ResponseEntity.status(HttpStatus.OK).body(coupon);
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}
	
	// 쿠폰 할인 적용시키기
	@Operation(summary = "쿠폰 할인 적용시키기([성공]")
	@PutMapping("/discount/{couponId}") 
	public ResponseEntity<?> applyCouponDiscount(@PathVariable(value = "userId") String userId,@RequestParam(value = "orderPrice") double orderPrice) {
		try {
			double discountPrice = couponService.applyCouponDiscount(userId, orderPrice);
			return ResponseEntity.status(HttpStatus.OK).body(discountPrice);
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}
	
	//	쿠폰 id로 쿠폰 가져오기
	@Operation(summary = "쿠폰 id로 쿠폰 가져오기([성공]")
	@GetMapping("/coupon/{couponId}")
	public ResponseEntity<?> getCouponByCouponId(@PathVariable(value = "couponId") Long couponId) {
		try {
			CouponDto foundCoupon = couponService.findCouponByCouponId(couponId); 
			return ResponseEntity.status(HttpStatus.OK).body(foundCoupon);
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}
	
	
	
	
}
		
