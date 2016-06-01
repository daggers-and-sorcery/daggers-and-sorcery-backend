package com.morethanheroic.swords.memoize.aspect;

import com.morethanheroic.swords.memoize.context.InvocationContext;
import com.morethanheroic.swords.memoize.service.RequestScopeCache;
import lombok.extern.log4j.Log4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * This aspect is used to create the memoize functionality with the {@link com.morethanheroic.swords.memoize.Memoize} annotation.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Memoization">https://en.wikipedia.org/wiki/Memoization</a>
 */
@Aspect
@Component
@Log4j
public class MemoizeAspect {

    @Autowired
    private RequestScopeCache requestScopeCache;

    @Around("@annotation(com.morethanheroic.swords.memoize.Memoize)")
    @SuppressWarnings("checkstyle:illegalthrows")
    public Object memoize(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        if (!isRequestContext()) {
            return proceedingJoinPoint.proceed();
        }

        final InvocationContext invocationContext = buildInvocationContext(proceedingJoinPoint);

        Object result = requestScopeCache.get(invocationContext);
        if (RequestScopeCache.NONE == result) {
            result = proceedingJoinPoint.proceed();

            log.debug("Memoizing result: " + result + ", for method invocation: " + invocationContext);

            requestScopeCache.put(invocationContext, result);
        } else {
            log.debug("Using memoized result: " + result + ", for method invocation: " + invocationContext);
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

    private boolean isRequestContext() {
        return RequestContextHolder.getRequestAttributes() != null;
    }
}
