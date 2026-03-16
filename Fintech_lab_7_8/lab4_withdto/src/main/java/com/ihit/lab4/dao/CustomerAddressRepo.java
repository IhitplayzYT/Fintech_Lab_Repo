package com.ihit.lab4.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ihit.lab4.entity.CustomerAddress;

public interface CustomerAddressRepo extends JpaRepository<CustomerAddress,Long>{
@Query("SELECT c FROM CustomerAddress c WHERE c.crudFlag <> :flag")
Page<CustomerAddress> findActive(@Param("flag") String flag, Pageable pageable);
Page<CustomerAddress> findByCrudFlagNot(String flag, Pageable pageable);
};