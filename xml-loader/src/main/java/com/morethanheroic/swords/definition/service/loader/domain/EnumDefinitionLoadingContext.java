package com.morethanheroic.swords.definition.service.loader.domain;

import lombok.Builder;
import lombok.Getter;

/**
 * Used when loading definitions based on an enum using {@link com.morethanheroic.swords.definition.service.loader.EnumXmlDefinitionLoader}.
 */
@Builder
@Getter
public class EnumDefinitionLoadingContext<T> implements DefinitionLoadingContext {

    @Getter
    private final Class<T> clazz;

    @Getter
    private final String resourcePath;

    @Getter
    private final String schemaPath;

    @Getter
    private final Class<? extends Enum> target;
}
