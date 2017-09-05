package com.morethanheroic.swords.combat.service.calc.damage.event.before;

import com.morethanheroic.swords.combat.bonus.domain.CombatBonus;
import com.morethanheroic.swords.combat.step.domain.CombatStep;
import com.morethanheroic.swords.combat.service.event.damage.before.domain.BeforeDamageCombatEventResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BeforeDamageCombatEventExtractor {

    public List<CombatStep> extractCombatSteps(final List<BeforeDamageCombatEventResult> beforeDamageCombatEventResults) {
        return beforeDamageCombatEventResults.stream()
                .flatMap(damageEventCalculationResult -> damageEventCalculationResult.getCombatSteps().stream())
                .collect(Collectors.toList());
    }

    public CombatBonus extractBonusDamage(final List<BeforeDamageCombatEventResult> beforeDamageCombatEventResults) {
        return beforeDamageCombatEventResults.stream()
                .map(BeforeDamageCombatEventResult::getBonusDamage)
                .reduce(CombatBonus::add)
                .orElse(CombatBonus.EMPTY_COMBAT_BONUS);
    }
}
