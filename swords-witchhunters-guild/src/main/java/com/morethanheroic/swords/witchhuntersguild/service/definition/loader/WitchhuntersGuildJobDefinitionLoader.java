package com.morethanheroic.swords.witchhuntersguild.service.definition.loader;

import com.google.common.collect.ImmutableList;
import com.morethanheroic.definition.loader.DefinitionLoader;
import com.morethanheroic.swords.definition.service.loader.NumericXmlDefinitionLoader;
import com.morethanheroic.swords.definition.service.loader.domain.NumericDefinitionLoadingContext;
import com.morethanheroic.swords.witchhuntersguild.domain.definition.WitchhuntersGuildJobDefinition;
import com.morethanheroic.swords.witchhuntersguild.service.definition.loader.domain.RawWitchhuntersGuildJobDefinition;
import com.morethanheroic.swords.witchhuntersguild.service.definition.transformer.WitchhuntersGuildJobDefinitionTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class WitchhuntersGuildJobDefinitionLoader implements DefinitionLoader<WitchhuntersGuildJobDefinition> {

    private static final String WITCHHUNTERS_GUILD_JOB_DEFINITION_LOCATION = "classpath:data/witchhunters-guild/job/definition/";
    private static final String WITCHHUNTERS_GUILD_JOB_SCHEMA_LOCATION = "classpath:data/witchhunters-guild/job/schema.xsd";

    private final WitchhuntersGuildJobDefinitionTransformer witchhuntersGuildJobDefinitionTransformer;
    private final NumericXmlDefinitionLoader numericXmlDefinitionLoader;

    @Override
    public List<WitchhuntersGuildJobDefinition> loadDefinitions() {
        return loadRawWitchhuntersGuildJobDefinitions().stream()
                .map(witchhuntersGuildJobDefinitionTransformer::transform)
                .collect(collectingAndThen(toList(), ImmutableList::copyOf));
    }

    private List<RawWitchhuntersGuildJobDefinition> loadRawWitchhuntersGuildJobDefinitions() {
        return numericXmlDefinitionLoader.loadDefinitions(
                NumericDefinitionLoadingContext.<RawWitchhuntersGuildJobDefinition>builder()
                        .clazz(RawWitchhuntersGuildJobDefinition.class)
                        .resourcePath(WITCHHUNTERS_GUILD_JOB_DEFINITION_LOCATION)
                        .schemaPath(WITCHHUNTERS_GUILD_JOB_SCHEMA_LOCATION)
                        .build()
        );
    }
}
