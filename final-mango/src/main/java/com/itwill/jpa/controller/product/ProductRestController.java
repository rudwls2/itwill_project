package com.itwill.jpa.controller.product;


import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.dto.board.BoardReplyDto;
import com.itwill.jpa.dto.cart.CartItemDto;
import com.itwill.jpa.dto.product.ProductDto;
import com.itwill.jpa.dto.product.ProductReplyDto;
import com.itwill.jpa.entity.board.BoardReply;
import com.itwill.jpa.entity.product.ProductReply;

import com.itwill.jpa.service.product.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class ProductRestController {
//프로덕트(음악,굿즈,티켓) 생성, 제거, 수정, 

	@Autowired
	private ProductService productService;
// 프로덕트 생성	
	@Operation(summary = "프로덕트 생성")
	@PostMapping("/create")
	public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(productService.insertProductDto(productDto));
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}
// 프로덕트 수정
	@Operation(summary = "프로덕트 수정")
	@PutMapping("/update")
	public ResponseEntity<?> updateProduct(@RequestBody ProductDto productDto) throws Exception {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(productService.updateProductDto(productDto));
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}
	// 프로덕트 삭제	
	@Operation(summary = "프로덕트 삭제")
	@DeleteMapping("/delete/{productNo}")
	public ResponseEntity<?> deleteProduct(@PathVariable(value = "productNo") Long productNo) {
		try {	
			productService.deleteProductDto(productNo);
			return ResponseEntity.status(HttpStatus.OK).body("productNo" + productNo + "가 삭제되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}						
		}
	
	@Operation(summary = "댓글등록")
	@PostMapping("product_reply_create/{productNo}")
	public ResponseEntity<ProductReplyDto> createProductReply(@PathVariable(name="productNo") Long productNo, @RequestBody ProductReplyDto productReplyDto) {
	    try {
	    	ProductReply productReply =ProductReply.toEntity(productReplyDto);
	    	ProductReply createReply = productService.ReplyInsert(productReply);
	    	ProductReplyDto createReplyDto = ProductReplyDto.toDto(createReply);
	    	return new ResponseEntity<>(createReplyDto, HttpStatus.CREATED);
	    }catch (Exception e) { 
	    	e.printStackTrace();
	    	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
		
	}
	
    @Operation(summary = "수량 업데이트")
    @PostMapping("/product_goods_detail/qty")
    public ResponseEntity<ProductDto> productQty(@RequestBody ProductDto dto) {
        try {
            ProductDto updatedQty = productService.goodsQty(dto.getProductNo(), dto.getProductQty());
            return new ResponseEntity<>(updatedQty, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
