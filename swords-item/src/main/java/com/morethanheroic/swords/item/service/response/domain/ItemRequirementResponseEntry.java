package com.morethanheroic.swords.item.service.response.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Hold the requirements of equipping an item.
 */
@Getter
@RequiredArgsConstructor
public class ItemRequirementResponseEntry {

    private final String attribute;
    private final int value;
}
