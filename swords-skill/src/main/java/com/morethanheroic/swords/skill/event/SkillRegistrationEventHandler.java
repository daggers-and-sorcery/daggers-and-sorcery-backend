package com.morethanheroic.swords.skill.event;

import com.morethanheroic.swords.login.service.event.domain.RegistrationEventConfiguration;
import com.morethanheroic.swords.login.service.event.domain.RegistrationEventHandler;
import com.morethanheroic.swords.skill.repository.domain.SkillMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create the user's skill data in the database.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SkillRegistrationEventHandler extends RegistrationEventHandler {

    @NonNull
    private final SkillMapper skillMapper;

    @Override
    public void onEvent(RegistrationEventConfiguration eventConfiguration) {
        skillMapper.insert(eventConfiguration.getUserEntity().getId());
    }
}
