package ru.alfabank.practice.kagrishin.bankonboarding.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
@RequiredArgsConstructor
public class LoggingAspect {

    private final ObjectMapper objectMapper;

    @Pointcut("@annotation(ru.alfabank.practice.kagrishin.bankonboarding.logging.Log)")
    private void withLogAnnotation() {
    }

    @Around(value = "withLogAnnotation()")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("[{}, args = {};] start", proceedingJoinPoint.getSignature(), proceedingJoinPoint.getArgs());
        try {
            Object result = proceedingJoinPoint.proceed();
            log.info("[{}, args = {};] with returning = {}; end",
                    proceedingJoinPoint.getSignature(), proceedingJoinPoint.getArgs(),
                    objectMapper.writeValueAsString(result)
            );
            return result;
        } catch (Throwable ex) {
            log.info("[{}, args = {};] threw exception {}", proceedingJoinPoint.getSignature(), proceedingJoinPoint.getArgs(), ex);
            throw ex;
        }
    }
}
