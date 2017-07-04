package com.morethanheroic.swords.combat.calculator.damage;

import com.google.common.collect.ImmutableMap;
import com.morethanheroic.swords.attribute.domain.SpecialAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.DiceValueAttributeCalculationResult;
import com.morethanheroic.swords.combat.bonus.CombatBonusTransformer;
import com.morethanheroic.swords.combat.bonus.domain.CombatBonus;
import com.morethanheroic.swords.combat.entity.CombatEntityUtil;
import com.morethanheroic.swords.combat.entity.domain.CombatEntity;
import com.morethanheroic.swords.combat.entity.domain.MonsterCombatEntity;
import com.morethanheroic.swords.combat.entity.domain.UserCombatEntity;
import com.morethanheroic.swords.combat.service.event.damage.DamageCombatEventHandler;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageCombatEventContext;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageCombatEventResult;
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
    private final CombatBonusTransformer combatBonusTransformer;

    @Override
    public DamageCombatEventResult handleEvent(final CombatEntity damagingEntity, final CombatEntity damagedEntity, final DamageCombatEventContext damageCombatEventContext) {
        if (combatEntityUtil.isPlayer(damagingEntity)) {
            CombatBonus combatBonus = CombatBonus.EMPTY_COMBAT_BONUS;

            final UserEntity userEntity = ((UserCombatEntity) damagingEntity).getUserEntity();
            final MonsterDefinition monsterDefinition = ((MonsterCombatEntity) damagedEntity).getMonsterDefinition();

            combatBonus = combatBonus.add(calculateExtraDamageForType(userEntity, monsterDefinition.getType()));
            combatBonus = combatBonus.add(calculateExtraDamageForType(userEntity, monsterDefinition.getSubtype()));

            return DamageCombatEventResult.builder()
                    .combatSteps(Collections.emptyList())
                    .bonusDamage(combatBonus)
                    .build();
        }

        return DamageCombatEventResult.EMPTY_RESULT;
    }

    private CombatBonus calculateExtraDamageForType(final UserEntity userEntity, final MonsterType monsterType) {
        if (MONSTER_TYPE_SPECIAL_ATTRIBUTE_MAP.containsKey(monsterType)) {
            final DiceValueAttributeCalculationResult attributeCalculationResult = (DiceValueAttributeCalculationResult) globalAttributeCalculator.calculateActualValue(userEntity, MONSTER_TYPE_SPECIAL_ATTRIBUTE_MAP.get(monsterType));

            return combatBonusTransformer.createFrom(attributeCalculationResult);
        }

        return CombatBonus.EMPTY_COMBAT_BONUS;
    }
}
