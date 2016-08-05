package com.morethanheroic.swords.shop.view.response.domain.buy;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.swords.item.view.response.service.domain.response.ItemModifierPartialResponse;
import com.morethanheroic.swords.item.view.response.service.domain.response.ItemRequirementPartialResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ShopBuyItemPartialResponse extends PartialResponse {

    private final int id;
    private final int price;
    private final int amount;
    private final String name;
    private final String type;
    private final String subtype;
    private final double weight;
    private final boolean usable;
    private final boolean equipment;
    private final String flavour;
    private final String description;
    private final List<? extends ItemModifierPartialResponse> modifiers;
    private final List<? extends ItemRequirementPartialResponse> requirements;
}
