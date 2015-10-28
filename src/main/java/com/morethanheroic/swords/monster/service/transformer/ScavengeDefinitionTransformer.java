package com.morethanheroic.swords.monster.service.transformer;

import com.morethanheroic.swords.monster.domain.ScavengeDefinition;
import com.morethanheroic.swords.monster.service.loader.domain.RawScavengeDefinition;
import org.springframework.stereotype.Service;

@Service
public class ScavengeDefinitionTransformer {

    public ScavengeDefinition transform(RawScavengeDefinition rawScavengeDefinition) {
        ScavengeDefinition.ScavengeDefinitionBuilder scavengeDefinitionBuilder = new ScavengeDefinition.ScavengeDefinitionBuilder();

        scavengeDefinitionBuilder.setAmount(rawScavengeDefinition.getAmount());
        scavengeDefinitionBuilder.setItem(rawScavengeDefinition.getItem());
        scavengeDefinitionBuilder.setChance(rawScavengeDefinition.getChance());

        return scavengeDefinitionBuilder.build();
    }
}
