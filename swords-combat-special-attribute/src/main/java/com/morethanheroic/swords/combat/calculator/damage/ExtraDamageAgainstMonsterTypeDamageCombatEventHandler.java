package com.morethanheroic.swords.combat.calculator.damage;

import com.google.common.collect.ImmutableMap;
import com.morethanheroic.swords.attribute.domain.SpecialAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.SimpleValueAttributeCalculationResult;
import com.morethanheroic.swords.combat.bonus.domain.CombatBonus;
import com.morethanheroic.swords.combat.entity.CombatEntityUtil;
import com.morethanheroic.swords.combat.entity.domain.CombatEntity;
import com.morethanheroic.swords.combat.entity.domain.MonsterCombatEntity;
import com.morethanheroic.swords.combat.entity.domain.UserCombatEntity;
import com.morethanheroic.swords.combat.service.event.damage.DamageCombatEventHandler;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageEventCalculationContext;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageEventCalculationResult;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.monster.domain.MonsterType;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExtraDamageAgainstMonsterTypeDamageCombatEventHandler implements DamageCombatEventHandler {

    private final static Map<MonsterType, SpecialAttribute> MONSTER_TYPE_SPECIAL_ATTRIBUTE_MAP = new ImmutableMap.Builder<MonsterType, SpecialAttribute>()
            .put(MonsterType.VAMPIRE, SpecialAttribute.EXTRA_DAMAGE_AGAINST_VAMPIRES)
            .build();

    private final CombatEntityUtil combatEntityUtil;
    private final GlobalAttributeCalculator globalAttributeCalculator;

    @Override
    public DamageEventCalculationResult handleEvent(final CombatEntity damagingEntity, final CombatEntity damagedEntity, final DamageEventCalculationContext damageEventCalculationContext) {
        if (combatEntityUtil.isPlayer(damagingEntity)) {
            //TODO: Add combat messages!!
            int extraDamage = 0;

            final UserEntity userEntity = ((UserCombatEntity) damagingEntity).getUserEntity();
            final MonsterDefinition monsterDefinition = ((MonsterCombatEntity) damagedEntity).getMonsterDefinition();

            extraDamage += calculateExtraDamageForType(userEntity, monsterDefinition.getType());
            extraDamage += calculateExtraDamageForType(userEntity, monsterDefinition.getSubtype());

            return DamageEventCalculationResult.builder()
                    .combatSteps(Collections.emptyList())
                    .bonusDamage(
                            CombatBonus.builder()
                                    .value(extraDamage)
                                    .build()
                    )
                    .build();
        }

        return DamageEventCalculationResult.EMPTY_RESULT;
    }

    private int calculateExtraDamageForType(final UserEntity userEntity, final MonsterType monsterType) {
        if (MONSTER_TYPE_SPECIAL_ATTRIBUTE_MAP.containsKey(monsterType)) {
            //TODO: Be able to add d2/d4 etc damages here too
            final SimpleValueAttributeCalculationResult attributeCalculationResult = globalAttributeCalculator.calculateActualValue(userEntity, MONSTER_TYPE_SPECIAL_ATTRIBUTE_MAP.get(monsterType));

            return attributeCalculationResult.getValue();
        }

        return 0;
    }
}
