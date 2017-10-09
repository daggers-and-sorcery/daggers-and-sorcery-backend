package com.morethanheroic.swords.login.service.event;

import com.morethanheroic.event.EventDispatcher;
import com.morethanheroic.swords.login.service.event.domain.RegistrationEventConfiguration;
import com.morethanheroic.swords.login.service.event.domain.RegistrationEventHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Dispatch new RegistrationEvents at login.
 */
@Service
@RequiredArgsConstructor
public class RegistrationEventDispatcher implements EventDispatcher<RegistrationEventConfiguration> {

    private final List<RegistrationEventHandler> registrationEventHandlers;

    @Override
    public void dispatch(final RegistrationEventConfiguration eventConfiguration) {
        for (RegistrationEventHandler registrationEventHandler : registrationEventHandlers) {
            registrationEventHandler.onEvent(eventConfiguration);
        }
    }
}
