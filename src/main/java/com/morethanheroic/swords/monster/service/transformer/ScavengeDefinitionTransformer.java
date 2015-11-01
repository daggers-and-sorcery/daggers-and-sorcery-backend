package com.morethanheroic.swords.monster.service.transformer;

import com.morethanheroic.swords.item.service.ItemDefinitionManager;
import com.morethanheroic.swords.monster.domain.ScavengingDefinition;
import com.morethanheroic.swords.monster.service.loader.domain.RawScavengeDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScavengeDefinitionTransformer {

    @Autowired
    private ItemDefinitionManager itemDefinitionManager;

    public ScavengingDefinition transform(RawScavengeDefinition rawScavengeDefinition) {
        ScavengingDefinition.ScavengeDefinitionBuilder scavengeDefinitionBuilder = new ScavengingDefinition.ScavengeDefinitionBuilder();

        scavengeDefinitionBuilder.setAmount(rawScavengeDefinition.getAmount());
        scavengeDefinitionBuilder.setItem(itemDefinitionManager.getItemDefinition(rawScavengeDefinition.getItem()));
        scavengeDefinitionBuilder.setChance(rawScavengeDefinition.getChance());

        return scavengeDefinitionBuilder.build();
    }
}
