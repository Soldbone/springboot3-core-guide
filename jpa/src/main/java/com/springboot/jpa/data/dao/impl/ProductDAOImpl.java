package com.springboot.jpa.data.dao.impl;

import com.springboot.jpa.data.dao.ProductDAO;
import com.springboot.jpa.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.springboot.jpa.data.entity.Product;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class ProductDAOImpl implements ProductDAO {

    private final ProductRepository productRepository;

    @Autowired
    public ProductDAOImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product insertProduct(Product product) {//새로운 상품을 DB에 저장(Create)
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    @Override
    public Product selectProduct(Long number) {//상품 번호로 상품을 조회(Read)
        Product selectedProduct = productRepository.getById(number);
        return selectedProduct;
    }

    @Override
    public Product updateProductName(Long number, String name) throws Exception {//특정 상품의 이름을 수정(Update)
        Optional<Product> selectedProduct = productRepository.findById(number);

        Product updatedProduct;
        if (selectedProduct.isPresent()) {//값이 있다면
            Product product = selectedProduct.get();

            product.setName(name); //새로운 이름으로 변경
            product.setUpdatedAt(LocalDateTime.now());//수정 시간을 현재시간으로 저장

            updatedProduct = productRepository.save(product);//DB에 수정사항 반영
        } else {
            throw new Exception();//값이 없으면 error
        }

        return updatedProduct;//수정된 상품의 객체 반환
    }
    @Override
    public void deleteProduct(Long number) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);//삭제할 상품이 DB에 있는지 확인

        if (selectedProduct.isPresent()) {//있으면 delete
            Product product = selectedProduct.get();

            productRepository.delete(product);
        } else {
            throw new Exception();
        }
    }
}