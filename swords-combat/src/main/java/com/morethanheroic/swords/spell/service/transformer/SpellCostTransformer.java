package com.morethanheroic.swords.spell.service.transformer;

import com.morethanheroic.swords.spell.domain.SpellCost;
import com.morethanheroic.swords.spell.service.loader.domain.RawSpellCost;
import org.springframework.stereotype.Service;

@Service
public class SpellCostTransformer {

    public SpellCost transform(final RawSpellCost rawSpellCost) {
        return SpellCost.builder()
                .id(rawSpellCost.getId())
                .amount(rawSpellCost.getAmount())
                .type(rawSpellCost.getType())
                .build();
    }
}
