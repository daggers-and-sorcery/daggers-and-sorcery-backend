package com.morethanheroic.swords.witchhuntersguild.service.definition.transformer;

import com.morethanheroic.swords.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.monster.service.cache.MonsterDefinitionCache;
import com.morethanheroic.swords.witchhuntersguild.domain.definition.WitchhuntersGuildJobDefinition;
import com.morethanheroic.swords.witchhuntersguild.domain.definition.requirement.WitchhuntersGuildJobItemRequirement;
import com.morethanheroic.swords.witchhuntersguild.domain.definition.requirement.WitchhuntersGuildJobKillRequirement;
import com.morethanheroic.swords.witchhuntersguild.domain.definition.requirement.WitchhuntersGuildJobRequirement;
import com.morethanheroic.swords.witchhuntersguild.service.definition.loader.domain.RawWitchhuntersGuildJobDefinition;
import com.morethanheroic.swords.witchhuntersguild.service.definition.loader.domain.RawWitchhuntersGuildJobRequirement;
import com.morethanheroic.swords.witchhuntersguild.service.definition.loader.domain.WitchhuntersGuildJobRequirementType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WitchhuntersGuildJobDefinitionTransformer implements DefinitionTransformer<WitchhuntersGuildJobDefinition, RawWitchhuntersGuildJobDefinition> {

    private final ItemDefinitionCache itemDefinitionCache;
    private final MonsterDefinitionCache monsterDefinitionCache;

    @Override
    public WitchhuntersGuildJobDefinition transform(final RawWitchhuntersGuildJobDefinition rawWitchhuntersGuildJobDefinition) {
        return WitchhuntersGuildJobDefinition.builder()
                .id(rawWitchhuntersGuildJobDefinition.getId())
                .name(rawWitchhuntersGuildJobDefinition.getName())
                .description(rawWitchhuntersGuildJobDefinition.getDescription())
                .reward(rawWitchhuntersGuildJobDefinition.getReward())
                .requirements(transformRequirement(rawWitchhuntersGuildJobDefinition.getRequirements()))
                .build();
    }

    private List<WitchhuntersGuildJobRequirement> transformRequirement(final List<RawWitchhuntersGuildJobRequirement> rawWitchhuntersGuildJobRequirements) {
        return rawWitchhuntersGuildJobRequirements.stream()
                .map(rawWitchhuntersGuildJobRequirement -> {
                    if (rawWitchhuntersGuildJobRequirement.getType() == WitchhuntersGuildJobRequirementType.ITEM) {
                        return WitchhuntersGuildJobItemRequirement.builder()
                                .item(itemDefinitionCache.getDefinition(rawWitchhuntersGuildJobRequirement.getId()))
                                .amount(rawWitchhuntersGuildJobRequirement.getAmount())
                                .build();
                    } else if (rawWitchhuntersGuildJobRequirement.getType() == WitchhuntersGuildJobRequirementType.KILL) {
                        return WitchhuntersGuildJobKillRequirement.builder()
                                .monster(monsterDefinitionCache.getDefinition(rawWitchhuntersGuildJobRequirement.getId()))
                                .amount(rawWitchhuntersGuildJobRequirement.getAmount())
                                .build();
                    } else {
                        throw new RuntimeException("Unknown type wile loading Witchhunter's Guild jobs: " + rawWitchhuntersGuildJobRequirement.getType() + "!");
                    }
                })
                .collect(Collectors.toList());
    }
}
