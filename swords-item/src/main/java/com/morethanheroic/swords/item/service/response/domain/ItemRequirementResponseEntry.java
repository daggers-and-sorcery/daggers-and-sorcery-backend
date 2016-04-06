package com.morethanheroic.swords.item.service.response.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.morethanheroic.swords.item.domain.ItemRequirement;
import lombok.RequiredArgsConstructor;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@RequiredArgsConstructor
public class ItemRequirementResponseEntry {

    private final ItemRequirement attribute;
    private final int value;
}
