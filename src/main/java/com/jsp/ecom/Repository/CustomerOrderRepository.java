package com.jsp.ecom.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.ecom.entity.CustomerOrder;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

}