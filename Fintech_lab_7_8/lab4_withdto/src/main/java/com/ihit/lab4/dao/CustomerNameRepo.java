package com.ihit.lab4.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ihit.lab4.entity.CustomerName;

public interface CustomerNameRepo extends JpaRepository<CustomerName, Long> {
@Query("SELECT c FROM CustomerName c WHERE c.crudFlag <> :flag")
Page<CustomerName> findActive(@Param("flag") String flag, Pageable pageable);

Page<CustomerName> findByCrudFlagNot(String flag, Pageable pageable);

}