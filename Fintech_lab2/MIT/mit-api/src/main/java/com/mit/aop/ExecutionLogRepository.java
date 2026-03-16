package com.mit.aop;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecutionLogRepository extends JpaRepository<ExecutionLog, Long> {
	Optional<ExecutionLog> findTopByMethodNameAndSuccessOrderByTimestampDesc(String methodName, boolean success);

}
