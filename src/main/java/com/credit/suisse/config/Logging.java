package com.credit.suisse.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.credit.suisse.constants.LogConstants;

@Aspect
@Component
public class Logging {

	@Around("execution (* com.credit.suisse..*(..))")
	public Object logMethods(ProceedingJoinPoint joinPoint) throws Throwable {
		String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
		String methodName = joinPoint.getSignature().getName();
		LogConstants.INFO_LOGGER.info("In class " + className + " entering mthod " + methodName);
		Object result = joinPoint.proceed();
		LogConstants.INFO_LOGGER.info("In class " + className + " exiting mthod " + methodName);
		return result;
	}
}
