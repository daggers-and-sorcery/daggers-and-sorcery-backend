package com.morethanheroic.swords.event;

/**
 * This interface is used to dispatch events to {@link EventHandler}s.
 *
 * @param <T> The configuration that used while handling the event.
 */
public interface EventDispatcher<T extends EventConfiguration> {

    void dispatch(T eventConfiguration);
}
