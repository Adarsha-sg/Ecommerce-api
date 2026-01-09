
package com.jsp.ecom.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.ecom.entity.Merchant;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {

}
