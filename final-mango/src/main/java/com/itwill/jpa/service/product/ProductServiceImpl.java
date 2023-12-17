package com.itwill.jpa.service.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.jpa.dao.product.ProductDao;
import com.itwill.jpa.dto.order.OrderDto;
import com.itwill.jpa.dto.order.OrderItemDto;
import com.itwill.jpa.dto.product.GoodsDto;
import com.itwill.jpa.dto.product.MusicDto;
import com.itwill.jpa.dto.product.ProductCategoryDto;
import com.itwill.jpa.dto.product.ProductDto;
import com.itwill.jpa.dto.product.TicketDto;
import com.itwill.jpa.entity.order.Delivery;
import com.itwill.jpa.entity.order.Order;
import com.itwill.jpa.entity.order.OrderItem;
import com.itwill.jpa.entity.product.Product;
import com.itwill.jpa.entity.product.Product.Goods;
import com.itwill.jpa.entity.product.Product.Membership;
import com.itwill.jpa.entity.product.Product.Music;
import com.itwill.jpa.entity.product.Product.Ticket;
import com.itwill.jpa.entity.user.User;
import com.itwill.jpa.entity.product.ProductCategory;
import com.itwill.jpa.entity.product.ProductReply;
import com.itwill.jpa.exception.product.NotEnoughProductStockException;
import com.itwill.jpa.repository.product.ProductCategoryRepository;
import com.itwill.jpa.repository.product.ProductReplyRepository;
import com.itwill.jpa.repository.product.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductCategoryRepository productCategoryRepository;
	
	@Autowired
	private ProductReplyRepository productReplyRepository;
	
	@Autowired
	ProductDao productDao;
	
	// productId값 가져오기		
	@Override
	public Product getProduct(Long productNo) {
		return productRepository.findById(productNo).get();
	}
	@Override
	public Product getProductCategory(Long categoryId) {
		return (Product) productRepository.findByProductCategoryCategoryId(categoryId);
	}
//	@Override
//	public ProductDto getProductDto(Long productNo) {
//		return productRepository.findById(productNo);
//	}
	// categoryId값 가져오기	
	@Override	
	public List<Product> getProductsByCategory(Long categoryId) {
		return productRepository.findByProductCategoryCategoryId(categoryId);
	}
	@Override
	public List<Product> productList() {
		return productRepository.findAll();
	}
	/*
	 * @Override public Product saveProduct(Product product) { return
	 * productRepository.save(product); }
	 */
	
	//전체 product 출력[DTO]
	@Override
	public List<ProductDto> productDtoList() {
		List<Product> productList = productRepository.findAll();
		List<ProductDto> productDtoList = new ArrayList<ProductDto>();
		for(Product product : productList) {
			productDtoList.add(ProductDto.toDto(product));
		}
		return productDtoList;
	}
		
	// 좋아요 누르기 기능[성공]
	@Override
	public Long checkLikeService(Long productNo) {
		Product findProduct = productRepository.findById(productNo).get();
		Long checkLike = Long.valueOf(findProduct.getProductStar());
		if(checkLike%2==1) {
			checkLike=1L;
		}else if(checkLike%2==0) {
			checkLike=0L;
		}
		return checkLike;
	}
	
	// 품절 안내 기능[성공]
	@Override
	   public Product outOfStockMsg(Long productNo) {
	      Product findProduct =productRepository.findById(productNo).get();
	      String msg="";
	      int stockCount=findProduct.getProductStock();
	      
	      if(stockCount==0) {
	         throw new NotEnoughProductStockException("품절된 상품입니다.");
	      }else {
	         msg=stockCount+"개 남았습니다.";
	      }
	      System.out.println(msg);
	      return null;
	   }
	
	// 품절 안내 기능-DTO [
	@Override
    public ProductDto outOfStockMsgDto(Long productNo) {
        Product findProduct = productRepository.findById(productNo).orElse(null);
        if (findProduct != null) {
            int stockCount = findProduct.getProductStock();
            if (stockCount == 0) {
                throw new NotEnoughProductStockException("품절된 상품입니다.");
            }
            ProductDto productDto = new ProductDto();
            productDto.setProductNo(productNo);
            productDto.setProductStock(stockCount);
            return productDto;
        }
		return null;
        }

	
/******************** productNo 찾기[ENTITY] ********************/
	// productNo 찾기[성공]	
	@Override
	public Optional<Product> findByProductNo(Long productNo) {
		return productRepository.findById(productNo);
	}	
