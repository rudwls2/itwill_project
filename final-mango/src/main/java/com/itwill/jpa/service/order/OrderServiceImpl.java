package com.itwill.jpa.service.order;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dao.order.OrderDao;
import com.itwill.jpa.dao.user.UserDao;
import com.itwill.jpa.dto.order.OrderDto;
import com.itwill.jpa.dto.order.OrderItemDto;
import com.itwill.jpa.entity.cart.Cart;
import com.itwill.jpa.entity.cart.CartItem;
import com.itwill.jpa.entity.order.Delivery;
import com.itwill.jpa.entity.order.Order;
import com.itwill.jpa.entity.order.OrderItem;
import com.itwill.jpa.entity.product.Product;
import com.itwill.jpa.entity.product.ProductCategory;
import com.itwill.jpa.entity.user.User;
import com.itwill.jpa.exception.user.UserNotFoundException;
import com.itwill.jpa.repository.cart.CartRepository;
import com.itwill.jpa.repository.order.OrderRepository;
import com.itwill.jpa.repository.product.ProductRepository;
import com.itwill.jpa.repository.user.UserRepository;
import com.itwill.jpa.service.product.ProductService;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	ProductService productService;
	@Autowired
	CartRepository cartRepository;

	//주문 생성
	@Override
	public OrderDto saveOrder(OrderDto dto) {
		Long productNo = dto.getOrderItemDtos().get(0).getProductNo();
		Order order=new Order( null, dto.getOrderPrice(),null,null,dto.getOrderStatus(),new Delivery(null, null, null, null, null, null, null),null,null,null);
		User user=userRepository.findById(dto.getUserId()).get();
		List<OrderItemDto> orderItemDtos=dto.getOrderItemDtos();
		List<OrderItem> orderItems=new ArrayList<>();
		for(OrderItemDto orderItemDto:orderItemDtos) {
			Product product =productRepository.findById(productNo).get();
			orderItems.add(new OrderItem(null, orderItemDto.getOiQty(), order, product));
		}
		order.setUser(user);
		order.setOrderItems(orderItems);
		Order saveOrder = orderRepository.save(order);
		
		
		OrderDto orderDto = OrderDto.toDto(saveOrder);
		return orderDto;
	}

	@Override
	public OrderDto saveCartOrder(String userId) {
		Cart cart=cartRepository.findByUserId(userId);
		User user=userRepository.findById(userId).get();
		Order order=new Order( null, cart.getCartTotPrice(),null,null, Order.OrderStatus.결제완료,new Delivery(null, null, null, null, null, null, null),null,null,null);
		
		List<OrderItem> orderItems=new ArrayList<>();
		List<CartItem> cartItems=cart.getCartitems();
		for (CartItem cartItem : cartItems) {
			Product product =productRepository.findById(cartItem.getProduct().getProductNo()).get();
			orderItems.add(new OrderItem(null, cartItem.getCartItemQty(), order, product));
		}
		order.setUser(user);
		order.setOrderItems(orderItems);
		Order saveOrder = orderRepository.save(order);
		

		OrderDto orderDto = OrderDto.toDto(saveOrder);
		return orderDto;
	}

	//주문 정보 수정
	@Transactional
	@Override
	public OrderDto updateOrder(OrderDto dto) throws Exception{
		Order order = orderDao.updateOrder(Order.toEntity(dto));
		OrderDto orderDto = OrderDto.toDto(order);
		return orderDto;
	}

	//주문 한개 삭제하고 Dto에 삭제 주문 정보 저장
	@Override
	public OrderDto deleteOrder(Long orderId) throws Exception {
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("주문이 존재하지 않습니다."));
		orderRepository.deleteById(orderId);
		OrderDto orderDto = OrderDto.toDto(order);
		return orderDto;
	}
		
	
	//주문 전체 삭제하고 Dto에 삭제 주문 리스트 저장
	@Override
	public List<OrderDto> deleteAllOrder() throws Exception {
		List<Order> orderList = orderRepository.findAll();
		List<OrderDto> orderDtoList = new ArrayList<OrderDto>();
		for (Order order : orderList) {
			orderDtoList.add(OrderDto.toDto(order));
		}
		orderRepository.deleteAll();
		return orderDtoList;
	}

	//유저 아이디로 주문 전체 불러오기
	@Override
	public List<OrderDto> ordersByUserId(String UserId) {
		List<Order> orderList = orderDao.getOrdersByUserId(UserId);
		System.out.println(orderList.get(0).getDelivery());
		List<OrderDto> orderDtoList = new ArrayList<OrderDto>();
		for (Order order : orderList) {
			orderDtoList.add(OrderDto.toDto(order));
		}
		return orderDtoList;
	}

	//전체 주문 불러오기(관리자)
	@Override
	public List<OrderDto> orders() {
		List<Order> orderList = orderRepository.findAll();
		List<OrderDto> orderDtoList = new ArrayList<OrderDto>();
		for (Order order : orderList) {
			orderDtoList.add(OrderDto.toDto(order));
		}
		orderDtoList.sort(Comparator.comparing(OrderDto::getUserId)); // 아이디로 정렬
		return orderDtoList;
	}

	@Override
	public List<OrderDto> orderListByNewer(String userId) {
		List<Order> orderList = orderDao.orderListByNewer(userId);
		List<OrderDto> orderDtoList = new ArrayList<OrderDto>();
		for (Order order : orderList) {
			orderDtoList.add(OrderDto.toDto(order));
		}
		return orderDtoList;
	}

	@Override
	public List<OrderDto> orderListByOlder(String userId) {
		List<Order> orderList = orderRepository.orderListByOlder(userId);
		List<OrderDto> orderDtoList = new ArrayList<OrderDto>();
		for (Order order : orderList) {
			orderDtoList.add(OrderDto.toDto(order));
		}
		return orderDtoList;
	}
	
	//주문 총금액 계산 메서드
	@Override
	public double calculateTotalOrderPrice(String userId) {
		
		double orderPrice=0;
		
		 // 유저아이디로 주문 목록을 가져오는 메서드
	    List<Order> userOrders = orderDao.getOrdersByUserId(userId);
	    
	    // 사용자의 주문 목록을 반복하면서 총 주문 금액 계산
	    for (Order order : userOrders) {
	        List<OrderItem> orderItemList = order.getOrderItems();
	       
			// 주문 항목 목록을 DTO로 변환하고 가격과 수량을 곱하여 총 주문 금액 계산
			for (OrderItem orderItem : orderItemList) {
				OrderItemDto dto = OrderItemDto.toDto(orderItem);
				double itemPrice = dto.getProductPrice();
				int itemQty = dto.getOiQty();
				orderPrice += itemPrice * itemQty;
			}
	        
	    }
		return orderPrice;
	}
	
	//멤버쉽 구매 결과 받아서 확인하고 유저 DB에 MemberShip true로 저장
	@Override
	public boolean isMembershipPurchasedAndSaveMembership(String userId) {
		// 사용자 정보를 데이터베이스에서 가져옴
		User user = userDao.findUser(userId);
		
		if (user == null) {
			// 사용자가 없으면 구매 실패
			return false;
		}
		
		if (user.isMembership() == true) {
			// 이미 멤버십을 구매한 경우 구매 실패
			return false;
		}
		
		// 멤버십 결제 로직 (예: 결제가 성공하면 멤버십을 구매한 것으로 간주)
		boolean membershipPurchaseSuccess = true;
		
		if (membershipPurchaseSuccess) {
			// 멤버십 구매가 성공하면 사용자 멤버십 정보 true로 업데이트
			user.setMembership(true);
			userRepository.save(user); // 멤버십 정보를 사용자 데이터베이스에 저장
			return true;
		}
		
		return false; // 결제 실패
	}
	
	//멤버십 구매 결과 메서드
	@Override
	public boolean performMembershipPurchaseLogic(String userId) {
		// 여기에 실제 멤버십 결제 로직을 구현
		// 외부 결제 게이트웨이와 연동하여 결제 처리
		// 결제가 성공하면 true 반환, 실패하면 false 반환
		return true; // 일단 임시로 멤버십 구매 성공으로 가정
	}
	
	//유저아이디와 카테고리번호로 주문아이템 찾기
	@Override
	public List<OrderItemDto> findOrderItemsByUserIdAndProductCategoryId(String userId, Long categoryId)
			throws UserNotFoundException {

		// 1. 사용자 찾기
		Optional<User> userOptional = userRepository.findById(userId);

		if (userOptional.isPresent()) {
			// 2. 카테고리에 해당하는 제품 조회
			List<Product> productsInCategory = productService.getProductsByCategory(categoryId);

			// 3. 해당 카테고리의 제품에 해당하는 오더 아이템 조회
			List<OrderItemDto> orderItemsInCategoryDto = new ArrayList<>();

			for (Product product : productsInCategory) {
				List<OrderItem> orderItems = orderRepository.findOrderItemsByUserIdAndProductCategoryId(userId,
						product.getProductCategory().getCategoryId());

				// 각 OrderItem을 OrderItemDto로 변환하여 리스트에 추가
				for (OrderItem orderItem : orderItems) {
					orderItemsInCategoryDto.add(OrderItemDto.toDto(orderItem));
				}
			}

			return orderItemsInCategoryDto;
		} else {
			throw new UserNotFoundException("회원을 찾을 수 없습니다."); // 로그인 유저를 찾을 수 없는 경우 예외 throw
		}

	}
	
	//유저의 아이템 카테고리 정보를 가져와서 주문 총금액 계산
	@Override
	public double calculateTotalOrderPriceByCatagoryId(String userId, Long categoryId) {

	    double orderPrice = 0;

	    // 유저 아이디로 주문 목록을 가져오는 메서드
	    List<Order> userOrders = orderDao.getOrdersByUserId(userId);

	    // 사용자의 주문 목록을 반복하면서 카테고리 아이디에 해당하는 주문만 처리
	    for (Order order : userOrders) {
	        List<OrderItem> orderItemList = order.getOrderItems();

	        // 주문 항목 목록을 반복
	        for (OrderItem orderItem : orderItemList) {
	            // 주문 항목에서 제품(프로덕트)을 가져온 후, 해당 제품의 카테고리를 확인
	            Product product = orderItem.getProduct();
	            
	            // Product와 카테고리 간의 관계를 통해 카테고리를 확인
	            if (product != null) {
	                ProductCategory productCategory = product.getProductCategory();
	                // Product의 카테고리 아이디와 categoryId를 비교하여 일치하면 계산
	                if (productCategory != null && productCategory.getCategoryId() == categoryId) {
	                    OrderItemDto dto = OrderItemDto.toDto(orderItem);
	                    double itemPrice = dto.getProductPrice();
	                    int itemQty = dto.getOiQty();
	                    orderPrice += itemPrice * itemQty;
	                }
	            }
	        }
	    }
	    return orderPrice;
	}







}
