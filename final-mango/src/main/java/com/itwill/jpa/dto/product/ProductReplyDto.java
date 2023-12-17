package com.itwill.jpa.dto.product;

import java.time.LocalDateTime;

import com.itwill.jpa.dto.board.BoardReplyDto;
import com.itwill.jpa.entity.board.BoardReply;
import com.itwill.jpa.entity.product.ProductReply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductReplyDto {
	
	private Long productReplyId;
    private String productReplytTitle;
    private String productReplyContent;
    private LocalDateTime creDateTime;
    private String userId;
    private Long productNo;
    
    public static ProductReplyDto toDto(ProductReply entity) {
    	return ProductReplyDto.builder()
    			 		 .productReplyId(entity.getProductReplyId())
    			 		 .productReplytTitle(entity.getProductReplytTitle())
    			 		 .productReplyContent(entity.getProductReplyContent())
    			 		 .userId(entity.getUser().getUserId())
    					 .build();
    }

}
