package com.morethanheroic.swords.memoize.service;

import com.morethanheroic.swords.memoize.context.InvocationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * This cache is re-created for every request, can be used to cache method invocations for one request time. It's not thread safe!
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "request")
public class RequestScopeCache {

    public static final Object NONE = new Object();

    private final Map<InvocationContext, Object> cache = new HashMap<>();

    public Object get(InvocationContext invocationContext) {
        return cache.containsKey(invocationContext) ? cache.get(invocationContext) : NONE;
    }

    public void put(InvocationContext methodInvocation, Object result) {
        cache.put(methodInvocation, result);
    }
}
