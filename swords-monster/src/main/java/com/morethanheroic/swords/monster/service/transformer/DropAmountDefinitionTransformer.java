package com.morethanheroic.swords.monster.service.transformer;

import com.morethanheroic.swords.monster.domain.DropAmountDefinition;
import com.morethanheroic.swords.monster.service.loader.domain.RawDropAmountDefinition;
import org.springframework.stereotype.Service;

@Service
public class DropAmountDefinitionTransformer {

    public DropAmountDefinition transform(RawDropAmountDefinition rawDropAmountDefinition) {
        return DropAmountDefinition.builder()
                .minimumAmount(rawDropAmountDefinition.getMin())
                .maximumAmount(rawDropAmountDefinition.getMax())
                .build();
    }
}
