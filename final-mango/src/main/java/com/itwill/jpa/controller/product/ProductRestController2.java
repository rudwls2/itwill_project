package com.itwill.jpa.controller.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.dto.product.MusicDto;
import com.itwill.jpa.entity.product.Product;
import com.itwill.jpa.entity.product.ProductCategory;
import com.itwill.jpa.service.product.ProductService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/product")
public class ProductRestController2 {
	@Autowired
	private ProductService productService;

	//https://velog.io/@ansalstmd/SPRING-REST-Controller%EB%A5%BC-%ED%86%B5%ED%95%9C-%EA%B2%8C%EC%8B%9C%ED%8C%90-%EA%B8%B0%EB%8A%A5-%EA%B5%AC%ED%98%84
	
	
	//음악 오름차순, 내림차순 변경 같은 액션
	//public ResponseEntity<List<?>> musicListDesc(@PathVariable)
	
	//음악 추가 -> admin용 페이지에 들어갈 거 같은데 시간 없을거 같아 보임
	/*
	 * @PostMapping("/music/create") public ResponseEntity<Product>
	 * createMusic(@RequestBody Product product){ return
	 * ResponseEntity.status(HttpStatus.CREATED)
	 * .body(productService.insertProduct(product)); }
	 */
	
	/*
	 * @PutMapping("/update") public ResponseEntity<Product>
	 * updateProduct(@RequestBody Product updateProduct)throws Exception{ return
	 * ResponseEntity.status(HttpStatus.OK)
	 * .body(productService.updateProduct(updateProduct)); }
	 * 
	 * @DeleteMapping("/{productNo}") public ResponseEntity<Map>
	 * deleteProduct(@PathVariable(name="productNo") Long productNo) throws
	 * Exception{ productService.deleteProduct(productNo); return
	 * ResponseEntity.status(HttpStatus.OK).body(new HashMap<>()); }
	 */
	
	//product 단일 기능 셋
	
}
