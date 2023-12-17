package com.itwill.jpa.dao.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwill.jpa.dto.product.ProductVoteDto;
import com.itwill.jpa.entity.product.Product;
import com.itwill.jpa.entity.vote.Vote;
import com.itwill.jpa.repository.product.ProductRepository;
import com.itwill.jpa.service.product.ProductServiceImpl;
import com.itwill.jpa.service.vote.VoteServiceImpl;

@Repository
public class ProductVoteDaoImpl implements ProductVoteDao{
	@Autowired
	ProductServiceImpl productServiceImpl;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private VoteServiceImpl voteServiceImpl;
	
	@Override
	public List<Product> findTop20ByTotalScore() {
		List<Product> productListTop20ByTotalScore 
		= productRepository.findTop20ByTotalScore();
		
		// product Top 20개 추출
	    if (productListTop20ByTotalScore.size() > 20) {
	        productListTop20ByTotalScore = productListTop20ByTotalScore.subList(0, 20);
	    }
	    // product Top 20에 의 사이즈에 맞는 voteId를 설정
	    for (int i = 0; i < productListTop20ByTotalScore.size(); i++) {
	        Product product = productListTop20ByTotalScore.get(i);
	       
	        Vote vote;
	        
			try {
				vote = voteServiceImpl.selectByVoteNo((long) (i));
				product.setVote(vote);
			} catch (Exception e) {
				e.printStackTrace();
			}
			productServiceImpl.updateProduct(product);
	    }
	    
		return productListTop20ByTotalScore;
	}
	
		// voteId가 null이 아닌 상품 리스트를 가져와서 voteTot 내림차순으로 정렬
		@Override
		public List<ProductVoteDto> findProductsByVoteIsNotNullOrderByVoteTotDesc() {
		    List<Product> products = productRepository.findProductsByVoteIsNotNull();
		    List<ProductVoteDto> productVote = ProductVoteDto.toDto(products);
		    // voteTot를 기준으로 내림차순으로 정렬
		    productVote.sort((p1, p2) -> Integer.compare(p2.getVoteTot(), p1.getVoteTot()));
		    return productVote;
		}
	
}