
package com.jsp.ecom.dao;

import org.springframework.stereotype.Repository;

import com.jsp.ecom.entity.Customer;
import com.jsp.ecom.entity.Merchant;
import com.jsp.ecom.entity.User;
import com.jsp.ecom.Repository.CustomerRepository;
import com.jsp.ecom.Repository.MerchantRepository;
import com.jsp.ecom.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserDao {

	private final UserRepository userRepository;
	private final MerchantRepository merchantRepository;
	private final CustomerRepository customerRepository;

	public boolean checkEmailAndMobieDuplicate(String email, Long mobile) {
		return userRepository.existsByEmailOrMobile(email, mobile);
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email).orElseThrow();
	}

	public void save(User user) {
		userRepository.save(user);
	}

	public void save(Merchant merchant) {
		merchantRepository.save(merchant);
	}

	public void save(Customer customer) {
		customerRepository.save(customer);
	}
}
