
package com.jsp.ecom.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.ecom.entity.Merchant;
import com.jsp.ecom.entity.User;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {

	Optional<Merchant> findByUser(User user);

}
