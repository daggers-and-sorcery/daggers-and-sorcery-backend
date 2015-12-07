package com.morethanheroic.swords.definition.loader;

import java.io.IOException;
import java.util.List;

/**
 * Gives a standardized API for the definition loaders. You can use definition loaders to load XML definitions into domain objects.
 *
 * @param <T> The type of the object the loader is loading.
 */
public interface DefinitionLoader<T> {

    List<T> loadDefinitions() throws IOException;
}
