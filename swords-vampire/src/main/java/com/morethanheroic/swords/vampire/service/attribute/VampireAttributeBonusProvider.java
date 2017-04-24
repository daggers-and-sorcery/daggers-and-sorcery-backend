package com.morethanheroic.swords.vampire.service.attribute;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.service.bonus.AttributeBonusProvider;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.AttributeCalculationResult;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.vampire.service.VampireCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Calculate (add) the +10 life bonus gained from being vampire.
 */
@Service
@RequiredArgsConstructor
public class VampireAttributeBonusProvider implements AttributeBonusProvider {

    private static final int VAMPIRE_BONUS = 10;

    private final VampireCalculator vampireCalculator;

    /**
     * Provide the bonus life points if the player is a vampire.
     *
     * @param userEntity the user we are generating the bonus for
     * @param attribute  the attribute we are generating the bonus for
     * @return the provided bonus, empty if no bonus is given
     */
    @Override
    public Optional<AttributeCalculationResult> calculateBonus(final UserEntity userEntity, final Attribute attribute) {
        if (attribute == CombatAttribute.LIFE && vampireCalculator.isVampire(userEntity)) {
            return Optional.of(new AttributeCalculationResult(VAMPIRE_BONUS, CombatAttribute.LIFE));
        }

        return Optional.empty();
    }
}
