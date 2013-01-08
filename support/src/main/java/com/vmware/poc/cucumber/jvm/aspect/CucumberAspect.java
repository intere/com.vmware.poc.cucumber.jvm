package com.vmware.poc.cucumber.jvm.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
//@Component("cucumberAspect")
public class CucumberAspect {

	private static final Logger logger = Logger.getLogger(CucumberAspect.class);

	public CucumberAspect() {
		logger.info("Cucumber Aspect was created");
	}

	@Pointcut ("within(cucumber.runtime.model.*) && execution(* *(..))")
	public void logging() {}

	@Before(value = "logging()", argNames = "joinPoint")
	public void enteringMethod(JoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
		String className = signature.getDeclaringType().getSimpleName();
		String methodName = signature.getName();
		logger.info("enteringMethod " + className + "::" + methodName);
	}

	@AfterReturning(pointcut = "logging()", returning = "returnValue", argNames = "joinPoint,returnValue")
	public void leavingMethod(JoinPoint joinPoint, Object returnValue) {
		Signature signature = joinPoint.getSignature();
		String className = signature.getDeclaringType().getSimpleName();
		String methodName = signature.getName();
		logger.info("leavingMethod " + className + "::" + methodName);
	}

}
