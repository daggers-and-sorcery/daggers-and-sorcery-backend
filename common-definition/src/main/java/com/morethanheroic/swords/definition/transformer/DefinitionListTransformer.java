package com.morethanheroic.swords.definition.transformer;

import java.util.List;

/**
 * Transforms a given freshly loaded definition list into a domain object.
 *
 * @param <T> The type of the domain object.
 * @param <K> The type of the loaded object.
 */
public interface DefinitionListTransformer<T extends List, K extends List> {

    T transform(K rawDefinition);
}
