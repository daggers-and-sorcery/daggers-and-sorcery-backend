package com.morethanheroic.swords.combat.domain.effect.entry;

import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.effect.ImprovedCombatEffectDefinition;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.statuseffect.service.StatusEffectManipulator;
import com.morethanheroic.swords.statuseffect.service.definition.cache.StatusEffectDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * Give a status effect to the target.
 */
@Service
@RequiredArgsConstructor
public class GiveStatusEffectCombatEffectDefinition extends ImprovedCombatEffectDefinition {

    private final StatusEffectManipulator statusEffectManipulator;
    private final StatusEffectDefinitionCache statusEffectDefinitionCache;

    @Override
    public void apply(final CombatEffectApplyingContext effectApplyingContext) {
        final int statusEffectId = effectApplyingContext.getEffectSettings().getSettingAsInt("status-effect-id");
        final int expirationTime = effectApplyingContext.getEffectSettings().getSettingAsInt("expiration-time-in-seconds");

        if (effectApplyingContext.getDestination().isUser()) {
            final UserEntity userEntity = ((UserCombatEntity) effectApplyingContext.getDestination().getCombatEntity()).getUserEntity();

            statusEffectManipulator.applyStatusEffect(userEntity, statusEffectDefinitionCache.getDefinition(statusEffectId), Instant.now().plus(expirationTime, ChronoUnit.SECONDS));
        } else {
            //TODO: Add a way to apply status effects on monsters too.
        }
    }

    @Override
    public String getId() {
        return "give_status_effect";
    }
}
