package com.morethanheroic.swords.combat.service.calc.damage.event;

import com.morethanheroic.swords.combat.domain.step.CombatStep;
import com.morethanheroic.swords.combat.service.event.damage.domain.DamageEventCalculationResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DamageEventCombatStepExtractor {

    public List<CombatStep> extractCombatSteps(final List<DamageEventCalculationResult> damageEventCalculationResults) {
        return damageEventCalculationResults.stream()
                .flatMap(damageEventCalculationResult -> damageEventCalculationResult.getCombatSteps().stream())
                .collect(Collectors.toList());
    }
}
