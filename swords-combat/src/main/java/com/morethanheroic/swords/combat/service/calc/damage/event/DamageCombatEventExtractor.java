package com.morethanheroic.swords.combat.service.calc.damage.event;

import com.morethanheroic.swords.combat.bonus.domain.CombatBonus;
import com.morethanheroic.swords.combat.step.domain.CombatStep;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageCombatEventResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DamageCombatEventExtractor {

    public List<CombatStep> extractCombatSteps(final List<DamageCombatEventResult> damageCombatEventResults) {
        return damageCombatEventResults.stream()
                .flatMap(damageEventCalculationResult -> damageEventCalculationResult.getCombatSteps().stream())
                .collect(Collectors.toList());
    }

    public CombatBonus extractBonusDamage(final List<DamageCombatEventResult> damageCombatEventResults) {
        return damageCombatEventResults.stream()
                .map(DamageCombatEventResult::getBonusDamage)
                .reduce(CombatBonus::add)
                .orElse(CombatBonus.EMPTY_COMBAT_BONUS);
    }
}
