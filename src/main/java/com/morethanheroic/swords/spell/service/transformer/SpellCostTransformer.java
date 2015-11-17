package com.morethanheroic.swords.spell.service.transformer;

import com.morethanheroic.swords.spell.domain.SpellCost;
import com.morethanheroic.swords.spell.service.loader.domain.RawSpellCost;
import org.springframework.stereotype.Service;

@Service
public class SpellCostTransformer {

    public SpellCost transform(RawSpellCost rawSpellCost) {
        SpellCost.SpellCostBuilder spellCostBuilder = new SpellCost.SpellCostBuilder();

        spellCostBuilder.setId(rawSpellCost.getId());
        spellCostBuilder.setAmount(rawSpellCost.getAmount());
        spellCostBuilder.setType(rawSpellCost.getType());

        return spellCostBuilder.build();
    }
}
