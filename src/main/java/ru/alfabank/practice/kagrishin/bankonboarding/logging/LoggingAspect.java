package ru.alfabank.practice.kagrishin.bankonboarding.logging;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class LoggingAspect {

    @Pointcut("@annotation(ru.alfabank.practice.kagrishin.bankonboarding.logging.Log)")
    private void withLogAnnotation() {
    }

    @Before("withLogAnnotation()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("[{}, args = {};] start", joinPoint.getSignature(), joinPoint.getArgs());
    }

    @AfterReturning(value = "withLogAnnotation()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        log.info("[{}, args = {};] with returning = {}; end", joinPoint.getSignature(), joinPoint.getArgs(), result);
    }
}
