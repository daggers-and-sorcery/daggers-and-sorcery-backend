package com.morethanheroic.swords.settings.event;

import com.morethanheroic.swords.login.service.event.domain.RegistrationEventConfiguration;
import com.morethanheroic.swords.login.service.event.domain.RegistrationEventHandler;
import com.morethanheroic.swords.settings.repository.domain.SettingsMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create the user's settings data in the database.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SettingsRegistrationEventHandler extends RegistrationEventHandler {

    @NonNull
    private final SettingsMapper settingsMapper;

    @Override
    public void onEvent(RegistrationEventConfiguration eventConfiguration) {
        settingsMapper.insert(eventConfiguration.getUserEntity().getId());
    }
}
