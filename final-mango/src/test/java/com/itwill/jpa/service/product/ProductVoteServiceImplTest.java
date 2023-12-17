
package com.itwill.jpa.service.product;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.jpa.TeamProjectMangoApplicationTest;
import com.itwill.jpa.dao.product.ProductVoteDaoImpl;
import com.itwill.jpa.dto.product.ProductVoteDto;
import com.itwill.jpa.entity.product.Product;
import com.itwill.jpa.entity.vote.Vote;
import com.itwill.jpa.repository.product.ProductRepository;

class ProductVoteServiceImplTest extends TeamProjectMangoApplicationTest{
/*	@Autowired
	ProductRepository productRepository;

	
	@Autowired
	ProductVoteServiceImple productVoteServiceImple;

	@Autowired
	ProductVoteDaoImpl productVoteDaoImpl;
	
	
	*//******************* Vote ***********************//*

	@Test
	@Transactional
	@Rollback(false)
	//@Disabled
	// 상품에서 음악 조회수(readCount), 음악 별점(productStar)의 합산해서 Top 20명 추출
	public void testfindTop20ByTotalScore() {

	//System.out.println(">>>>>>>>>>" + productVoteServiceImple.findbyTop20ByTotalScore());
		List<ProductVoteDto> productVote = productVoteDaoImpl.findProductsByVoteIsNotNullOrderByVoteTotDesc();
		System.out.println(productVote);	
	}

	
	

	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	// voteID로 상품검색 후 상품 리스트 가져오기	
	void testFindByVoteVoteId() throws Exception {
		//Product product = productRepository.findByVoteVoteId(1L);
		//System.out.println(">>>>>>>>>>"+product);
		System.out.println(">>>>>>>>>>"+productRepository.findByVoteVoteId(1L));
		

	}

	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	// voteID가 null이 아닌 상품 리스트 가져오기	
	void testfindProductsByVoteIdIsNotNull() throws Exception {
		//Product product = productRepository.findByVoteVoteId(1L);
		//System.out.println(">>>>>>>>>>"+product);
		System.out.println(">>>>>>>>>>"+productRepository.findProductsByVoteIsNotNull().size());
		

	}
	
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	// voteId가 null이 아닌 상품 리스트를 가져와서 voteTot 내림차순으로 정렬
	void testFindProductsByVoteIsNotNullOrderByVoteTotDesc() throws Exception {
		//Product product = productRepository.findByVoteVoteId(1L);
		//System.out.println(">>>>>>>>>>"+product);
		System.out.println(">>>>>>>>>>"+productVoteDaoImpl.findProductsByVoteIsNotNullOrderByVoteTotDesc());
		

	}

*/
}
