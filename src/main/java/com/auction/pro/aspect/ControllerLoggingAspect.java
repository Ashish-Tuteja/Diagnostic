package com.auction.pro.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class ControllerLoggingAspect {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ControllerLoggingAspect.class.getName());

	@Pointcut("within(@org.springframework.stereotype.Controller *)")
	public void controller() {
	}

	@Pointcut("execution(* *(..))")
	public void methodPointcut() {
	}

	@Pointcut("within(@org.springframework.web.bind.annotation.RequestMapping *)")
	public void requestMapping() {
	}

	@Before("controller() && methodPointcut() && requestMapping()")
	public void aroundControllerMethod(JoinPoint joinPoint) throws Throwable {
		LOGGER.debug("Invoked: " + getMethodInfo(joinPoint));
	}

	@AfterReturning("controller() && methodPointcut() && requestMapping()")
	public void afterControllerMethod(JoinPoint joinPoint) {
		LOGGER.debug("Finished: " + getMethodInfo(joinPoint));
	}

	private String getMethodInfo(JoinPoint joinPoint) {
		return joinPoint.getTarget().getClass() + "#"
				+ joinPoint.getSignature().getName() + "\n\targs:"
				+ Arrays.toString(joinPoint.getArgs()) + " ";
	}

}
