package com.morethanheroic.swords.combat.service.calc.drop;

import com.morethanheroic.math.PercentageCalculator;
import com.morethanheroic.swords.combat.domain.Drop;
import com.morethanheroic.swords.combat.service.calc.drop.domain.DropSplitCalculationResult;
import com.morethanheroic.swords.loot.domain.DropAmountDefinition;
import com.morethanheroic.swords.loot.domain.DropDefinition;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Calculate drops for various kinds of things.
 */
@Service
@RequiredArgsConstructor
public class DropCalculator {

    private static final int MAXIMUM_DROP_PERCENTAGE = 100;

    private final Random random = new Random();
    private final PercentageCalculator percentageCalculator;

    public List<Drop> calculateDropsForMonster(MonsterDefinition monster) {
        return calculateDrops(monster.getDropDefinitions());
    }

    public List<Drop> calculateDrops(final List<DropDefinition> dropDefinitions) {
        final ArrayList<Drop> result = new ArrayList<>();

        for (DropDefinition drop : dropDefinitions) {
            if (MAXIMUM_DROP_PERCENTAGE * random.nextDouble() < drop.getChance()) {
                result.add(new Drop(drop.getItem(), calculateDropAmount(drop.getAmount()), drop.isIdentified()));
            }
        }

        return Collections.unmodifiableList(result);
    }

    /**
     * Split a drop to two separate lists based on a percentage.
     *
     * @param drops               the drops to split
     * @param splittingPercentage the percentage to use for calculating, if the roll is lower or equal than this
     *                            percentage then its added to the success result otherwise its added to the failed
     *                            result
     * @param safeSplitting       if safe splitting is enabled the failed column will have at least one element added
     *                            every time
     * @return the result of the calculation
     */
    public DropSplitCalculationResult splitDrops(final List<Drop> drops, final int splittingPercentage, final boolean safeSplitting) {
        final List<Drop> successfulResult = new ArrayList<>();
        final List<Drop> failedResult = new ArrayList<>();

        for (Drop drop : drops) {
            if (percentageCalculator.calculatePercentageHit(splittingPercentage)) {
                successfulResult.add(drop);
            } else {
                failedResult.add(drop);
            }
        }

        if (safeSplitting && failedResult.size() == 0 && successfulResult.size() > 1) {
            final Drop removedDrop = successfulResult.remove(1);

            failedResult.add(removedDrop);
        }

        return DropSplitCalculationResult.builder()
                .successfulResult(successfulResult)
                .failedResult(failedResult)
                .build();
    }

    private int calculateDropAmount(DropAmountDefinition dropAmountDefinition) {
        if (dropAmountDefinition.getMinimumAmount() == dropAmountDefinition.getMaximumAmount()) {
            return dropAmountDefinition.getMinimumAmount();
        }

        return random.nextInt(dropAmountDefinition.getMaximumAmount() - dropAmountDefinition.getMinimumAmount())
                + dropAmountDefinition.getMinimumAmount();
    }
}
