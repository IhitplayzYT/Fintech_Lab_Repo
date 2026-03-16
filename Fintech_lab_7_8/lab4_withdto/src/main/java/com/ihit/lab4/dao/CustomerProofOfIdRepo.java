package com.ihit.lab4.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ihit.lab4.entity.CustomerProofOfId;

public interface CustomerProofOfIdRepo extends JpaRepository<CustomerProofOfId,Long>{
@Query("SELECT c FROM CustomerProofOfId c WHERE c.crudFlag <> :flag")
Page<CustomerProofOfId> findActive(@Param("flag") String flag, Pageable pageable);

Page<CustomerProofOfId> findByCrudFlagNot(String flag, Pageable pageable);

};