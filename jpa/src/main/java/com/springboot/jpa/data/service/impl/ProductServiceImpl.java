package com.springboot.jpa.data.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import com.springboot.jpa.data.service.ProductService;

import com.springboot.jpa.data.dao.ProductDAO;
import com.springboot.jpa.data.dto.ProductDto;
import com.springboot.jpa.data.dto.ProductResponseDto;
import com.springboot.jpa.data.entity.Product;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
	
	private final ProductDAO productDAO;

	@Override
	public ProductResponseDto getProduct(Long number) {
		//현재 서비스 레이어에는 DTO 객체와 엔티티 객체가 공존하도록 설계되어 있어 변환 필요
		Product product = productDAO.selectProduct(number);
		
		ProductResponseDto productResponseDto = new ProductResponseDto();
		productResponseDto.setNumber(product.getNumber());
		productResponseDto.setName(product.getName());
		productResponseDto.setPrice(product.getPrice());
		productResponseDto.setStock(product.getStock());
		
		return productResponseDto;
	}

	@Override
	public ProductResponseDto saveProduct(ProductDto productDto) {
		//저장 메서드 : 전달받은 DTO 객체를 통해 엔티티 객체를 생성해서 초기화한 후 DAO 객체로 전달
		Product product = new Product();
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		product.setStock(productDto.getStock());
		product.setCreatedAt(LocalDateTime.now());
		product.setUpdatedAt(LocalDateTime.now());
		
		Product savedProduct = productDAO.insertProduct(product);
		
		ProductResponseDto productResponseDto = new ProductResponseDto();
		productResponseDto.setNumber(savedProduct.getNumber());
		productResponseDto.setName(savedProduct.getName());
		productResponseDto.setPrice(savedProduct.getPrice());
		productResponseDto.setStock(savedProduct.getStock());
		
		return productResponseDto;
	}

	@Override
	public ProductResponseDto changeProductName(Long number, String name) throws Exception {
		//업데이트 메서드 : 상품정보 중 이름을 변경하는 작업을 수행
		Product changedProduct = productDAO.updateProductName(number, name);
		
		ProductResponseDto productResponseDto = new ProductResponseDto();
		productResponseDto.setNumber(changedProduct.getNumber());
		productResponseDto.setName(changedProduct.getName());
		productResponseDto.setPrice(changedProduct.getPrice());
		productResponseDto.setStock(changedProduct.getStock());
		
		return productResponseDto;
	}

	@Override
	public void deleteProduct(Long number) throws Exception {
		productDAO.deleteProduct(number);
	}
	
}