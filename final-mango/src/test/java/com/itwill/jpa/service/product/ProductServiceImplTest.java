package com.itwill.jpa.service.product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.jpa.TeamProjectMangoApplicationTest;
import com.itwill.jpa.dao.product.ProductDao;
import com.itwill.jpa.dao.product.ProductVoteDaoImpl;
import com.itwill.jpa.dto.product.GoodsDto;
import com.itwill.jpa.dto.product.MusicDto;
import com.itwill.jpa.dto.product.ProductCategoryDto;
import com.itwill.jpa.dto.product.ProductDto;
import com.itwill.jpa.dto.product.TicketDto;
import com.itwill.jpa.entity.board.Board;
import com.itwill.jpa.entity.product.Product;
import com.itwill.jpa.entity.product.ProductCategory;
import com.itwill.jpa.exception.product.NotEnoughProductStockException;
import com.itwill.jpa.entity.product.Product.Goods;
import com.itwill.jpa.entity.product.Product.Music;
import com.itwill.jpa.repository.product.ProductCategoryRepository;
import com.itwill.jpa.repository.product.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@SpringBootTest
class ProductServiceImplTest extends TeamProjectMangoApplicationTest {
	
	@Autowired
	ProductServiceImpl productServiceImpl;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProductDao productDao;
	
	@Autowired
	ProductCategoryRepository productCategoryRepository;
	
	// productNo 찾기[성공]
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	public void testFindByProductNo() {
		Long productNo = 1L; // 제품 번호 지정
		Optional<Product> productOptional = productServiceImpl.findByProductNo(productNo); // 제품 조회
		System.out.println("찾은 번호" + productOptional);

	}
	// productNo 찾기-DTO로 받기[성공]	
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
    public void testFindProductDtoByProductNo() {
        // 가상의 Product 엔티티 생성
        Long productNo = 1L;

        // 테스트 실행
        Optional<ProductDto> productDtoOptional = productServiceImpl.findProductDtoByProductNo(productNo);
        System.out.println("찾은 번호" + productDtoOptional);
    }
    
	// 카테고리별 구분[성공]
//	@Test
//	@Transactional
//	@Rollback(false)
//	@Disabled
//	public void testFindByProductCategory() {
//		Long categoryId = 1L;
//		ProductCategory category = new ProductCategory();
//		category.setCategoryId(1L);
//
//		List<Product> products = productServiceImpl.findByProductCategory(category);
//
//		for (Product product : products) {
//			System.out.println("상품명: " + product.getProductName());
//			System.out.println("카테고리: " + product.getProductCategory().getProductCategoryName());
//			System.out.println(
//					"Product Name : " + product.getProductName() + "///Read Count : " + product.getReadCount());
//		}
//		System.out.println("music 모음 >>>>>" + products);
//
//	}
	// 카테고리별 구분-DTO로 받기[성공]
//	@Test
//	@Transactional
//	@Rollback(false)
//	@Disabled
//	void testFindProductByCategoryId() {
//		List<ProductDto> productDtoList = productServiceImpl.findByProductCategoryId(1L);	
//		System.out.println("프로덕트리스트" + productDtoList);
//	}
	// 카테고리별 구분-DTO로 받기[성공]
	// 굿즈 categoryId의 값만 가져올 수 잇음 (categoruId 다르면 null값 받아옴)
	// 출력되는 값 FindProductByCategoryId와 차이 없으면 삭제 예정
	
/*	
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void testFindGoodsByCategoryId() {
		List<TicketDto> ticketDtoList = productServiceImpl.findTicketByCategoryId(2L);
		System.out.println("굿즈리스트" + ticketDtoList);
	}		
*/	
	// InsertProduct는 dType이 product로 되기때문에 사용 x
	// music 추가[성공]
//	@Test
//	@Transactional
//	@Rollback(false)
//	@Disabled
//	public void testInsertMusic() {
//		// 새로운 Product 객체를 생성
//		Music music = new Music();
//		music.setCategoryId(1L);
//		music.getProductCategory();
//		music.setProductName("새로운 제품");
//		music.setProductPrice(10000);
//		// 제품 추가
//		Music insertedMusic = productServiceImpl.insertMusic(music);
//	}
	
