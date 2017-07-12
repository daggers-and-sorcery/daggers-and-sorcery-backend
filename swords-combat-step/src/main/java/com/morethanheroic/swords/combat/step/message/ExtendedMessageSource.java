package com.morethanheroic.swords.combat.step.message;

import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.*;

public class ExtendedMessageSource extends ResourceBundleMessageSource {

    private final Map<Locale, ResourceBundle> resourceBundleCache = new HashMap<>();

    public boolean hasKey(final Locale locale, final String messageCode) {
        return getKeys(locale).contains(messageCode);
    }

    public Set<String> getKeys(final Locale locale) {
        if (!resourceBundleCache.containsKey(locale)) {
            resourceBundleCache.put(locale, getResourceBundle("messages", locale));
        }

        return resourceBundleCache.get(locale).keySet();
    }
}
