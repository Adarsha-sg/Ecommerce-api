package com.jsp.ecom.Service;

import java.util.Map;

public interface CustomerService {

	Map<String, Object> getProducts(int page, int size, String sort, boolean desc, String name, String category,
			String lowerRange, String higherRange);

	Map<String, Object> addToCart(Long id, String name, String size);

	Map<String, Object> viewCart(String name);

	Map<String, Object> removeFromCart(Long id, String name);
	
	Map<String, Object> buyFromCart(String email,String address);

	Map<String, Object> confirmPayment(Long id, String razorpay_payment_id);

}