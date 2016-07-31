package com.morethanheroic.swords.combat.service;

import com.morethanheroic.swords.combat.domain.CombatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class CombatMessageFactory {

    private static final Locale DEFAULT_LOCALE = Locale.ENGLISH;

    private final MessageSource messageSource;

    public CombatMessage newMessage(final String icon, final String messageCode, final Object... data) {
        final CombatMessage combatMessage = newMessage(messageCode, data);

        combatMessage.addData("icon", icon);

        return combatMessage;
    }

    public CombatMessage newMessage(final String messageCode, final Object... data) {
        final CombatMessage combatMessage = new CombatMessage();

        combatMessage.addData("message", messageSource.getMessage(messageCode, data, DEFAULT_LOCALE));

        return combatMessage;
    }
}
