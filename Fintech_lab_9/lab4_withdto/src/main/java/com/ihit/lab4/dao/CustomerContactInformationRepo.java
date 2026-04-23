package com.ihit.lab4.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ihit.lab4.entity.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerContactInformationRepo extends JpaRepository<CustomerContactInformation,Long>{
@Query("SELECT c FROM CustomerContactInformation c WHERE c.crudFlag <> :flag")
Page<CustomerContactInformation> findActive(@Param("flag") String flag, Pageable pageable);

Page<CustomerContactInformation> findByCrudFlagNot(String flag, Pageable pageable);
};