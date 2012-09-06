package com.vmware.poc.cucumber.jvm.spring;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vmware.poc.cucumber.jvm.TestConfig;

public class SpringLoader {
	
	private static ApplicationContext springContext;
	
	public static ApplicationContext getSpringContext() {
		if(springContext==null) {
			String cpUrl = "classpath:/com/vmware/poc/cucumber/jvm/poc-config.xml";
			
			springContext = new ClassPathXmlApplicationContext(cpUrl);			
		}
		
		return springContext;
	}

	/**
	 * This method will get you a TestConfig by name.
	 * @return
	 */
	public static TestConfig loadTestConfigByName(String name) {
		
		Map<String, TestConfig> configs = getSpringContext().getBeansOfType(TestConfig.class);
		
		TestConfig config = configs.get(name);
		
		if(config==null) {
			throw new IllegalArgumentException("TestConfig with bean name: '" + name + "' wasn't found");
		}
		
		return config;
	}
}
