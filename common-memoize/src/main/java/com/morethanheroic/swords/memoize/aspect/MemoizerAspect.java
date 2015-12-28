package com.morethanheroic.swords.memoize.aspect;

import com.morethanheroic.swords.memoize.context.InvocationContext;
import com.morethanheroic.swords.memoize.service.RequestScopeCache;
import lombok.extern.log4j.Log4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Log4j
@Component
public class MemoizerAspect {

    @Autowired
    private RequestScopeCache requestScopeCache;

    @Around("@annotation(com.morethanheroic.swords.memoize.Memoize)")
    public Object memoize(ProceedingJoinPoint pjp) throws Throwable {
        InvocationContext invocationContext = new InvocationContext(
                pjp.getSignature().getDeclaringType(),
                pjp.getSignature().getName(),
                pjp.getArgs()
        );

        Object result = requestScopeCache.get(invocationContext);
        if (RequestScopeCache.NONE == result) {
            result = pjp.proceed();
            log.info("Memoizing result: " + result + ", for method invocation: " + invocationContext);
            requestScopeCache.put(invocationContext, result);
        } else {
            log.info("Using memoized result: " + result + ", for method invocation: " + invocationContext);
        }

        return result;
    }
}
