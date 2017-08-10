package com.morethanheroic.swords.combat.service.calc.teardown.event;

import java.util.Collections;
import java.util.List;

import com.morethanheroic.swords.combat.entity.domain.UserCombatEntity;
import com.morethanheroic.swords.combat.service.event.terminate.TeardownCombatEventHandler;
import com.morethanheroic.swords.combat.service.event.terminate.domain.TeardownCombatEventContext;
import com.morethanheroic.swords.combat.step.domain.CombatStep;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * This {@link TeardownCombatEventHandler} update the user's basic statistics after the combat.
 */
@Order(-10)
@Service
@RequiredArgsConstructor
public class UpdateUserTeardownCombatEventHandler implements TeardownCombatEventHandler {

    @Override
    public List<CombatStep> handleEvent(TeardownCombatEventContext teardownCombatEventContext) {
        final UserCombatEntity userCombatEntity = teardownCombatEventContext.getUser();
        userCombatEntity.getUserEntity().setBasicStats(userCombatEntity.getActualHealth(), userCombatEntity.getActualMana(), userCombatEntity.getUserEntity().getMovementPoints());


        return Collections.emptyList();
    }
}
