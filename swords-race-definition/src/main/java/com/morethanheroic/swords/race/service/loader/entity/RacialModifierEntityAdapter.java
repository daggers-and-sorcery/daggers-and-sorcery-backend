package com.morethanheroic.swords.race.service.loader.entity;

import com.morethanheroic.swords.race.model.modifier.RacialModifier;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Delegate the unmarshalling of elements into {@link RawRacialModifierEntry}.
 */
public class RacialModifierEntityAdapter extends XmlAdapter<RacialModifierEntityAdapterPlaceholder, RawRacialModifierEntry> {

    @Override
    public RawRacialModifierEntry unmarshal(final RacialModifierEntityAdapterPlaceholder racialModifierEntityAdapterPlaceholder) throws Exception {
        return RawNumericRacialModifierEntry.builder()
                .racialModifier(RacialModifier.valueOf(racialModifierEntityAdapterPlaceholder.getType()))
                .value((int) racialModifierEntityAdapterPlaceholder.getValue())
                .build();
    }

    @Override
    public RacialModifierEntityAdapterPlaceholder marshal(RawRacialModifierEntry element) throws Exception {
        throw new UnsupportedOperationException("Marshalling is not supported for racial modifier entities.");
    }
}
