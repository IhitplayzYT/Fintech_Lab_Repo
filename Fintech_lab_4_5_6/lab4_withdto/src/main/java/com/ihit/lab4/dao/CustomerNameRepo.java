package com.ihit.lab4.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ihit.lab4.entity.*;

public interface CustomerNameRepo extends JpaRepository<CustomerName,Long>{};
