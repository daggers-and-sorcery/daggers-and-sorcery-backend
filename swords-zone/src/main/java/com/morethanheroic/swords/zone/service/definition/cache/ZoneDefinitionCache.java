package com.morethanheroic.swords.zone.service.definition.cache;

import com.morethanheroic.definition.cache.impl.MapBasedDefinitionCache;
import com.morethanheroic.swords.location.domain.Location;
import com.morethanheroic.swords.zone.domain.ExplorationZone;
import com.morethanheroic.swords.zone.domain.ZoneDefinition;
import com.morethanheroic.swords.zone.service.definition.loader.ZoneDefinitionLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ZoneDefinitionCache extends MapBasedDefinitionCache<ExplorationZone, ZoneDefinition> {

    protected ZoneDefinitionCache(final ZoneDefinitionLoader zoneDefinitionLoader) {
        super(
                zoneDefinitionLoader.loadDefinitions().stream()
                        .collect(Collectors.toMap(ZoneDefinition::getId, Function.identity()))
        );

        log.info("Loaded " + this.getSize() + " zone definitions.");
    }

    public List<ZoneDefinition> getDefinitionsOnLocation(final Location location) {
        return this.getDefinitions().stream()
                .filter(zoneDefinition -> zoneDefinition.getLocation() == location)
                .sorted(Comparator.comparingInt(ZoneDefinition::getMinimumLevel))
                .collect(Collectors.toList());
    }
}
