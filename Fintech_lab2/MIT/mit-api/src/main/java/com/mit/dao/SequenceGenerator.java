package com.mit.dao;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

@Component
public class SequenceGenerator {
	@PersistenceContext
	private EntityManager entityManager;

	public Long getNextSequenceValue(String sequenceName) {
		BigDecimal nextValue = (BigDecimal) entityManager.createNativeQuery("SELECT " + sequenceName + ".NEXTVAL FROM dual")
			      .getSingleResult();
	    return nextValue.longValue();
	    
	}

}
