
package com.jsp.ecom.dto;

import java.util.List;

import com.jsp.ecom.entity.Item;

import lombok.Data;

@Data
public class OrderDto {
	private String address;
	private Double amount;
	private List<Item> items;
	private String paymentId;
	private String orderId;
}
