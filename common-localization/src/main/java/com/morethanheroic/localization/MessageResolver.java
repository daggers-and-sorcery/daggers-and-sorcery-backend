package com.morethanheroic.localization;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class MessageResolver {

    private static final Locale DEFAULT_LOCALE = Locale.ENGLISH;

    private final MessageSource messageSource;

    public String resolveMessage(final String messageId, final Object... args) {
        return messageSource.getMessage(messageId, args, DEFAULT_LOCALE);
    }
}
