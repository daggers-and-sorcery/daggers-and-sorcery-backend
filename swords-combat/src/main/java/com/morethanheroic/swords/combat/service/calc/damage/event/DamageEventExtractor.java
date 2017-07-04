package com.morethanheroic.swords.combat.service.calc.damage.event;

import com.morethanheroic.swords.combat.bonus.domain.CombatBonus;
import com.morethanheroic.swords.combat.domain.step.CombatStep;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageEventCalculationResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DamageEventExtractor {

    private final static CombatBonus EMPTY_COMBAT_BONUS = CombatBonus.builder().build();

    public List<CombatStep> extractCombatSteps(final List<DamageEventCalculationResult> damageEventCalculationResults) {
        return damageEventCalculationResults.stream()
                .flatMap(damageEventCalculationResult -> damageEventCalculationResult.getCombatSteps().stream())
                .collect(Collectors.toList());
    }

    public CombatBonus extractBonusDamage(final List<DamageEventCalculationResult> damageEventCalculationResults) {
        return damageEventCalculationResults.stream()
                .map(DamageEventCalculationResult::getBonusDamage)
                .reduce(CombatBonus::add)
                .orElse(EMPTY_COMBAT_BONUS);
    }
}
