package com.mit.aop;

import java.time.LocalDateTime;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class ExecutionTraceAspect {
	private static final Logger logger = LoggerFactory.getLogger(ExecutionTraceAspect.class);
	private final ExecutionLogRepository executionLogRepository;
	private final ObjectMapper objectMapper = new ObjectMapper();

	public ExecutionTraceAspect(ExecutionLogRepository executionLogRepository) {
		this.executionLogRepository = executionLogRepository;
	}

	@Pointcut("execution(* com.mit.service..*(..)) || execution(* com.mit.controller..*(..))")
	public void monitoredMethods() {
	}

	@Around("monitoredMethods()")
	public Object traceExecution(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();
		String methodName = joinPoint.getSignature().toShortString();
		String params = objectMapper.writeValueAsString(joinPoint.getArgs());

		logger.info("Entering: {} with params: {}", methodName, params);
		Object result;
		boolean success = true;
		try {
			result = joinPoint.proceed();
		} catch (Exception e) {
			success = false;
			logger.error("Exception in {}", methodName, e);
			throw e;
		}
		long executionTime = System.currentTimeMillis() - startTime;
		String resultJson = objectMapper.writeValueAsString(result);

		executionLogRepository
				.save(new ExecutionLog(methodName, params, resultJson, executionTime, success, LocalDateTime.now()));
		return result;
	}
}