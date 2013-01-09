package com.vmware.poc.cucumber.jvm.aspect;

import gherkin.formatter.model.Step;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import cucumber.runtime.model.CucumberFeature;
import cucumber.runtime.model.CucumberScenario;

@Aspect
public class CucumberAspect {

	private static final Logger logger = Logger.getLogger(CucumberAspect.class);
	private CucumberFeature feature;
	private CucumberScenario scenario;
	private Step step;

	public CucumberAspect() {
		logger.info("Cucumber Aspect was created");
	}
	
	//1.  CucumberFeature::getFeature
	//2.  CucumberScenario::run
	//3.  StepContainer::runStep
	
	@Pointcut ("within(cucumber.runtime.model.*) && execution(* *(..))")
	public void logging() {}

	@Before(value = "logging()", argNames = "joinPoint")
	public void enteringMethod(JoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
		String className = signature.getDeclaringType().getSimpleName();
		String methodName = signature.getName();
		logger.debug("enteringMethod " + className + "::" + methodName);
		
		if(className.equals("CucumberFeature") && methodName.equals("getI18n")) {
			this.feature = ((CucumberFeature)joinPoint.getThis());
			logger.info("Discovered feature: " + this.feature.getFeature().getName());
			
		} else if(className.equals("CucumberScenario") && methodName.equals("run")) {
			this.scenario = ((CucumberScenario)joinPoint.getThis());
			
			logger.info("Discovered scenario: " + this.scenario.getVisualName());
			
		} else if(className.equals("StepContainer") && methodName.equals("runStep")) {
			this.step = (Step) joinPoint.getArgs()[0];
			logger.info("Discovered step: " + this.step.getName());
		}

	}

	@AfterReturning(pointcut = "logging()", returning = "returnValue", argNames = "joinPoint,returnValue")
	public void leavingMethod(JoinPoint joinPoint, Object returnValue) {
		Signature signature = joinPoint.getSignature();
		String className = signature.getDeclaringType().getSimpleName();
		String methodName = signature.getName();
		logger.debug("leavingMethod " + className + "::" + methodName);
	}

}
