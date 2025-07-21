package com.springboot.test.controller;


import com.springboot.test.data.dto.ChangeProductNameDto;
import com.springboot.test.data.dto.ProductDto;
import com.springboot.test.data.dto.ProductResponseDto;
import com.springboot.test.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Product GET 메서드", description = "Product의 번호를 기반으로 DB의 Product 정보를 반환하는 메서드")
    @GetMapping()
    public ResponseEntity<ProductResponseDto> getProduct(
            @Parameter(name = "number", description = "번호", required = true) Long number) {
        ProductResponseDto productResponseDto = productService.getProduct(number);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @Operation(summary = "Product POST 메서드", description = "Product DTO를 DB에 저장하는 메서드")
    @PostMapping()
    public ResponseEntity<ProductResponseDto> createProduct(
            @RequestBody ProductDto productDto) {
        ProductResponseDto productResponseDto = productService.saveProduct(productDto);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }
    
    @Operation(summary = "Product PUT 메서드", description = "저장된 Product의 이름을 바꾸는 메서드")
    @PutMapping
    public ResponseEntity<ProductResponseDto> changeProductName(
            @RequestBody ChangeProductNameDto changeProductNameDto) throws Exception {
        ProductResponseDto productResponseDto = productService.changeProductName(
                changeProductNameDto.getNumber(),
                changeProductNameDto.getName());

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @Operation(summary = "Product DELETE 메서드", description = "Product의 번호를 기반으로 Product를 삭제하는 메서드")
    @DeleteMapping
    public ResponseEntity<String> deleteProduct(
            @Parameter(name = "number", description = "삭제할 Product의 번호", required = true) Long number) throws Exception {
        productService.deleteProduct(number);

        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되엇습니다.");
    }

}
