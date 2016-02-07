package com.morethanheroic.swords.movement.event;

import com.morethanheroic.swords.login.service.event.domain.RegistrationEventConfiguration;
import com.morethanheroic.swords.login.service.event.domain.RegistrationEventHandler;
import com.morethanheroic.swords.movement.repository.domain.MovementMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create the user's movement data in the database.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MovementRegistrationEventHandler extends RegistrationEventHandler {

    private static final int STARTING_POSITION_X = 6;
    private static final int STARTING_POSITION_Y = 9;
    private static final int STARTING_MAP_ID = 1;

    @NonNull
    private final MovementMapper movementMapper;

    @Override
    public void onEvent(RegistrationEventConfiguration eventConfiguration) {
        movementMapper.createNewPosition(eventConfiguration.getUserEntity().getId(), STARTING_MAP_ID, STARTING_POSITION_X, STARTING_POSITION_Y);
    }
}
