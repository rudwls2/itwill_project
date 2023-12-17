package com.itwill.jpa.service.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Query;

import com.itwill.jpa.entity.product.Product;

public interface ProductVoteService {

	// Vote 대상 뽑기 --> 20위 뽑기
	List<Product> findbyTop20ByTotalScore();

	// voteID로 상품검색 후 상품 1개 가져오기
	Product findByVoteVoteId(Long voteId);
	
	// voteId가 null아닌 리스트 가져오기
	List<Product> findProductsByVoteIsNotNull();
	
}
