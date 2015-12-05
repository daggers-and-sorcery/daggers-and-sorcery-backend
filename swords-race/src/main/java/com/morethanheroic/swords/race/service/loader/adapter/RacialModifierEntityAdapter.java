package com.morethanheroic.swords.race.service.loader.adapter;

import com.morethanheroic.swords.race.model.modifier.RacialModifier;
import com.morethanheroic.swords.race.service.loader.entity.modifier.entry.RawNumericRacialModifierEntry;
import com.morethanheroic.swords.race.service.loader.entity.modifier.entry.RawRacialModifierEntry;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Delegate the unmarshalling of elements into {@link RawRacialModifierEntry}.
 */
public class RacialModifierEntityAdapter extends XmlAdapter<RacialModifierEntityAdapterPlaceholder, RawRacialModifierEntry> {

    @Override
    public RawRacialModifierEntry unmarshal(RacialModifierEntityAdapterPlaceholder element) throws Exception {
        return RawNumericRacialModifierEntry.builder()
                .racialModifier(RacialModifier.valueOf(element.getType()))
                .value(Integer.parseInt(element.getValue()))
                .build();
    }

    @Override
    public RacialModifierEntityAdapterPlaceholder marshal(RawRacialModifierEntry element) throws Exception {
        throw new UnsupportedOperationException("Marshalling is not supported for racial modifier entities.");
    }
}
