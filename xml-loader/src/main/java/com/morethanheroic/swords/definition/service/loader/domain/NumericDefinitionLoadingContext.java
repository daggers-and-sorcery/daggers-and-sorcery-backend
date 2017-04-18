package com.morethanheroic.swords.definition.service.loader.domain;

import lombok.Builder;
import lombok.Getter;

/**
 * Used when loading definitions based on an numeric id using {@link com.morethanheroic.swords.definition.service.loader.EnumXmlDefinitionLoader}.
 */
@Builder
@Getter
public class NumericDefinitionLoadingContext<T> implements DefinitionLoadingContext {

    private final Class<T> clazz;
    private final String resourcePath;
    private final String schemaPath;
}
