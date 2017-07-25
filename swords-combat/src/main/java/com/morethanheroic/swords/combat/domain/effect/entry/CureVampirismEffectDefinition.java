package com.morethanheroic.swords.combat.domain.effect.entry;

import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.effect.ImprovedCombatEffectDefinition;
import com.morethanheroic.swords.combat.entity.domain.UserCombatEntity;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.vampire.service.VampireCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CureVampirismEffectDefinition extends ImprovedCombatEffectDefinition {

    private final VampireCalculator vampireCalculator;

    @Override
    public void apply(CombatEffectApplyingContext effectApplyingContext) {
        if (effectApplyingContext.getSource().isUser()) {
            final UserEntity userEntity = ((UserCombatEntity) effectApplyingContext.getSource().getCombatEntity()).getUserEntity();

            vampireCalculator.setVampire(userEntity, false);
        }
    }

    @Override
    public String getId() {
        return "cure_vampirism";
    }
}
