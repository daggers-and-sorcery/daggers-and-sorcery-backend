package com.morethanheroic.swords.definition.transformer;

/**
 * Transforms a given freshly loaded definition into a domain object.
 *
 * @param <T> The type of the domain object.
 * @param <K> The type of the loaded object.
 */
public interface DefinitionTransformer<T, K> {

    /**
     * Transform a freshly loaded raw definition into a domain object.
     *
     * @param rawDefinition the raw definition to transform
     * @return the new object
     */
    T transform(K rawDefinition);
}
