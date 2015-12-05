package com.morethanheroic.swords.definition.service.loader;

import java.io.IOException;
import java.util.List;

/**
 * Blueprint for writing xml definition loaders.
 */
public interface XmlDefinitionLoader<T> {

    List loadDefinitions(Class clazz, String resourcePath, String schemaPath, T target) throws IOException;
}
