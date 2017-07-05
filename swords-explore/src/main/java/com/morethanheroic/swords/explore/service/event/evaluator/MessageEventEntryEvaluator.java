package com.morethanheroic.swords.explore.service.event.evaluator;

import com.morethanheroic.swords.explore.domain.event.result.ExplorationEventEntryResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.TextExplorationEventEntryResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class MessageEventEntryEvaluator {

    private static final Locale DEFAULT_LOCALE = Locale.ENGLISH;

    private final MessageSource messageSource;

    public ExplorationEventEntryResult messageEntry(final String messageCode, Object... args) {
        return TextExplorationEventEntryResult.builder()
                .content(messageSource.getMessage(messageCode, args, DEFAULT_LOCALE))
                .build();
    }
}
