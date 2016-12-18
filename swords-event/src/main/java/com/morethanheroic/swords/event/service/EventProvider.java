package com.morethanheroic.swords.event.service;

import com.google.common.collect.ImmutableMap;
import com.morethanheroic.swords.event.domain.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventProvider {

    private final Map<Integer, Event> eventMap;

    public EventProvider(final List<Event> events) {
        eventMap = events.stream()
                .collect(Collectors.collectingAndThen(Collectors.toMap(Event::getId, Function.identity()), ImmutableMap::copyOf));
    }

    public Event getEvent(int id) {
        return eventMap.get(id);
    }

    public boolean eventExists(int id) {
        return eventMap.containsKey(id);
    }
}
