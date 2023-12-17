package com.itwill.jpa.repository.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.jpa.entity.product.ProductReply;

public interface ProductReplyRepository extends JpaRepository<ProductReply, Long>{

	List<ProductReply> findByProduct_productNo(Long productNo);
}
