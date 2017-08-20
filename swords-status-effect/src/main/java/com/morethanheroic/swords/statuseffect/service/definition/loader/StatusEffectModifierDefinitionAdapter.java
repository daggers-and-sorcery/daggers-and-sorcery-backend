package com.morethanheroic.swords.statuseffect.service.definition.loader;

import com.morethanheroic.swords.statuseffect.service.definition.loader.domain.RawStatusEffectBasicModifierDefinition;
import com.morethanheroic.swords.statuseffect.service.definition.loader.domain.RawStatusEffectCustomModifierDefinition;
import com.morethanheroic.swords.statuseffect.service.definition.loader.domain.RawStatusEffectModifierDefinition;
import com.morethanheroic.swords.statuseffect.service.definition.loader.domain.StatusEffectModifierDefinitionPlaceholder;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class StatusEffectModifierDefinitionAdapter extends XmlAdapter<StatusEffectModifierDefinitionPlaceholder, RawStatusEffectModifierDefinition> {

    @Override
    public RawStatusEffectModifierDefinition unmarshal(StatusEffectModifierDefinitionPlaceholder statusEffectModifierDefinitionPlaceholder) throws Exception {
        if (statusEffectModifierDefinitionPlaceholder.getEffectId() != null) {
            final RawStatusEffectCustomModifierDefinition rawStatusEffectCustomModifierDefinition = new RawStatusEffectCustomModifierDefinition();

            rawStatusEffectCustomModifierDefinition.setEffectId(statusEffectModifierDefinitionPlaceholder.getEffectId());

            return rawStatusEffectCustomModifierDefinition;
        }

        final RawStatusEffectBasicModifierDefinition rawStatusEffectBasicModifierDefinition = new RawStatusEffectBasicModifierDefinition();

        rawStatusEffectBasicModifierDefinition.setAmount(rawStatusEffectBasicModifierDefinition.getAmount());
        rawStatusEffectBasicModifierDefinition.setD2(rawStatusEffectBasicModifierDefinition.getD2());
        rawStatusEffectBasicModifierDefinition.setD4(rawStatusEffectBasicModifierDefinition.getD4());
        rawStatusEffectBasicModifierDefinition.setD6(rawStatusEffectBasicModifierDefinition.getD6());
        rawStatusEffectBasicModifierDefinition.setD8(rawStatusEffectBasicModifierDefinition.getD8());
        rawStatusEffectBasicModifierDefinition.setD10(rawStatusEffectBasicModifierDefinition.getD10());

        return rawStatusEffectBasicModifierDefinition;
    }

    @Override
    public StatusEffectModifierDefinitionPlaceholder marshal(RawStatusEffectModifierDefinition v) throws Exception {
        throw new UnsupportedOperationException("Marshalling is not supported for status effect modifier entities.");
    }
}
