package com.morethanheroic.swords.memoize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marked methods are memoized. Their return value is cached for the lifetime of the request. The caching is based on the arguments so
 * if the memoized method is called with different arguments in the same request the method is run again and the result is cached for
 * the new arguments separately.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Memoization">https://en.wikipedia.org/wiki/Memoization</a>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Memoize {
}
