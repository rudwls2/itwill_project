package com.itwill.jpa.dao.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwill.jpa.dto.product.ProductDto;
import com.itwill.jpa.entity.product.Product;
import com.itwill.jpa.entity.product.Product.Goods;
import com.itwill.jpa.entity.product.Product.Music;
import com.itwill.jpa.entity.product.Product.Ticket;
import com.itwill.jpa.entity.product.ProductCategory;
import com.itwill.jpa.repository.product.ProductCategoryRepository;
import com.itwill.jpa.repository.product.ProductRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;

@Repository
public class ProductDaoImpl implements ProductDao{
    private final EntityManager entityManager;

    @Autowired
    public ProductDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
	@Autowired
	ProductRepository productRepository;
	@Autowired
	ProductCategoryRepository productCategoryRepository;
	
	
	// 제품 조회
	@Override
	public Product selectProduct(Long productNo) {
		Product selectProduct = productRepository.findById(productNo).get();
		return selectProduct;
	}

	// 제품 나열
	@Override
	public List<Product> selectList() {
		return productRepository.findAll();
	}	
	// 제품 등록
	@Override
	public Product insertProduct(Product product) {
		Product insertProduct = productRepository.save(product);
		return insertProduct;
	}

	public Product updateProduct(Product product) throws Exception {
		return null;
	}
	// 제품 업데이트[DTO][성공]
	@Override
	public Product updateProductDto(ProductDto productDto) throws Exception {
		Optional<Product> findProductOptional =
				productRepository.findById(productDto.getProductNo());
		Product updateProduct=null;
		if(findProductOptional.isPresent()) {
			Product findProduct = findProductOptional.get();
			findProduct.setProductName(productDto.getProductName());
			findProduct.setProductPrice(productDto.getProductPrice());
			findProduct.setProductDate(productDto.getProductDate());
			findProduct.setReadCount(productDto.getReadCount());
			findProduct.setProductStock(productDto.getProductStock());
			findProduct.setProductImage(productDto.getProductImage());
			findProduct.setProductMovie(productDto.getProductMovie());
			findProduct.setProductArtist(productDto.getProductArtist());
			findProduct.setProductAddress(productDto.getProductAddress());
			findProduct.setStartPeriod(productDto.getStartPeriod());
			findProduct.setPeriodOfUse(productDto.getPeriodOfUse());
			updateProduct=productRepository.save(findProduct);
		}else {
			throw new Exception("존재하지 않는 제품입니다.");
		}
		return updateProduct;
	}
	
	@Override
    public Product increaseReadCountByProductDto(ProductDto productDto) throws Exception {
		Optional<Product> findProductOptional =
					productRepository.findById(productDto.getProductNo());
		Product updateProduct=null;
        if (findProductOptional.isPresent()) {
			Product findProduct = findProductOptional.get();
			findProduct.setReadCount(productDto.getReadCount() + 1);
        } else {
			throw new Exception("존재하지 않는 제품입니다.");
        }
        return updateProduct;
    }

	// 제품 삭제
	@Override
	public void deleteProduct(Long productNo) throws Exception {
		Optional<Product> selectProductOptional= productRepository.findById(productNo);
		if(selectProductOptional.isEmpty()) {
			throw new Exception("존재하지 않는 제품입니다.");
		}
		productRepository.delete(selectProductOptional.get());
	}
	

	
	//제품 카테고리별 나열		
	@Override
	public List<Product> getProductByCategoryId(Long categoryId) {
		Optional<ProductCategory> categoryOptional = productCategoryRepository.findById(categoryId);
		if(categoryOptional.isPresent()) {
			ProductCategory productCategory = categoryOptional.get();
			return productRepository.findByProductCategory(productCategory);
		}else {
			return new ArrayList<>();  // 카테고리를 찾지 못한 경우 빈 목록을 반환
		}
	}
	@Override
	public List<Music> getMusicByCategoryId(Long categoryId) {
		Optional<ProductCategory> categoryOptional = productCategoryRepository.findById(categoryId);
		if(categoryOptional.isPresent()) {
			ProductCategory productCategory = categoryOptional.get();
			return productRepository.findMusicByProductCategory(productCategory);
		}else {
			return new ArrayList<>();  // 카테고리를 찾지 못한 경우 빈 목록을 반환
		}
	}
	@Override
	public List<Goods> getGoodsByCategoryId(Long categoryId) {
		Optional<ProductCategory> categoryOptional = productCategoryRepository.findById(categoryId);
		if(categoryOptional.isPresent()) {
			ProductCategory productCategory = categoryOptional.get();
			return productRepository.findGoodsByProductCategory(productCategory);
		}else {
			return new ArrayList<>();  // 카테고리를 찾지 못한 경우 빈 목록을 반환
	}
}
	@Override
	public List<Ticket> getTicketByCategoryId(Long categoryId) {
		Optional<ProductCategory> categoryOptional = productCategoryRepository.findById(categoryId);
		if(categoryOptional.isPresent()) {
			ProductCategory productCategory = categoryOptional.get();
			return productRepository.findTicketByProductCategory(productCategory);
		}else {
			return new ArrayList<>();  // 카테고리를 찾지 못한 경우 빈 목록을 반환
	}
	}






}

