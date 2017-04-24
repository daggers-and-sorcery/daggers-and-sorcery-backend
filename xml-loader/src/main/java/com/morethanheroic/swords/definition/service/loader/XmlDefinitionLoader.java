package com.morethanheroic.swords.definition.service.loader;

import com.morethanheroic.swords.definition.service.loader.domain.DefinitionLoadingContext;

import java.util.List;

/**
 * Blueprint for writing xml definition loaders.
 * @param <T> The type of the loadable target.
 */
public interface XmlDefinitionLoader<T> {

    <Z> List<Z> loadDefinitions(DefinitionLoadingContext definitionLoadingContext);
}