	// goods 추가 - DTO로 받기[성공]
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	public void testProductDto() {
	    // GoodsDto를 생성하고 categoryId 설정
		Product product = new Product();
		product.setCategoryId(2L);
	    ProductDto productDto = ProductDto.toDto(product);
	    productDto.setProductName("테스트 상품");
	    productDto.setProductPrice(7777);
	    productDto.setProductStock(100);
//	    goodsDto.setProductCategoryId(productCategory);
	    // insertGoodsDto 메서드 호출
	    ProductDto savedProductDto = productServiceImpl.insertProductDto(productDto);
	    System.out.println(savedProductDto);
		System.out.println(productDto.getProductName());
		System.out.println(productDto.getProductPrice());
//		System.out.println(goodsDto.getProductCategory());
		
	}
	
	/*
	// product 삭제[성공]
	@Test
	@Transactional
	@Rollback(false)	 
	@Disabled
	public void testDeleteProduct() throws Exception {
		// 삭제하려는 제품번호 지정
		Long productNo = 1L;
		// 제품 삭제
		productServiceImpl.deleteProduct(productNo);
	}
*/
	// product 삭제2[성공]	
//	  @Test	  
//	  @Transactional
//	  @Rollback(false)	  
//	  @Disabled
//	  public void testDeleteProduct2() { 
//		  // 삭제하려는 제품의 번호 지정
//		  Long productNo = 2L;	  
//		  // 제품 삭제
//		  productServiceImpl.deleteProduct2(productNo);	  
//	  }
	
	// product 삭제 - DTO로 받기[성공]
		@Test
		@Transactional
		@Rollback(false)
		@Disabled	 
		void testDeledtProductDto() throws Exception {
			// productNo 값을 지정
			productServiceImpl.deleteProductDto(169L);
			productServiceImpl.deleteProductDto(170L);
			productServiceImpl.deleteProductDto(171L);
			productServiceImpl.deleteProductDto(172L);
			productServiceImpl.deleteProductDto(174L);
			productServiceImpl.deleteProductDto(175L);
			productServiceImpl.deleteProductDto(176L);
			productServiceImpl.deleteProductDto(178L);
			productServiceImpl.deleteProductDto(179L);
		}

	  
	// product 수정[성공]
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	public void testUpdateProduct() {
		Long productNo = 6L;
		Product product = productServiceImpl.getProduct(productNo);
		// 수정
		product.setProductName("수정 테스트완료");
		// updateProduct 메서드 호출
		Product updatedProduct = productServiceImpl.updateProduct(product);
	}
	
	
	// product 수정 - DTO로 받기
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	public void testUpdateProductDto() throws Exception {
		
		ProductDto productDto = new ProductDto(6L, "sadasda", 123, null, null, 0, null, 0, null, null, null, null, 0, null, null, null,0);
		productDto.setProductNo(6L);
		productDto.setProductName("수정완료3");
		productDto.setProductArtist("가수수정2");
		productDto.setReadCount(3L);
		
		ProductDto updatedProductDto = productServiceImpl.updateProductDto(productDto);
		System.out.println(updatedProductDto);
		
	}
		
//		Long productNo = 6L;
//		Product product = productServiceImpl.getProduct(productNo);
		
		
	// 제목키워드로 검색[성공]
//	@Test
//	@Transactional
//	@Rollback(false)
//	@Disabled
//	void searchProductsByKeywordTest() {
//		String keyword = "굿즈 제품";
//		List<Product> products = productServiceImpl.searchProductsByKeyword(keyword);
//		System.out.println("검색결과>>>" + products);
//	}

