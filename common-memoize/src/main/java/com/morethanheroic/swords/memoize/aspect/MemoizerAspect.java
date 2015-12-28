package com.morethanheroic.swords.memoize.aspect;

import com.morethanheroic.swords.memoize.context.InvocationContext;
import com.morethanheroic.swords.memoize.service.RequestScopeCache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MemoizerAspect {

    @Autowired
    private RequestScopeCache requestScopeCache;

    @Around("@annotation(com.morethanheroic.swords.memoize.Memoize)")
    public Object memoize(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        final InvocationContext invocationContext = buildInvocationContext(proceedingJoinPoint);

        Object result = requestScopeCache.get(invocationContext);
        if (RequestScopeCache.NONE == result) {
            result = proceedingJoinPoint.proceed();

            requestScopeCache.put(invocationContext, result);
        }

        return result;
    }

    private InvocationContext buildInvocationContext(ProceedingJoinPoint proceedingJoinPoint) {
        return new InvocationContext(
                proceedingJoinPoint.getSignature().getDeclaringType(),
                proceedingJoinPoint.getSignature().getName(),
                proceedingJoinPoint.getArgs()
        );
    }
}
