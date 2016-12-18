package com.morethanheroic.swords.definition.service.loader;

import java.io.IOException;
import java.util.List;

/**
 * Blueprint for writing xml definition loaders.
 * @param <T> The type of the loadable target.
 */
public interface XmlDefinitionLoader<T> {

    //TODO: Define a loading context instead because target is not needed in every implementation.
    <Z> List<Z> loadDefinitions(Class<Z> clazz, String resourcePath, String schemaPath, T target) throws IOException;
}