/******************** productNo 찾기[DTO] ********************/
	// productNo 찾기[성공]	
	@Override
	public Optional<ProductDto> findProductDtoByProductNo(Long productNo) {
	    Optional<Product> productOptional = productRepository.findById(productNo);
	    if (productOptional.isPresent()) {
	        Product product = productOptional.get();
	        ProductDto productDto = ProductDto.toDto(product);
	        return Optional.of(productDto);
	    } else {
	    }
	    return Optional.empty();
	}	
/*************************************************************************/	
	@Override
	public List<ProductReply> findByProduct_productNo(Long productNo){
		return productReplyRepository.findByProduct_productNo(productNo);
	}	
/******************** categoryId별로 전체나열[ENTITY] ********************/
	
	// product 카테고리별 구분[성공]
//	@Override
//	public List<Product> findByProductCategory(ProductCategory categoryId) {
//		return productRepository.findByProductCategory(categoryId);
//	}
/*************************************************************************/	
	@Override
	public List<Product> findByCategoryId(Long categoryId) {
		return productRepository.findByProductCategoryCategoryId(categoryId);
	}
/******************** categoryId별로 전체나열[DTO] ********************/
		
	// product categoryId별로 전체나열 - DTO [성공]
	@Override
	public List<ProductDto> findByProductCategoryId(Long categoryId) {
		List<Product> productList = productDao.getProductByCategoryId(categoryId);
		List<ProductDto> productDtoList = new ArrayList<ProductDto>();
		for (Product product : productList) {
			productDtoList.add(ProductDto.toDto(product));
		}
		return productDtoList;
	}
	
	// music categoryId별로 전체나열 - DTO [성공]
	@Override
	public List<MusicDto> findMusicByCategoryId(Long categoryId) {
		List<Music> musicList = productDao.getMusicByCategoryId(categoryId);
		List<MusicDto> productDtoList = new ArrayList<MusicDto>();
		for (Music music : musicList) {
			productDtoList.add(MusicDto.toDto(music));
		}
		return productDtoList;
	}
	// goods categoryId별로 전체나열 - DTO [성공]
	@Override
	public List<GoodsDto> findGoodsByCategoryId(Long categoryId) {
		List<Goods> goodsList = productDao.getGoodsByCategoryId(categoryId);
		List<GoodsDto> goodsDtoList = new ArrayList<GoodsDto>();
		for (Goods goods : goodsList) {
			goodsDtoList.add(GoodsDto.toDto(goods));
		}
		return goodsDtoList;
	}
	// ticket categoryId별로 전체나열 - DTO [성공]
	@Override
	public List<TicketDto> findTicketByCategoryId(Long categoryId) {
		List<Ticket> ticketList = productDao.getTicketByCategoryId(categoryId);
		List<TicketDto> ticketDtoList = new ArrayList<TicketDto>();
		for (Ticket ticket : ticketList) {
			ticketDtoList.add(TicketDto.toDto(ticket));
		}
		return ticketDtoList;
	}	
/*********************************************/	
    // 카테고리 ID를 사용하여 상품 조회 - DTO
//    @Override
//    public List<ProductDto> getProductsByCategoryDto(Long categoryId) {
//        List<Product> productList = productRepository.findByProductCategoryCategoryId(categoryId);
//        List<ProductDto> productDtoList = ProductDto.toDtoList(productList);
//        return productDtoList;
//    }	
/******************** insert[ENTITY] ********************/	
	//product 추가[성공]
//	@Override
//	public Product insertProduct(Product product) {
//		return productRepository.save(product);
//	}
	
	//music 추가[성공]
	@Override
	public Music insertMusic(Music music) {
		return productRepository.save(music);
	}
	//goods 추가[성공]
	@Override
	public Goods insertGoods(Goods goods) {
		return productRepository.save(goods);
	}
	//ticket 추가[성공]
	@Override
	public Ticket insertTicket(Ticket ticket) {
		return productRepository.save(ticket);
	}
	//membership 추가[성공]
	@Override
	public Membership insertMembership(Membership membership) {
		return productRepository.save(membership);
	}

	//댓글달기	
	@Override
	public ProductReply ReplyInsert(ProductReply productReply) {
		return productReplyRepository.save(productReply);
	}
/******************** INSERT[DTO] ********************/
	// product 등록 - DTO[성공]
	@Override
	public ProductDto insertProductDto(ProductDto dto) {
		Product product = productRepository.save(Product.toEntity(dto));
		ProductDto productDto = ProductDto.toDto(product);
		return productDto;
	}

/*********************************************/	
	
/******************** DELETE[ENTITY] ********************/	
	//product 삭제[성공]	
	@Override
	public void deleteProduct(Long productNo) throws Exception {
		productRepository.deleteById(productNo);
		
	}
