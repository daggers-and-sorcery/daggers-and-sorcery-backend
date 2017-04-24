package com.morethanheroic.swords.event.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Holds the static data of an event.
 */
@Getter
@Builder
@ToString(of = {"id", "name"})
public class EventDefinition {

    private final int id;
    private final EventType type;
    private final String name;
    private final int length;
}
