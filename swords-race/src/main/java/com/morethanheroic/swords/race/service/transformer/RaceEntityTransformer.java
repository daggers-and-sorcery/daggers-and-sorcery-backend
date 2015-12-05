package com.morethanheroic.swords.race.service.transformer;

import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.race.model.RaceEntity;
import com.morethanheroic.swords.race.model.modifier.RacialModifier;
import com.morethanheroic.swords.race.model.modifier.entry.NumericRacialModifierEntry;
import com.morethanheroic.swords.race.model.modifier.entry.RacialModifierEntry;
import com.morethanheroic.swords.race.service.loader.entity.RawRaceEntity;
import com.morethanheroic.swords.race.service.loader.entity.modifier.entry.RawNumericRacialModifierEntry;
import com.morethanheroic.swords.race.service.loader.entity.modifier.entry.RawRacialModifierEntry;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Transform a {@link RawRaceEntity} to a {@link RaceEntity}.
 */
@Service
public class RaceEntityTransformer {

    public RaceEntity transform(RawRaceEntity rawRaceEntity) {
        return RaceEntity.builder()
                .name(rawRaceEntity.getName())
                .race(buildRaceFromName(rawRaceEntity.getName()))
                .racialModifierEntryMap(transformModifierList(rawRaceEntity.getRacialModifierList()))
                .build();
    }

    private Race buildRaceFromName(String name) {
        return Race.valueOf(name.replace(' ', '_').toUpperCase());
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
