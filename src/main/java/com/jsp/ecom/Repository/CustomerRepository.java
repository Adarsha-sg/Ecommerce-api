
package com.jsp.ecom.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.ecom.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