	// product 조회수별 내림차순 정렬[성공]
//	@Test
//	@Transactional
//	@Rollback(false)
//	@Disabled
//	public void testGetProductOrderByReadCountDesc() {
//		List<Product> products = productServiceImpl.getProductOrderByReadCountDesc();
//		for (Product product : products) {
//			System.out.println(
//					"Product Name : " + product.getProductName() + "///Read Count : " + product.getReadCount());
//		}
//	}
	// product 조회수별 오름차순 정렬[DTO][성공]
//	@Test
//	@Transactional
//	@Rollback(false)	
//	@Disabled
//	public void testProductByReadCountAscDto() {
//		// Arrange (준비)
//		Long categoryId = 1L; // 적절한 카테고리 ID 설정[1. music, 2. goods, 3. ticket, 4. membership]
//		// Act (실행)
//		List<ProductDto> productList = productServiceImpl.productByReadCountAscDto(categoryId);
//		for (ProductDto productDto : productList) {
//			System.out.println(
//					"Product Name : " + productDto.getProductName() + "///Read Count : " + productDto.getReadCount());
//		}
//		
//	}
	// product 조회수별 내림차순 정렬[DTO][성공]
//	@Test
//	@Transactional
//	@Rollback(false)	
//	@Disabled
//	public void testProductByReadCountDescDto() {
//		// Arrange (준비)
//		Long categoryId = 1L; // 적절한 카테고리 ID 설정[1. music, 2. goods, 3. ticket, 4. membership]
//		// Act (실행)
//		List<ProductDto> productList = productServiceImpl.productByReadCountDescDto(categoryId);
//		for (ProductDto productDto : productList) {
//			System.out.println(
//					"Product Name : " + productDto.getProductName() + "///Read Count : " + productDto.getReadCount());
//		}
//		
//	}
//	@Test
//	@Transactional
//	@Rollback(false)
////	@Disabled
//	    public void testProductByReadCountDescDto() throws Exception {
//	    ProductDto productDto = new ProductDto();
//        // 테스트할 메서드 호출
//        List<ProductDto> productList = productServiceImpl.productByReadCountDescDto(productDto);
//        for(ProductDto product : productList) {
//        	System.out.println(
//        			"Product Name : " + productDto.getProductName() + "///Read Count : " + productDto.getReadCount());
//        }
//	}
	// product 조회수별 오름차순 정렬[성공]
//	@Test
//	@Transactional
//	@Rollback(false)
//	@Disabled
//	public void testGetProductOrderByReadCountAsc() {
//		List<Product> products = productServiceImpl.getProductOrderByReadCountAsc();
//		for (Product product : products) {
//			System.out.println(
//					"Product Name : " + product.getProductName() + "///Read Count : " + product.getReadCount());
//		}
//	}

