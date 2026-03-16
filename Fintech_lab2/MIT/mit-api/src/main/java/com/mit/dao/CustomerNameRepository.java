package com.mit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mit.entity.CustomerName;

public interface CustomerNameRepository extends JpaRepository<CustomerName, Long>  {
	
	List<CustomerName> findBycustomerIdentifier(Long Id);
	
	
	
}
