package com.morethanheroic.swords.zone.service.definition.cache;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.morethanheroic.swords.definition.cache.impl.MapBasedDefinitionCache;
import com.morethanheroic.swords.location.domain.Location;
import com.morethanheroic.swords.zone.domain.ExplorationZone;
import com.morethanheroic.swords.zone.domain.ZoneDefinition;
import com.morethanheroic.swords.zone.service.definition.loader.ZoneDefinitionLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
                .collect(Collectors.toList());
    }
}
