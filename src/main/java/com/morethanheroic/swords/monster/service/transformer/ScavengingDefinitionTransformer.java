package com.morethanheroic.swords.monster.service.transformer;

import com.morethanheroic.swords.item.service.ItemDefinitionManager;
import com.morethanheroic.swords.monster.domain.ScavengingDefinition;
import com.morethanheroic.swords.monster.service.loader.domain.RawScavengingDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScavengingDefinitionTransformer {

    @Autowired
    private ItemDefinitionManager itemDefinitionManager;

    @Autowired
    private ScavengingAmountDefinitionTransformer scavengingAmountDefinitionTransformer;

    public ScavengingDefinition transform(RawScavengingDefinition rawScavengingDefinition) {
        ScavengingDefinition.ScavengeDefinitionBuilder scavengeDefinitionBuilder = new ScavengingDefinition.ScavengeDefinitionBuilder();

        scavengeDefinitionBuilder.setAmount(scavengingAmountDefinitionTransformer.transform(rawScavengingDefinition.getAmount()));
        scavengeDefinitionBuilder.setItem(itemDefinitionManager.getItemDefinition(rawScavengingDefinition.getItem()));
        scavengeDefinitionBuilder.setChance(rawScavengingDefinition.getChance());

        return scavengeDefinitionBuilder.build();
    }
}
