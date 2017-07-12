package com.morethanheroic.swords.shop.domain;

import com.morethanheroic.swords.item.domain.ItemDefinition;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@Builder
public class AvailableItem {

    private final ItemDefinition item;
}
