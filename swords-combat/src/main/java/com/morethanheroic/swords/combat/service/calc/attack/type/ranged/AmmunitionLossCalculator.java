package com.morethanheroic.swords.combat.service.calc.attack.type.ranged;

import com.morethanheroic.math.RandomCalculator;
import com.morethanheroic.swords.combat.step.domain.DefaultCombatStep;
import com.morethanheroic.swords.combat.entity.domain.UserCombatEntity;
import com.morethanheroic.swords.combat.step.domain.CombatStep;
import com.morethanheroic.swords.combat.step.message.CombatMessageFactory;
import com.morethanheroic.swords.equipment.EquipmentEntityFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AmmunitionLossCalculator {

    private static final int MINIMUM_ROLL = 1;
    private static final int MAXIMUM_ROLL = 100;
    private static final int AMMUNITION_LOST_ABOVE_TARGET = 75;

    private static final int AMMUNITION_DECREASE_AMOUNT = 1;

    private final RandomCalculator randomCalculator;
    private final EquipmentEntityFactory equipmentEntityFactory;
    private final CombatMessageFactory combatMessageFactory;

    public List<CombatStep> calculateAmmunitionLoss(final UserCombatEntity attacker) {
        final List<CombatStep> result = new ArrayList<>();

        if (randomCalculator.randomRollForTarget(MINIMUM_ROLL, MAXIMUM_ROLL, AMMUNITION_LOST_ABOVE_TARGET)) {
            equipmentEntityFactory.getEntity(attacker.getUserEntity()).decreaseAmmunition(AMMUNITION_DECREASE_AMOUNT);

            result.add(
                    DefaultCombatStep.builder()
                            .message(combatMessageFactory.newMessage("ammunition", "COMBAT_MESSAGE_ARROW_LOST", AMMUNITION_DECREASE_AMOUNT))
                            .build()
            );
        }

        return result;
    }
}
