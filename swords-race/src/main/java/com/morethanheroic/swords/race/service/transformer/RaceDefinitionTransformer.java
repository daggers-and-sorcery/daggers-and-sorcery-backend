package com.morethanheroic.swords.race.service.transformer;

import com.morethanheroic.swords.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.race.model.RaceDefinition;
import com.morethanheroic.swords.race.model.modifier.RacialModifier;
import com.morethanheroic.swords.race.model.modifier.entry.NumericRacialModifierEntry;
import com.morethanheroic.swords.race.model.modifier.entry.RacialModifierEntry;
import com.morethanheroic.swords.race.service.loader.entity.RawRaceDefinition;
import com.morethanheroic.swords.race.service.loader.entity.RawNumericRacialModifierEntry;
import com.morethanheroic.swords.race.service.loader.entity.RawRacialModifierEntry;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Transform a {@link RawRaceDefinition} to a {@link RaceDefinition}.
 */
@Service
public class RaceDefinitionTransformer implements DefinitionTransformer<RaceDefinition, RawRaceDefinition> {

    @Override
    public RaceDefinition transform(RawRaceDefinition rawRaceDefinition) {
        return RaceDefinition.builder()
                .name(rawRaceDefinition.getName())
                .race(buildRaceFromName(rawRaceDefinition.getName()))
                .racialModifierEntryMap(transformModifierList(rawRaceDefinition.getRacialModifierList()))
                .build();
    }

    private Race buildRaceFromName(String name) {
        return Race.valueOf(name.replace(' ', '_').toUpperCase(Locale.ENGLISH));
    }

    private Map<RacialModifier, RacialModifierEntry> transformModifierList(List<RawRacialModifierEntry> rawRacialModifierEntryList) {
        final Map<RacialModifier, RacialModifierEntry> result = new HashMap<>();

        if (rawRacialModifierEntryList == null) {
            return result;
        }

        for (RawRacialModifierEntry rawRacialModifierEntry : rawRacialModifierEntryList) {
            final RacialModifierEntry racialModifierEntry;

            if (rawRacialModifierEntry instanceof RawNumericRacialModifierEntry) {
                racialModifierEntry = new NumericRacialModifierEntry(rawRacialModifierEntry.getRacialModifier(), rawRacialModifierEntry.getValue());
            } else {
                throw new IllegalArgumentException("Unhandled RawRacialModifier: " + rawRacialModifierEntry.getClass() + "!");
            }

            result.put(rawRacialModifierEntry.getRacialModifier(), racialModifierEntry);
        }

        return result;
    }
}
