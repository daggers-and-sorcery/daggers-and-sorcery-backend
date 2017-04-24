package com.morethanheroic.swords.vampire.service.shop.price;

import com.morethanheroic.swords.shop.service.price.modifier.domain.ItemPriceModificationContext;
import com.morethanheroic.swords.shop.service.price.modifier.domain.ItemPriceModifier;
import com.morethanheroic.swords.shop.service.price.modifier.domain.ItemPriceModifierType;
import com.morethanheroic.swords.shop.service.price.modifier.provider.ItemSellPriceModifierProvider;
import com.morethanheroic.swords.vampire.service.VampireCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Provides the item sell price modification when the user is a vampire.
 */
@Service
@RequiredArgsConstructor
public class VampireItemSellPriceModifierProvider implements ItemSellPriceModifierProvider {

    private static final double VAMPIRE_PRICE_MODIFIER_VALUE = -0.25;

    private final VampireCalculator vampireCalculator;

    @Override
    public Optional<ItemPriceModifier> calculateModification(ItemPriceModificationContext itemPriceModificationContext) {
        if (vampireCalculator.isVampire(itemPriceModificationContext.getUserEntity())) {
            return Optional.of(
                    ItemPriceModifier.builder()
                            .modifierType(ItemPriceModifierType.PERCENTAGE)
                            .value(VAMPIRE_PRICE_MODIFIER_VALUE)
                            .build()
            );
        }

        return Optional.empty();
    }
}
