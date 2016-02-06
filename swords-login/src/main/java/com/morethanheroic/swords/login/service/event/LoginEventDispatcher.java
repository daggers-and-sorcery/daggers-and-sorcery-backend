package com.morethanheroic.swords.login.service.event;

import com.morethanheroic.swords.event.EventDispatcher;
import com.morethanheroic.swords.login.service.event.domain.LoginEventConfiguration;
import com.morethanheroic.swords.login.service.event.domain.LoginEventHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Dispatch new LoginEvents at login.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoginEventDispatcher implements EventDispatcher<LoginEventConfiguration> {

    private final List<LoginEventHandler> loginEventHandlers;

    @Override
    public void dispatch(final LoginEventConfiguration eventConfiguration) {
        for (LoginEventHandler loginEventHandler : loginEventHandlers) {
            loginEventHandler.onEvent(eventConfiguration);
        }
    }
}
