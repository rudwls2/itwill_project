
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
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.dto.order.DeliveryDto;
import com.itwill.jpa.service.order.DeliveryService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor//생성자를 자동으로 생성하여 멤버필드(DI)주입 해주는 어노테이션
public class DeliveryRestController {


	private final DeliveryService deliveryService;

	/* Restful Delivery */
	
	@Operation(summary = "배달 생성")
	@PostMapping("/create")
	public ResponseEntity<?> createDelivery(@RequestBody DeliveryDto deliveryDto) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(deliveryService.saveDelivery(deliveryDto));
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
			
			
		}
	}
	
	
	@Operation(summary = "배달 수정")
	@PutMapping("/update")
	public ResponseEntity<?> updateDelivery(@RequestBody DeliveryDto deliveryDto) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(deliveryService.updateDelivery(deliveryDto));
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
			
			
		}
	}
	
	@Operation(summary = "배달 한개 삭제")
	@DeleteMapping("/delete/{deliveryId}")
	public ResponseEntity<?> deleteDelivery(@PathVariable(value = "deliveryId") Long deliveryId) {
		try {
			deliveryService.deleteDelivery(deliveryId);
			return ResponseEntity.status(HttpStatus.CREATED).body("배달번호"+ deliveryId+"번이 삭제되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
			
			
		}
	}
	
	@Operation(summary = "유저 아이디로 배송지 정보 불러오기")
	@GetMapping("/user/{userId}")
	public ResponseEntity<?> getDeliveriesByUserId(@PathVariable(value = "userId") String userId) {
		try {
			List<DeliveryDto> deliveriesUserId = deliveryService.findDelivery(userId);
			return ResponseEntity.status(HttpStatus.CREATED).body(deliveriesUserId);
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
			
			
		}
	}
	
	@Operation(summary = "배송지아이디로 배송지 정보 찾기")
	@GetMapping("/{deliveryId}")
	public ResponseEntity<?> getDeliveriesByDeliveryId(@PathVariable(value = "deliveryId") Long deliveryId) {
		try {
			DeliveryDto deliveriesDeliveryId = deliveryService.findByDeliveryId(deliveryId);
			return ResponseEntity.status(HttpStatus.CREATED).body(deliveriesDeliveryId);
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
			
			
		}
	}
	
	//관리자 권한
	@Operation(summary = "배송지 정보들 불러오기")
	@GetMapping("/all")
	public ResponseEntity<?> getDeliveries() {
		try {
			List<DeliveryDto> deliveries = deliveryService.deliverys();
			return ResponseEntity.status(HttpStatus.CREATED).body(deliveries);
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
			
			
		}
	}





}
