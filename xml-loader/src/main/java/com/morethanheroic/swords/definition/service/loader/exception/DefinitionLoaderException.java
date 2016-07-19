package com.morethanheroic.swords.definition.service.loader.exception;

/**
 * Exception thrown when an error happens while loading a definition.
 */
public class DefinitionLoaderException extends RuntimeException {

    public DefinitionLoaderException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
