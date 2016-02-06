package com.morethanheroic.swords.event;

/**
 * You can use this interface to react to events.
 *
 * @param <T> The configuration that used while handling the event.
 */
public interface EventHandler<T extends EventConfiguration> {

    void onEvent(T eventConfiguration);
}
