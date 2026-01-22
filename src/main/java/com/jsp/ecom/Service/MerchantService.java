package com.jsp.ecom.Service;

import java.util.Map;

import com.jsp.ecom.dto.ProductDto;

public interface MerchantService {

	Map<String, Object> saveProduct(ProductDto productDto, String email);

	Map<String, Object> getProducts(String email);

	Map<String, Object> deleteProduct(Long id, String email);

	Map<String, Object> updateProduct(Long id, ProductDto productDto, String email);

	Map<String, Object> addProducts(String email);

}