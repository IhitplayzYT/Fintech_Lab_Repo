package com.mit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mit.entity.CustomerDetails;

public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, Long>  {
	
	@Query(value ="Select * from CUST_DTL A where A.CSTID_IDFR=:Id  AND A.CUST_CRUD_VALUE<>'D' And A.CUST_HOST_TS=(Select Max (CUST_HOST_TS) from CUST_DTL B where A.CSTID_IDFR=B.CSTID_IDFR)", nativeQuery = true)
	CustomerDetails findBycustomerIdentifier(Long Id);
	
	@Query(value ="Select * from CUST_DTL where CUST_TYPE = :custType", nativeQuery = true)
	List<CustomerDetails> getCustomerByType(@Param("custType") String custType);
	
	@Query(value ="Select * from CUST_DTL A where A.CUST_CRUD_VALUE<>'D' And A.CUST_HOST_TS=(Select Max (CUST_HOST_TS) from CUST_DTL B where A.CSTID_IDFR=B.CSTID_IDFR)", nativeQuery = true)
	List<CustomerDetails> getAllCustomers();
	
	
	
	
}
