package com.ihit.lab4.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ihit.lab4.entity.*;

public interface CustomerClassificationRepo extends JpaRepository<CustomerClassification,Long>{
@Query("SELECT c FROM CustomerClassification c WHERE c.crudFlag <> :flag")
Page<CustomerClassification> findActive(@Param("flag") String flag, Pageable pageable);
Page<CustomerClassification> findByCrudFlagNot(String flag, Pageable pageable);
};