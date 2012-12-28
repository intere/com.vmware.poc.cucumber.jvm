package com.vmware.poc.cucumber.jvm.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component("cucumberAspect")
public class CucumberAspect {
	
	private static final Logger LOG = Logger.getLogger(CucumberAspect.class);
	
	public CucumberAspect() {
		LOG.info("Cucumber Aspect was created");
	}

	@Pointcut("execution(* cucumber..*(..))")
	private Object cucumberCutpoint(ProceedingJoinPoint pjp) throws Throwable {
		StopWatch sw = new StopWatch(getClass().getSimpleName());
		try {
			sw.start(pjp.getSignature().getName());
			return pjp.proceed();
		} finally {
			sw.stop();
			LOG.info(sw.prettyPrint());
		}
	}
}