//	
//	//product 삭제[성공]
//	
//	  @Override
//	  public void deleteProduct2(Long productNo) { 
//		  Optional<Product> productOptional = productRepository.findById(productNo);
//	  if(productOptional.isPresent()) { 
//		  Product product = productOptional.get();
//		  productRepository.delete(product); //product 객체 있으면 꺼내서 삭제
//		  }else {	  
//	  //예외처리
//	  }
//		
//	}
/******************** DELETE[DTO] ********************/	
	  
		// product 삭제 - DTO[성공]	
	@Override
	public ProductDto deleteProductDto(Long productNo) throws Exception {
		Product product = productRepository.findById(productNo).orElseThrow(() -> new IllegalArgumentException("제품이 존재하지 않습니다."));
		productRepository.deleteById(productNo);
		ProductDto productDto = ProductDto.toDto(product);
		return productDto;
	}
		/*********************************************/
		
/******************** UPDATE[ENTITY] ********************/
	//product 수정[성공]
	@Override
	public Product updateProduct(Product product) {
		Product isProduct = productRepository.findById(product.getProductNo()).orElse(null);
		if(isProduct != null) {
			isProduct.setProductCategory(product.getProductCategory()); // product 카테고리 수정
			isProduct.setProductName(product.getProductName()); // product 이름 수정
			isProduct.setProductPrice(product.getProductPrice()); // product 가격 수정
			isProduct.setProductStar(product.getProductStar()); // product 좋아요 수정
			isProduct.setProductContent(product.getProductContent()); // product 설명 수정
			isProduct.setProductReply(product.getProductReply()); // product 댓글 수정
			isProduct.setReadCount(product.getReadCount()); // product 조회수 수정
			isProduct.setProductStock(product.getProductStock()); // product 재고 수정
			isProduct.setProductImage(product.getProductImage()); // product 이미지 수정
			isProduct.setProductMovie(product.getProductMovie()); // music 뮤직비디오 수정
			isProduct.setProductArtist(product.getProductArtist()); // product 아티스트 수정
			isProduct.setProductAddress(product.getProductAddress()); // 콘서트 장소 수정
			isProduct.setStartPeriod(product.getStartPeriod()); // membership 시작날짜 수정
			isProduct.setPeriodOfUse(product.getPeriodOfUse()); // membership 사용기간 수정
			return productRepository.save(isProduct); //수정내용 저장
		}else {
			//제품 없으면 예외처리
			throw new EntityNotFoundException("제품을 찾을 수 없습니다.");
		}
	}
/*********************************************/	
	
//	// Product를 ProductDto로 변환하는 메서드	
//	private ProductDto convertProductToProductDto(Product product) {
//		// Product 엔티티의 필드 값을 ProductDto로 복사
//		ProductDto productDto = new ProductDto();
//		productDto.setProductNo(product.getProductNo());
//		productDto.setProductName(product.getProductName());
//		// 나머지 필드 복사
//		return productDto;
//	}
/******************** UPDATE[DTO] ********************/	
	// product 수정 - DTO[성공]
	@Transactional
	@Override
	public ProductDto updateProductDto(ProductDto dto) throws Exception {
		Product updateProduct = productDao.updateProductDto(dto);
		ProductDto productDto = ProductDto.toDto(updateProduct);
		return productDto;
	}
	/*********************************************/
	// product 조회수 올리기 - DTO
	   @Override
	   public ProductDto increaseProductReadCountDto(ProductDto dto) throws Exception {
	   	Product updateProduct = productDao.increaseReadCountByProductDto(dto);
	   	ProductDto productDto = ProductDto.toDto(updateProduct);
	   	return productDto;
	   }	
/******************** INCREASE READCOUNT[ENTITY] ********************/	
	// product 조회수 올리기[성공]
	@Override
	public Product increaseReadCount(Product product) {
        // 현재 조회수를 가져와서 1 증가
        Long currentReadCount = product.getReadCount();
        Long newReadCount = currentReadCount + 1L; // 1을 Long으로 캐스팅해서 증가
        
        // 증가된 조회수를 엔티티에 설정
        product.setReadCount(newReadCount);

        // 업데이트된 엔티티를 저장하고 반환
        return productRepository.save(product);
	}
/******************** INCREASE READCOUNT[DTO] ********************/
	// goods 조회수 올리기 - DTO[실패]	
