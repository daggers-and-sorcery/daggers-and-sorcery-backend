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

        rawStatusEffectBasicModifierDefinition.setModifier(statusEffectModifierDefinitionPlaceholder.getModifier());
        rawStatusEffectBasicModifierDefinition.setAmount(statusEffectModifierDefinitionPlaceholder.getAmount());
        rawStatusEffectBasicModifierDefinition.setD2(statusEffectModifierDefinitionPlaceholder.getD2());
        rawStatusEffectBasicModifierDefinition.setD4(statusEffectModifierDefinitionPlaceholder.getD4());
        rawStatusEffectBasicModifierDefinition.setD6(statusEffectModifierDefinitionPlaceholder.getD6());
        rawStatusEffectBasicModifierDefinition.setD8(statusEffectModifierDefinitionPlaceholder.getD8());
        rawStatusEffectBasicModifierDefinition.setD10(statusEffectModifierDefinitionPlaceholder.getD10());

        return rawStatusEffectBasicModifierDefinition;
    }

    @Override
    public StatusEffectModifierDefinitionPlaceholder marshal(RawStatusEffectModifierDefinition v) throws Exception {
        throw new UnsupportedOperationException("Marshalling is not supported for status effect modifier entities.");
    }
}
