package com.mit.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mit.entity.CustomerSignin;

public interface CustomerSigninRepository extends JpaRepository<CustomerSignin, Long> {
	CustomerSignin findByUsernameAndPassword(String username,String password);
}
