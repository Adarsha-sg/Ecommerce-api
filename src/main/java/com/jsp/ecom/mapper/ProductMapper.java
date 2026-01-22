package com.jsp.ecom.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jsp.ecom.dto.FakeStoreData;
import com.jsp.ecom.dto.ItemDto;
import com.jsp.ecom.dto.OrderDto;
import com.jsp.ecom.dto.ProductDto;
import com.jsp.ecom.entity.CustomerOrder;
import com.jsp.ecom.entity.Item;
import com.jsp.ecom.entity.Merchant;
import com.jsp.ecom.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

	@Mapping(target = "name", expression = "java(productDto.getName())")
	@Mapping(target = "merchant", expression = "java(merchant)")
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "approved", ignore = true)
	Product toProductEntity(ProductDto productDto, Merchant merchant);

	ProductDto toProductDto(Product product);

	List<ProductDto> toProductDtoList(List<Product> products);

	@Mapping(target = "name", expression = "java(item.getProduct().getName())")
	@Mapping(target = "brand", expression = "java(item.getProduct().getBrand())")
	@Mapping(target = "category", expression = "java(item.getProduct().getCategory())")
	@Mapping(target = "price", expression = "java(item.getProduct().getPrice())")
	@Mapping(target = "productId", expression = "java(item.getProduct().getId())")
	ItemDto toItemDto(Item item);

	List<ItemDto> toItemsDtoList(List<Item> items);

	@Mapping(target = "name", expression = "java(data.getTitle())")
	@Mapping(target = "approved", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "merchant", expression = "java(merchant)")
	@Mapping(target = "brand", expression = "java(merchant.getName())")
	@Mapping(target = "size", constant = "FREE")
	@Mapping(target = "stock", constant = "20")
	Product toProductEntity(FakeStoreData data, Merchant merchant);

	OrderDto toOrderDto(CustomerOrder order);

	List<OrderDto> toOrderDtos(List<CustomerOrder> orders);
}