package com.ihit.lab4.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ihit.lab4.entity.*;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerIdentificationRepo extends JpaRepository<CustomerIdentification, Long> {
@Query("SELECT c FROM CustomerIdentification c WHERE c.crudFlag <> :flag")
Page<CustomerIdentification> findActive(@Param("flag") String flag, Pageable pageable);

Page<CustomerIdentification> findByCrudFlagNot(String flag, Pageable pageable);
}