package com.morethanheroic.swords.explore.service.event.evaluator;

import com.morethanheroic.swords.explore.domain.event.result.ExplorationEventEntryResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.TextExplorationEventEntryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageEventEntryEvaluator {

    private static final Locale DEFAULT_LOCALE = Locale.ENGLISH;

    @Autowired
    private MessageSource messageSource;

    public ExplorationEventEntryResult messageEntry(final String messageCode, Object... args) {
        return TextExplorationEventEntryResult.builder()
                .content(messageSource.getMessage(messageCode, args, DEFAULT_LOCALE))
                .build();
    }
}
