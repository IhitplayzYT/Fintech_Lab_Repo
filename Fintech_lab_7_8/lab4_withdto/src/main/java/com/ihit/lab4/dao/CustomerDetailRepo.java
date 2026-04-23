package com.ihit.lab4.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ihit.lab4.entity.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerDetailRepo extends JpaRepository<CustomerDetail,Long>{
@Query("SELECT c FROM CustomerDetail c WHERE c.crudFlag <> :flag")
Page<CustomerDetail> findActive(@Param("flag") String flag, Pageable pageable);

Page<CustomerDetail> findByCrudFlagNot(String flag, Pageable pageable);
};