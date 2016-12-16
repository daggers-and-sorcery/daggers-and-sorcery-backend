package com.morethanheroic.swords.shop.service.price.domain;

import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

/**
 * Contains the context of the item calculation.
 */
@Getter
@Builder
public class ItemPriceCalculationContext {

    private final UserEntity userEntity;
    private final ItemDefinition itemDefinition;
}
