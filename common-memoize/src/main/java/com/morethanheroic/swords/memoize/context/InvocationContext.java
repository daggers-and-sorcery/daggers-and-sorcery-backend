package com.morethanheroic.swords.memoize.context;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * This class is only used as a {@link java.util.Map} key for the memoizing mechanism.
 */
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class InvocationContext {

    private final Class targetClass;
    private final String targetMethod;
    private final Object[] args;
}
