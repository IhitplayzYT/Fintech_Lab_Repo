package com.mit.dao;

import org.springframework.data.repository.CrudRepository;

import com.mit.entity.CustomerDetailsBackup;

public interface CustomerDetailsBackUpRepository extends CrudRepository<CustomerDetailsBackup, Long>  {
	
}
