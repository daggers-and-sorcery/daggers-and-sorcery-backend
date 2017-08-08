package com.morethanheroic.swords.item.view.request.advice.domain;

import com.morethanheroic.swords.inventory.domain.IdentificationType;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import lombok.Builder;
import lombok.Getter;

/**
 * Contains all of the data of an item in the request. Used when we need the knowledge of identification type
 * and not just the item's definition.
 */
@Getter
@Builder
public class ItemRequestEntity {

    private final IdentificationType identificationType;
    private final ItemDefinition itemDefinition;
}