//	@Override
//	public ProductDto increaseProductReadCountDto(ProductDto productDto) throws Exception {
////		return null;
//        // 주어진 productDto에서 productNo를 가져옵니다.
//        Long productNo = productDto.getProductNo();
//        // productNo를 이용하여 해당 엔티티를 데이터베이스에서 찾습니다.
//        Optional<Product> productOptional = productRepository.findById(productNo);
//
//        if (productOptional.isPresent()) {
//            // 조회수를 1 증가시킵니다.
//        	Product product = productOptional.get();
//            product.setReadCount(product.getReadCount() + 1);
//            // 변경된 엔티티를 저장합니다.
//            productRepository.save(product);
//            // 엔티티를 DTO로 변환하여 반환합니다.
//            return ProductDto.toDto(product);
//        } else {
//            throw new Exception("해당 productNo에 해당하는 제품을 찾을 수 없습니다.");
//        }
//    }


    
    /******************** 오름차순[ENTITY]  ********************/
    // product 조회수별 오름차순 정렬[성공]
    public List<Product> getProductOrderByReadCountAsc() {
    	Sort sort = Sort.by(Sort.Direction.ASC, "readCount");
    	return productRepository.findAll(sort);
    }
	/******************** 오름차순[DTO] ********************/
	// product 조회수별 오름차순 정렬
	@Override
	public List<ProductDto> productByReadCountAscDto(Long categoryId) {
		Sort sort = Sort.by(Sort.Direction.ASC, "readCount");
		List<Product> productList = productRepository.findProductByProductCategoryCategoryIdOrderByReadCountAsc(categoryId, sort);
		List<ProductDto> productDtoList = new ArrayList<>();
		for (Product product : productList) {
			productDtoList.add(ProductDto.toDto(product));
		}
		return productDtoList;
	}
	
	// product 오래 등록된 순으로 정렬[성공]
	@Override
	public List<ProductDto> productListByNewer(Long categoryId) {
		Sort sort = Sort.by(Sort.Direction.ASC, "productDate");
		List<Product> productList = productRepository.findProductByProductCategoryCategoryIdOrderByProductDateAsc(categoryId, sort);
		List<ProductDto> productDtoList = new ArrayList<>();
		for (Product product : productList) {
			productDtoList.add(ProductDto.toDto(product));
		}
		return productDtoList;
	}
/*********************************************/		
	/******************** 내림차순[ENTITY] ********************/
	
	// product 조회수별 내림차순 정렬[성공]
	public List<Product> getProductOrderByReadCountDesc() {
		Sort sort = Sort.by(Sort.Direction.DESC, "readCount");
		return productRepository.findAll(sort);
	}
/******************** 내림차순[DTO][성공] ********************/
	// product 조회수별 내림차순 정렬
	@Override
	public List<ProductDto> productByReadCountDescDto(Long categoryId) {
		Sort sort = Sort.by(Sort.Direction.DESC, "readCount");
		List<Product> productList = productRepository.findProductByProductCategoryCategoryIdOrderByReadCountDesc(categoryId, sort);
		List<ProductDto> productDtoList = new ArrayList<>();
		for (Product product : productList) {
			productDtoList.add(ProductDto.toDto(product));
		}
		return productDtoList;
	}
	
	// product 최신 등록 순으로 정렬[성공]
		//older,newer 기능 반대-추후 이름수정 필요
	@Override
	public List<ProductDto> productListByOlder(Long categoryId) {
		Sort sort = Sort.by(Sort.Direction.DESC, "productDate");
		List<Product> productList = productRepository.findProductByProductCategoryCategoryIdOrderByProductDateDesc(categoryId, sort);
		List<ProductDto> productDtoList = new ArrayList<>();
		for (Product product : productList) {
			productDtoList.add(ProductDto.toDto(product));
		}
		return productDtoList;
	}		

/*********************************************/

	//제목키워드로 검색[성공]
	@Override
	public List<Product> searchProductsByKeyword(String keyword) {
		return productRepository.findByProductNameContaining(keyword);
	}
	/*==============================================================*/

@Override
public void saveProductCategory(ProductCategoryDto productCategoryDto) {
	// TODO Auto-generated method stub
	
}


@Override
public ProductDto goodsQty(Long productNo, int productQty) throws Exception {
	Optional<Product> findProduct = productRepository.findById(productNo);
	Product updatedProduct = null;
	if (findProduct.isPresent()) {
		Product product = findProduct.get();
		product.setProductQty(productQty);
		updatedProduct = productRepository.save(product);
		return ProductDto.toDto(updatedProduct);
	} else {
		throw new Exception("상품을 찾을 수 없습니다.");
	}
}



	
	/*============================================================*/
	
	/*********************************************/	

}