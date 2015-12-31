package com.morethanheroic.swords.dependency.aspect;

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
@Order(Ordered.HIGHEST_PRECEDENCE)
public class InjectAtReturnAspect {

    @Autowired
    private AutowireCapableBeanFactory autowireBeanFactory;

    @Around("@annotation(com.morethanheroic.swords.dependency.InjectAtReturn)")
    @SuppressWarnings("checkstyle:illegalthrows")
    public Object memoize(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        final Object result = proceedingJoinPoint.proceed();

        autowireBeanFactory.autowireBean(result);

        return result;
    }
}
