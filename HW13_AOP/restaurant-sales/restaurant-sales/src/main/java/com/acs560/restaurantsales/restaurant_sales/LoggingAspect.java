package com.acs560.restaurantsales.restaurant_sales;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * Pointcut to match all public methods in controller, service, and repository layers.
     */
    @Before("execution(public * com.acs560.restaurantsales.restaurant_sales.controllers..*(..)) || " +
            "execution(public * com.acs560.restaurantsales.restaurant_sales.services..*(..)) || " +
            "execution(public * com.acs560.restaurantsales.restaurant_sales.repositories..*(..))")
    public void logMethodCall(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        logger.info("Intercepted method: " + className + "." + methodName + " - JB");
    }
}
