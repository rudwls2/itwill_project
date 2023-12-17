package com.itwill.jpa.dto.product;


import java.sql.Date;
import java.util.List;

import com.itwill.jpa.entity.product.Product;
import com.itwill.jpa.entity.product.Product.Goods;
import com.itwill.jpa.entity.product.Product.Membership;
import com.itwill.jpa.entity.product.Product.Music;
import com.itwill.jpa.entity.product.Product.Ticket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MembershipDto {
	
	private String productCategory; // 프로덕트 카테고리
	 	
	private String productName;  // 프로덕트 이름
	
	private int productPrice; // 프로덕트 가격
	
	private String productContent; // 프로덕트(음악,굿즈,콘서트) 설명
	
	private Date startPeriod; // 멤버십 시작날짜
	
	private int periodOfUse; // 멤버십 사용기간
	
	private String productImage; // 프로덕트(음악,굿즈,콘서트,멤버십) 이미지
	
	private Long productCategoryId; // 프로덕트 카테고리
	
    private String userId;	
	
//	private List<Product> products;
//	private List<Music> musics;
//	private List<Goods> goodss;
//	private List<Ticket> tickets;
//	private List<Membership> memberships;
//	
//	
//public ProductMembershipDto(Product product) {
//	this.productName = product.getProductName();
//	this.productPrice = product.getProductPrice();
//	this.productContent = product.getProductContent();
//	this.productImage = product.getProductImage();
//	this.startPeriod = product.getStartPeriod();
//	this.periodOfUse = product.getPeriodOfUse();
//
//	}

public static MembershipDto toDto(Product entity) {
	return MembershipDto.builder()
			.productName(entity.getProductName())
			.productPrice(entity.getProductPrice())
			.productContent(entity.getProductContent())
			.productImage(entity.getProductImage())
			.startPeriod(entity.getStartPeriod())
			.periodOfUse(entity.getPeriodOfUse())
			.productCategoryId(entity.getProductCategory().getCategoryId())
//          .userId(entity.getUser().getUserId())
			.build();
}


}

