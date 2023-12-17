package com.itwill.jpa.service.product;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.jpa.dao.product.ProductDao;
import com.itwill.jpa.dto.product.GoodsDto;
import com.itwill.jpa.dto.product.MusicDto;
import com.itwill.jpa.dto.product.ProductCategoryDto;
import com.itwill.jpa.dto.product.ProductDto;
import com.itwill.jpa.dto.product.TicketDto;
import com.itwill.jpa.entity.board.BoardReply;
import com.itwill.jpa.entity.product.Product;
import com.itwill.jpa.entity.product.ProductCategory;
import com.itwill.jpa.entity.product.ProductReply;
import com.itwill.jpa.entity.product.Product.Goods;
import com.itwill.jpa.entity.product.Product.Membership;
import com.itwill.jpa.entity.product.Product.Music;
import com.itwill.jpa.entity.product.Product.Ticket;



@Service
@Transactional(readOnly = true)
//@RequiredArgsConstructor
public interface ProductService{
	
//Product//	
	Product getProduct(Long productNo); 
	
	Product getProductCategory(Long categoryId);
	
//	ProductDto getProductDto(Long productNo);
	
	/******************** INSERT[ENTITY] ********************/
	// product 등록[성공]
//	public Product insertProduct(Product product);	
	
	// music 등록[성공]
	public Music insertMusic(Music music);
	
	// goods 등록[성공]
	public Goods insertGoods(Goods goods);
	
	// ticket 등록[성공]
	public Ticket insertTicket(Ticket ticket);
	
	// membership 등록[성공]
	public Membership insertMembership(Membership membership);
	
	/******************** INSERT[DTO] ********************/
	// product 등록 - DTO
	ProductDto insertProductDto(ProductDto productDto);	
	
	/******************** DELETE[ENTITY] ********************/
	// product 삭제[성공]		
	void deleteProduct(Long productNo) throws Exception;		
	// product 삭제[성공]
//	public void deleteProduct2(Long productNo);
	
	/******************** DELETE[DTO] ********************/
	// product 삭제 - DTO[성공]
	ProductDto deleteProductDto(Long productNo) throws Exception;
	/*********************************************/
	
	/******************** UPDATE[ENTITY] ********************/	
	// product 업데이트[성공]
	public Product updateProduct(Product product);
	
	/******************** UPDATE[DTO] ********************/	
	// product 업데이트 - DTO	
	ProductDto updateProductDto(ProductDto productDto) throws Exception;
	/*********************************************/
	
	/******************** INCREASE READCOUNT[ENTITY] ********************/	
	// product 조회수 올리기[성공]
	Product increaseReadCount(Product product);
	/******************** INCREASE READCOUNT[DTO] ********************/
	ProductDto increaseProductReadCountDto(ProductDto productDto) throws Exception;

	/*
	 * ProductDto increaseReadCountByProductNo(ProductDto dto) throws Exception;
	 * 
	 * ProductDto increaseReadCountByProductNo(Long productNo) throws Exception;
	 */
	/*************************************************************/
	public List<ProductReply> findByProduct_productNo(Long productNo);
	/******************** categoryId별로 전체나열[ENTITY] ********************/		
	// product categoryId별 분류
	public List<Product> findByCategoryId(Long categoryId);
	
	/******************** categoryId별로 전체나열[DTO] ********************/		
	// product categoryId별로 전체나열 - DTO	
	List<ProductDto> findByProductCategoryId(Long categoryId);
		
	// ticket categoryId별로 전체나열 - DTO	
	List<MusicDto> findMusicByCategoryId(Long categoryId);
	
	// ticket categoryId별로 전체나열 - DTO	
	List<TicketDto> findTicketByCategoryId(Long categoryId);
		
	// goods categoryId별로 전체나열 - DTO	
	List<GoodsDto> findGoodsByCategoryId(Long categoryId);
	/*********************************************/	
	
	// product category별 분류
//	List<Product> findByProductCategory(ProductCategory categoryId);
	List<Product> productList(); 
	
	List<Product> getProductsByCategory(Long categoryId);
	
//	List<ProductDto> getProductsByCategorysDtos(Long categoryId);
	
	//전체 product 출력[DTO]	
	List<ProductDto> productDtoList();
	
    // 카테고리 ID를 사용하여 상품 조회 메서드
//	List<ProductDto> getProductsByCategoryDto(Long categoryId);
	
//ProductCategory//		
    void saveProductCategory(ProductCategoryDto productCategoryDto);
	

	// 좋아요 누르기 기능[성공]
	Long checkLikeService(Long productNo);
	/******************** 품절 안내 기능[ENTITY] ********************/
	// 품절 안내 기능[성공]
	Product outOfStockMsg(Long productNo);
	
	/******************** 품절 안내 기능[DTO] ********************/
	ProductDto outOfStockMsgDto(Long productNo);
	
	/******************** productNo 찾기[ENTITY] ********************/	
	// productNo 찾기[성공]
	public Optional<Product> findByProductNo(Long productNo);
	/******************** productNo 찾기[DTO] ********************/	
	// productNo 찾기 - DTO
	Optional<ProductDto> findProductDtoByProductNo(Long productNo);


	
	/******************** 오름차순[ENTITY] ********************/		
	// product 조회수별 오름차순 정렬 [성공]
	public List<Product> getProductOrderByReadCountAsc();
	
	/******************** 오름차순[DTO] ********************/		
	// product 조회수별 내림차순 정렬 - DTO	[성공]
	List<ProductDto> productByReadCountAscDto(Long categoryId) throws Exception;
	
	//주문 최신순으로 나열하기
	List<ProductDto> productListByOlder(Long categoryId) throws Exception;
	/*********************************************/	
	/******************** 내림차순[ENTITY] ********************/		
	// product 조회수별 내림차순 정렬 [성공]
	public List<Product> getProductOrderByReadCountDesc();
	
	/******************** 내림차순[DTO] ********************/		
	// product 조회수별 내림차순 정렬 - DTO	[성공]
	List<ProductDto> productByReadCountDescDto(Long categoryId) throws Exception;
	
	//주문 오래된순으로 나열하기
		// >> older , newer 기능 반대로 구현되어있음. 추후 이름수정 필요.
	List<ProductDto> productListByNewer(Long categoryId) throws Exception;	
	/*********************************************/	
	
	/******************** 키워드로 검색[ENTITY] ********************/	
	// 키워드로 검색[성공]
	public List<Product> searchProductsByKeyword(String keyword);
	/******************** 키워드로 검색[DTO] ********************/	
	/*********************************************/
	
	/******************** 댓글달기 ********************/	
	ProductReply ReplyInsert(ProductReply productReply);


	




	/******************** UPDATE[DTO] ********************/

	
//	// productName 찾기
//	public Product findByProductName(String productName);
//	
//	// productArtist 찾기
//	public Product findByProductAtrist(String productArtist);

	/******************** INCREASE READCOUNT[DTO] ********************/

	//굿즈상품 카트에 보낼 수량
	public ProductDto goodsQty(Long productNo, int productQty) throws Exception;
}