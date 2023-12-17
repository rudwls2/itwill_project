package com.itwill.jpa.entity.product;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.itwill.jpa.dto.board.BoardReplyDto;
import com.itwill.jpa.dto.product.ProductReplyDto;
import com.itwill.jpa.entity.board.Board;
import com.itwill.jpa.entity.board.BoardReply;
import com.itwill.jpa.entity.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_reply")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductReply {
	@Id
    @SequenceGenerator(name = "PRODUCT_REPLY_NO_SEQ",sequenceName = "PRODUCT_REPLY_NO_SEQ",initialValue = 1 , allocationSize =1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_REPLY_NO_SEQ")
	private Long productReplyId;
	
	private String productReplytTitle;
	private String productReplyContent;

    @CreationTimestamp
    private LocalDateTime creDateTime;

    public static ProductReply toEntity(ProductReplyDto dto) {
    	return ProductReply.builder()
    					 .productReplyId(dto.getProductReplyId())
    					 .productReplytTitle(dto.getProductReplytTitle())
    					 .productReplyContent(dto.getProductReplyContent())
    					 .user(User.builder().userId(dto.getUserId()).build())
    					 .product(Product.builder().productNo(dto.getProductNo()).build())
    					 .build();
    }
	@ManyToOne
	@JoinColumn(name = "product_no")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "user_Id")
	private User user;	
}