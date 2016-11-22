package com.morethanheroic.swords.loot.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Builder
public class LootDefinition {

    private final int id;
    private final List<DropDefinition> dropDefinitions;
}
