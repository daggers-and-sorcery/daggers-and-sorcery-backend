package com.morethanheroic.swords.combat.service.attack;

import java.util.List;

import org.springframework.stereotype.Service;

import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.step.domain.CombatStep;
import com.morethanheroic.swords.combat.service.calc.AttackCalculatorProvider;
import com.morethanheroic.swords.combat.service.calc.AttackTypeCalculator;
import com.morethanheroic.swords.combat.service.calc.attack.AttackType;
import com.morethanheroic.swords.equipment.service.EquipmentFacade;
import com.morethanheroic.swords.user.domain.UserEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayerAttackCalculator {

    private final AttackCalculatorProvider attackCalculatorProvider;
    private final AttackTypeCalculator attackTypeCalculator;
    private final EquipmentFacade equipmentFacade;

    public List<CombatStep> playerAttack(final CombatContext combatContext) {
        return attackCalculatorProvider.getAttackCalculatorForAttackType(calculateUserAttackType(combatContext.getUser().getUserEntity()))
                                       .calculateAttack(combatContext.getUser(), combatContext.getOpponent(), combatContext);
    }

    private AttackType calculateUserAttackType(UserEntity userEntity) {
        return attackTypeCalculator.calculateAttackType(equipmentFacade.getEquipment(userEntity));
    }
}
