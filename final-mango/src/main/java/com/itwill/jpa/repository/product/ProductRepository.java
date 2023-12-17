package com.itwill.jpa.repository.product;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.itwill.jpa.dto.product.ProductDto;
import com.itwill.jpa.entity.order.Order;
import com.itwill.jpa.entity.product.Product;
import com.itwill.jpa.entity.product.Product.Goods;
import com.itwill.jpa.entity.product.Product.Music;
import com.itwill.jpa.entity.product.Product.Ticket;
import com.itwill.jpa.entity.product.ProductCategory;
import com.itwill.jpa.entity.vote.Vote;

public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findAll();
	/******************** categoryId별로 전체나열 ********************/
//	category로 찾기
	List<Product> findByProductCategoryCategoryId(Long categoryId);
	List<Product> findByProductCategory(ProductCategory category);
	List<Music> findMusicByProductCategory(ProductCategory category);	
	List<Goods> findGoodsByProductCategory(ProductCategory category);	
	List<Ticket> findTicketByProductCategory(ProductCategory category);
	
	/********************* 조회수, 등록순으로 전체나열 ********************/
	// 조회수별 오름차순 정렬
	List<Product> findByProductCategoryCategoryIdOrderByReadCount(Long categoryId, Sort sort);
	// 조회수별 내림차순 정렬
	List<Product> findProductByProductCategoryCategoryIdOrderByReadCountDesc(Long categoryId, Sort sort);
	// 조회수별 오름차순 정렬
	List<Product> findProductByProductCategoryCategoryIdOrderByReadCountAsc(Long categoryId, Sort sort);
	// 오래된 순 정렬
	List<Product> findProductByProductCategoryCategoryIdOrderByProductDateDesc(Long categoryId, Sort sort);
	// 최신순 정렬
	List<Product> findProductByProductCategoryCategoryIdOrderByProductDateAsc(Long categoryId, Sort sort);
	
	
	// 등록날자별 최신순 정렬
	List<Product> findByProductCategoryCategoryIdOrderByProductDate(Long categoryId, Sort sort);
	

	/*****************************************************************/
//	keyword가 포함된 product 찾기
	List<Product> findByProductNameContaining(String keyword);
// product readCount 증가
    @Modifying
    @Query("UPDATE Product p SET p.readCount = p.readCount + 1 WHERE p.productNo = :productNo")
    void increaseReadCount(@Param("productNo") Long productNo);
	
	/************************ Vote관련 ***************************/

	// 상품에서 음악 조회수(readCount), 음악 별점(productStar)의 합산해서 Top 20명 추출
	@Query(value = "SELECT p FROM Product p ORDER BY (COALESCE(p.readCount, 0) + COALESCE(p.productStar, 0)) DESC")
	List<Product> findTop20ByTotalScore();
	/*
	@Query("SELECT p FROM Product p WHERE p.vote.id = :voteId")
	List<Product> findProductsByVoteId(@Param("voteId") Long voteId);
	*/

	// voteID로 상품검색 후 상품 1개 가져오기
	 Product findByVoteVoteId(Long voteId);

	 
	 // voteId가 null아닌 리스트 가져오기
	 @Query("SELECT p FROM Product p JOIN p.vote v WHERE v IS NOT NULL")
	 List<Product> findProductsByVoteIsNotNull();

}