package com.itwill.jpa.dto.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itwill.jpa.entity.order.Order;
import com.itwill.jpa.entity.order.Order.OrderStatus;
import com.itwill.jpa.entity.order.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
//웹에서 고객에게 목적에 따라 데이터를 선택적으로 보여주기 위해서 엔티티 속에서 골라낸 정보를 담은 객체(Dto)
public class OrderDto {

	private Long orderId;
	
	private int orderPrice;
	
	private OrderStatus orderStatus; //주문 상태

	private String userId;
	
	private String userName;
	
	private Long deliveryId;
	
	private String deliveryName;
	
	private String deliveryPhone;
	
	private String deliveryAddress;
	
	private String deliveryCompany;
		
//	private Long couponId;

	//@Builder.Default
	private List<OrderItemDto> orderItemDtos;
	
	

	@JsonIgnore
	@CreationTimestamp
	private LocalDateTime createdAt;
	@JsonIgnore
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	
	//Dto에서 고객에게 보여주는 주문 정보들이 어떤값인지를 설정하는 생성자(초기화)
	public OrderDto(Order order) {
		
		this.orderId = order.getOrderId();
		this.orderPrice = order.getOrderPrice();
		this.orderStatus = order.getOrderStatus();
		this.userId = order.getUser().getUserId();
		this.userName = order.getUser().getUserName();
		this.deliveryId = order.getDelivery().getDeliveryId();
		this.deliveryAddress = order.getDelivery().getDeliveryAddress();
		this.deliveryCompany = order.getDelivery().getDeliveryCompany();
		this.deliveryName = order.getDelivery().getDeliveryName();
		this.deliveryPhone = order.getDelivery().getDeliveryPhone();
		this.createdAt = order.getCreatedAt();
		this.updatedAt = order.getUpdatedAt();
		
		
		
	}
				
	
	//Entity to Dto 변환
	public static OrderDto toDto(Order entity) {
		OrderDto orderDto = OrderDto.builder()
									.orderId(entity.getOrderId())
									.orderPrice(entity.getOrderPrice())
									.orderStatus(entity.getOrderStatus())
									.userId(entity.getUser().getUserId())
									.userName(entity.getUser().getUserName())
									.deliveryId(entity.getDelivery().getDeliveryId())
									.deliveryAddress(entity.getDelivery().getDeliveryAddress())
									.deliveryCompany(entity.getDelivery().getDeliveryCompany())
									.deliveryName(entity.getDelivery().getDeliveryName())
									.deliveryPhone(entity.getDelivery().getDeliveryPhone())
									.createdAt(entity.getCreatedAt())
									.updatedAt(entity.getUpdatedAt())
									.build();
		
		
		// OrderItem 엔티티 목록을 OrderItemDto 목록으로 변환하여 설정
		List<OrderItemDto> orderItemDtoList = new ArrayList<>();
		for (OrderItem orderItem : entity.getOrderItems()) {
			orderItemDtoList.add(OrderItemDto.toDto(orderItem));
		}
		orderDto.setOrderItemDtos(orderItemDtoList);

		return orderDto;
	}
	
}
	
		

	
	

					
			

			