	// product 오래 등록된 순으로 정렬[DTO][성공]
	@Test
	@Transactional
	@Rollback(false)	
	@Disabled
	public void testProductListByNewer() {
		// Arrange (준비)
		Long categoryId = 3L; // 적절한 카테고리 ID 설정[1. music, 2. goods, 3. ticket, 4. membership]
		// Act (실행)
		List<ProductDto> productList = productServiceImpl.productListByNewer(categoryId);
		for (ProductDto productDto : productList) {
			System.out.println(
					"Product Name : " + productDto.getProductName() + "///Product Date : " + productDto.getProductDate());
		}
		
	}	
	// product 오래 등록된 순으로 정렬[DTO][성공]
	@Test
	@Transactional
	@Rollback(false)	
	@Disabled
	public void testProductListByOlder() {
		// Arrange (준비)
		Long categoryId = 3L; // 카테고리 ID 설정[1. music, 2. goods, 3. ticket, 4. membership]
		// Act (실행)
		List<ProductDto> productList = productServiceImpl.productListByOlder(categoryId);
		for (ProductDto productDto : productList) {
			System.out.println(
					"Product Name : " + productDto.getProductName() + "///Product Date : " + productDto.getProductDate());
		}
		
	}	
	// product 조회수 올리기[성공]
//	@Test
//	@Transactional
//	@Rollback(false)
//	@Disabled
//	public void testIncreaseReadCount() {
//		Optional<Product> productOptional = productRepository.findById(5L);
//		if (productOptional.isPresent()) {
//			// 엔티티가 존재할 경우
//			Product product = productOptional.get();
//			// readCount 증가
//			product.setReadCount(product.getReadCount() + 1);
//			// 변경사항 저장
//			productRepository.save(product);
//		} else {
//			// 엔티티 못찾았을 경우의 예외처리
//		}
//	}
	// product 조회수 올리기[DTO][실패]	
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	public void testIncreaseProductReadCountDto() throws Exception {
        // Given: 테스트용 Product 엔티티를 생성하고 데이터베이스에 저장
        Product product = new Product();
        product.setProductNo(2L);
        productRepository.save(product);
        // When: increaseProductReadCountDto 메서드를 호출
        ProductDto productDto = new ProductDto();
        productDto.setProductNo(2L);
        ProductDto updatedProductDto = productServiceImpl.increaseProductReadCountDto(productDto);

    }
//    @Test
//	@Transactional
//	@Rollback(false)
////	@Disabled
//    public void testOutOfStockMsgDto_StockAvailable() {
//        Long productNo = 1L;
//        Product product = new Product();
//        product.setProductNo(productNo);
//        product.setProductStock(10);
//
//        when(productRepository.findById(productNo)).thenReturn(Optional.of(product));
//
//        ProductDto productDto = productServiceImpl.outOfStockMsgDto(productNo);
//
//        assertEquals(productNo, productDto.getProductNo());
//        assertEquals(10, productDto.getProductStock());
//    }
//
//    @Test
//	@Transactional
//	@Rollback(false)
//	@Disabled
//    public void testOutOfStockMsgDto_StockEmpty() {
//        Long productNo = 2L;
//        Product product = new Product();
//        product.setProductNo(productNo);
//        product.setProductStock(0);
//
//        when(productRepository.findById(productNo)).thenReturn(Optional.of(product));
//
//        productServiceImpl.outOfStockMsgDto(productNo);
//    }
//
//    @Test
//	@Transactional
//	@Rollback(false)
//	@Disabled
//    public void testOutOfStockMsgDto_ProductNotFound() {
//        Long productNo = 3L;
//
//        when(productRepository.findById(productNo)).thenReturn(Optional.empty());
//
//        ProductDto productDto = productServiceImpl.outOfStockMsgDto(productNo);
//
//        assertEquals(null, productDto);
//    }
	
	/******************* 진행중 
	 * @throws Exception *******************/
//	// productName 찾기
//    @Test
//    @Transactional
// //   @Rollback(false)
//    @Disabled
//    public void testFindByProductName() {
//        // 제품 이름 지정
//        String productName = "음악 제품 1";
//        // 제품을 조회합니다.
//        Product product = productServiceImpl.findByProductName(productName);
//
//    }
//
//	// productArtist 찾기
//    @Test
//    @Transactional
//    @Rollback(false)
//    @Disabled
//    public void testFindByProductArtist() {
//        // 아티스트 지정
//        String productArtist = "아티스트 1";
//
//        // 아티스트 조회
//        Product product = productServiceImpl.findByProductAtrist(productArtist);

//@Test
//@Transactional
//@Rollback(false)
//@Disabled
//public void testFindByCategoryId() {
//	Long categoryId = 1L;
//	
//	List<Product> products = productServiceImpl.findByCategoryId(categoryId);
//	
//	for (Product product : products) {
//		System.out.println("product ID: " + product.getProductNo());
//		System.out.println("product 이름: " + product.getProductName());
//		System.out.println("카테고리 ID: " + product.getProductCategory().getCategoryId());
//	}
//}
	
@Test
@Transactional
@Disabled
@Rollback(false)
public void goGoodsQtyData() throws Exception {
	Long productNo = 7L;
	int productQty = 10;
	productServiceImpl.goodsQty(productNo, productQty);
	
}
	

}