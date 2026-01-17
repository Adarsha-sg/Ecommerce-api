package com.jsp.ecom.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.ecom.entity.Merchant;
import com.jsp.ecom.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByMerchant(Merchant merchant);

	Optional<Product> findByIdAndMerchant(Long id, Merchant merchant);

}