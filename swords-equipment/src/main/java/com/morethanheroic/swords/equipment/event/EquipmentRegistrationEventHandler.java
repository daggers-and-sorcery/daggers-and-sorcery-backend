package com.morethanheroic.swords.equipment.event;

import com.morethanheroic.swords.equipment.repository.domain.EquipmentMapper;
import com.morethanheroic.swords.login.service.event.domain.RegistrationEventConfiguration;
import com.morethanheroic.swords.login.service.event.domain.RegistrationEventHandler;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create the user's equipment data in the database.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EquipmentRegistrationEventHandler extends RegistrationEventHandler {

    @NonNull
    private final EquipmentMapper equipmentMapper;

    @Override
    public void onEvent(RegistrationEventConfiguration eventConfiguration) {
        equipmentMapper.insert(eventConfiguration.getUserEntity().getId());
    }
}
