package com.morethanheroic.swords.zone.service.definition.loader;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.morethanheroic.definition.loader.DefinitionLoader;
import com.morethanheroic.swords.definition.service.loader.EnumXmlDefinitionLoader;
import com.morethanheroic.swords.definition.service.loader.domain.EnumDefinitionLoadingContext;
import com.morethanheroic.swords.zone.domain.ExplorationZone;
import com.morethanheroic.swords.zone.domain.ZoneDefinition;
import com.morethanheroic.swords.zone.service.definition.loader.domain.RawZoneDefinition;
import com.morethanheroic.swords.zone.service.definition.transformer.ZoneDefinitionTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ZoneDefinitionLoader implements DefinitionLoader<ZoneDefinition> {

    private final EnumXmlDefinitionLoader enumXmlDefinitionLoader;
    private final ZoneDefinitionTransformer zoneDefinitionTransformer;

    @Override
    public List<ZoneDefinition> loadDefinitions() {
        return loadRawZoneEntities().stream()
                .map(zoneDefinitionTransformer::transform)
                .collect(
                        collectingAndThen(toList(), ImmutableList::copyOf)
                );
    }


    private List<RawZoneDefinition> loadRawZoneEntities() {
        return enumXmlDefinitionLoader.loadDefinitions(
                EnumDefinitionLoadingContext.<RawZoneDefinition>builder()
                        .clazz(RawZoneDefinition.class)
                        .resourcePath("classpath:data/zone/definition/")
                        .schemaPath("classpath:data/zone/schema.xsd")
                        .target(ExplorationZone.class)
                        .build()
        );
    }
}
