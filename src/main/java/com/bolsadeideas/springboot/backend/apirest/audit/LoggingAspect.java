package com.bolsadeideas.springboot.backend.apirest.audit;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * This class is used for logging execution of service and repository
 * Spring components.
 *
 * @author Ernesto A. Rodriguez Acosta
 */
@Aspect
@Component
@Slf4j
public class LoggingAspect {

    /**
     * Pointcut that matches all repositories, services and Web REST endpoints.
     */
    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
                " || within(@org.springframework.stereotype.Service *)" +
                "within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {
        // Method is empty as this is just a Pointcut, the implementations
        // are in the advices.
    }

    @Pointcut("within(com.bolsadeideas.springboot.backend.apirest..*)" +
            " || within(com.bolsadeideas.springboot.backend.apirest.services..*)" +
            "within(com.bolsadeideas.springboot.backend.apirest.controllers..*)")
    public void applicationPackagePointcut() {
        // Method is empty as this is just a Pointcut, the implementations
        // are in the advices.
    }

    /**
     * Advice that logs methods throwing exceptions.
     *
     * @param joinPoint joint point for advice
     * @param e exception
     */
    @AfterThrowing(value = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("Exception in {}.{} with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
    }

    /**
     * Advice that logs when a method is entered and exited.
     * @param proceedingJoinPoint join point for advice
     * @return  result
     * @throws Throwable throws IllegalArgumentException
     */
    @Around(value = "applicationPackagePointcut() && springBeanPointcut()")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        if(log.isDebugEnabled()) {
            log.debug("BEGIN: {}.{}() with argument[s] = {}", proceedingJoinPoint.getSignature().getDeclaringTypeName(),
                    proceedingJoinPoint.getSignature().getName(), Arrays.toString(proceedingJoinPoint.getArgs()));
        }

        try {
            Object result = proceedingJoinPoint.proceed();

            if(log.isDebugEnabled()) {
                log.debug("END: {}.{}() with result = {}", proceedingJoinPoint.getSignature().getDeclaringTypeName(),
                        proceedingJoinPoint.getSignature().getName(), result);
            }

            return result;
        }
        catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}-{}()", Arrays.toString(proceedingJoinPoint.getArgs()),
                    proceedingJoinPoint.getSignature().getDeclaringTypeName(),
                    proceedingJoinPoint.getSignature().getName());

            throw e;
        }
    }
}
