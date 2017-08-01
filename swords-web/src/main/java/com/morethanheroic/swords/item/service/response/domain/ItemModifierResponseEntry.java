package com.morethanheroic.swords.item.service.response.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.morethanheroic.swords.item.domain.modifier.ItemModifier;
import lombok.RequiredArgsConstructor;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@RequiredArgsConstructor
public class ItemModifierResponseEntry {

    private final ItemModifier attribute;
    private final int value;
}
