package com.itwill.jpa.dao.product;

import java.util.List;

import com.itwill.jpa.dto.product.ProductDto;
import com.itwill.jpa.entity.product.Product;
import com.itwill.jpa.entity.product.Product.Goods;
import com.itwill.jpa.entity.product.Product.Music;
import com.itwill.jpa.entity.product.Product.Ticket;
import com.itwill.jpa.entity.product.ProductCategory;

public interface ProductDao {
// 제품 선택	
	Product selectProduct(Long productNo);	
	
// 제품 카테고리 선택
//	ProductCategory selectProductCategory(Long categoryId);

// 제품 등록(admin)
	Product insertProduct(Product product);
	
//	Ticket insertTicket(Ticket ticket);
//	
//	Goods insertGoods(Goods goods);
		
// 제품 수정(admin)[ENTITY]	
	Product updateProduct(Product product) throws Exception;
// 제품 수정(admin)[DTO][성공]	
	Product updateProductDto(ProductDto product) throws Exception;
// 제품 readCount[DTO]	
	Product increaseReadCountByProductDto(ProductDto productDto) throws Exception;
		
//	Goods updateGoods(Goods goods) throws Exception;
//	
//	Ticket updateTicket(Ticket ticket) throws Exception;

// 제품 삭제(admin)
	void deleteProduct(Long productNo) throws Exception;
	
	List<Product> selectList();

//제품 카테고리별 나열	
	List<Product> getProductByCategoryId(Long categoryId);
	
	List<Music> getMusicByCategoryId(Long categoryId);
	
	List<Ticket> getTicketByCategoryId(Long categoryId);
//	
	List<Goods> getGoodsByCategoryId(Long categoryId);

	
	
	
	
}
