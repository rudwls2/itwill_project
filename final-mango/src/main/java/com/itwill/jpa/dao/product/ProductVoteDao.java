package com.itwill.jpa.dao.product;

import java.util.List;

import com.itwill.jpa.dto.product.ProductVoteDto;
import com.itwill.jpa.entity.product.Product;

public interface ProductVoteDao {

	List<Product> findTop20ByTotalScore();

	List<ProductVoteDto> findProductsByVoteIsNotNullOrderByVoteTotDesc();
	
}
