package com.itwill.jpa.service.product;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dao.product.ProductDao;
import com.itwill.jpa.dto.product.ProductVoteDto;
import com.itwill.jpa.entity.product.Product;
import com.itwill.jpa.repository.product.ProductRepository;

@Service
public class ProductVoteServiceImple implements ProductVoteService{

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	ProductDao productDao;
	
	
	// 20명 선택
	@Override
	public List<Product> findbyTop20ByTotalScore() {
		List<Product> findTop20ByTotalScore = productRepository.findTop20ByTotalScore(); 	
		return findTop20ByTotalScore;
	}


	// voteID로 상품검색 후 상품 리스트 가져오기
	@Override
	public Product findByVoteVoteId(Long voteId) {
		Product findByVoteVoteId = productRepository.findByVoteVoteId(voteId);
		return findByVoteVoteId;
	}

	// voteId가 null아닌 리스트 가져오기
	@Override
	public List<Product> findProductsByVoteIsNotNull() {
		
		List<Product> findProductsByVoteIsNotNull = productRepository.findProductsByVoteIsNotNull(); 
		return findProductsByVoteIsNotNull;
	}


	
	

}
