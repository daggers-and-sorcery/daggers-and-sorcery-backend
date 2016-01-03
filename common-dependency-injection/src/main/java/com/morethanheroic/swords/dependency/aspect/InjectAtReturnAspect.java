package com.morethanheroic.swords.dependency.aspect;

import lombok.extern.log4j.Log4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(Ordered.LOWEST_PRECEDENCE)
@Log4j
public class InjectAtReturnAspect {

    @Autowired
    private AutowireCapableBeanFactory autowireBeanFactory;

    @Around("@annotation(com.morethanheroic.swords.dependency.InjectAtReturn)")
    @SuppressWarnings("checkstyle:illegalthrows")
    public Object injectAtReturn(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        final Object result = proceedingJoinPoint.proceed();

        log.debug("Autowiring: " + result);

        autowireBeanFactory.autowireBean(result);
        autowireBeanFactory.initializeBean(result, null);

        return result;
    }
}
