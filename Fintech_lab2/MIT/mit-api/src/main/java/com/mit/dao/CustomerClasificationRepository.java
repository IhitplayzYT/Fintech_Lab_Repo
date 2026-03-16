package com.mit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mit.entity.CustomerClassification;

@Repository
public interface CustomerClasificationRepository extends JpaRepository<CustomerClassification, Long> {

	CustomerClassification findByCustomerClassificationID(Long customerClassificationID);
}