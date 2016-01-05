package com.morethanheroic.swords.memoize.context;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * This class is only used as a key for the memoizing mechanism.
 */
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@SuppressFBWarnings("EI_EXPOSE_REP2")
public class InvocationContext {

    private final Class targetClass;
    private final String targetMethod;
    private final Object[] args;
}
