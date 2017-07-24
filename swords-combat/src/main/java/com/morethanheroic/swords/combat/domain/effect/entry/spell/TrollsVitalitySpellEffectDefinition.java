package com.morethanheroic.swords.combat.domain.effect.entry.spell;

import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.effect.ImprovedCombatEffectDefinition;
import com.morethanheroic.swords.combat.entity.domain.UserCombatEntity;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.statuseffect.service.StatusEffectManipulator;
import com.morethanheroic.swords.statuseffect.service.definition.cache.StatusEffectDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class TrollsVitalitySpellEffectDefinition extends ImprovedCombatEffectDefinition {

    private static final int TROLLS_VITALITY_STATUS_EFFECT_ID = 1;
    private static final int XP_REWARD = 20;

    private final StatusEffectManipulator statusEffectManipulator;
    private final StatusEffectDefinitionCache statusEffectDefinitionCache;
    private final SkillEntityFactory skillEntityFactory;

    @Override
    public void apply(CombatEffectApplyingContext effectApplyingContext) {
        final int expirationTime = effectApplyingContext.getEffectSettings().getSettingAsInt("expiration-time-in-seconds");

        if (effectApplyingContext.getDestination().isUser()) {
            final UserEntity userEntity = ((UserCombatEntity) effectApplyingContext.getDestination().getCombatEntity()).getUserEntity();

            statusEffectManipulator.applyStatusEffect(userEntity, statusEffectDefinitionCache.getDefinition(TROLLS_VITALITY_STATUS_EFFECT_ID), Instant.now().plus(expirationTime, ChronoUnit.SECONDS));

            skillEntityFactory.getEntity(userEntity).increaseExperience(SkillType.ALTERATION, XP_REWARD);
        } else {
            //TODO: Add a way to apply status effects on monsters too.
        }
    }

    @Override
    public String getId() {
        return "trolls_vitality";
    }
}
