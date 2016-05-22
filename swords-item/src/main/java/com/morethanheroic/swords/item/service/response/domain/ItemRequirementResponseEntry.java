package com.morethanheroic.swords.item.service.response.domain;

import com.morethanheroic.swords.item.domain.ItemRequirement;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Hold the requirements of equipping an item.
 */
@Getter
@RequiredArgsConstructor
public class ItemRequirementResponseEntry {

    private final ItemRequirement attribute;
    private final int value;
}
