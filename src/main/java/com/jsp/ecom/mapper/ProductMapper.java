package com.jsp.ecom.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jsp.ecom.dto.ProductDto;
import com.jsp.ecom.entity.Merchant;
import com.jsp.ecom.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

	@Mapping(target = "name", expression = "java(productDto.getName())")
	@Mapping(target = "merchant", expression = "java(merchant)")
	@Mapping(target = "id", ignore = true)
	Product toProductEntity(ProductDto productDto, Merchant merchant);

	ProductDto toProductDto(Product product);

	List<ProductDto> toProductDtoList(List<Product> products);

}