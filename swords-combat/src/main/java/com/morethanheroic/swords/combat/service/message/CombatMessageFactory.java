package com.morethanheroic.swords.combat.service.message;

import com.morethanheroic.localization.MessageResolver;
import com.morethanheroic.swords.combat.domain.CombatMessage;
import com.morethanheroic.swords.combat.service.message.domain.CombatMessageContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CombatMessageFactory {

    private static final Locale DEFAULT_LOCALE = Locale.ENGLISH;

    private final MessageResolver messageResolver;
    private final ExtendedMessageSource extendedMessageSource;

    public CombatMessage newMessage(final String icon, final String messageCode, final Object... data) {
        final CombatMessage combatMessage = newMessage(messageCode, CombatMessageContext.builder().build(), data);

        combatMessage.addData("icon", icon);

        return combatMessage;
    }

    public CombatMessage newMessage(final String icon, final String messageCode, final CombatMessageContext combatMessageContext, final Object... data) {
        final CombatMessage combatMessage = newMessage(messageCode, combatMessageContext, data);

        combatMessage.addData("icon", icon);

        return combatMessage;
    }

    private CombatMessage newMessage(final String messageCode, final CombatMessageContext combatMessageContext, final Object... data) {
        final CombatMessage combatMessage = new CombatMessage();

        combatMessage.addData("message", messageResolver.resolveMessage(calculateMessageCode(messageCode, combatMessageContext), data));

        return combatMessage;
    }

    private String calculateMessageCode(final String messageCode, final CombatMessageContext combatMessageContext) {
        /*
         message_type_subtype_attacktype_weaptype
         message_type_subtype_attacktype
         message_type_subtype_weaptype
         message_type_attacktype_weaptype
         message_type_attacktype
         message_type_weaptype
         message_subtype_attacktype
         message_subtype_weaptype
         message_attacktype
         message_weaptype
         */

        if (combatMessageContext.getType() != null && combatMessageContext.getSubtype() != null
                && extendedMessageSource.hasKey(DEFAULT_LOCALE, messageCode + "_WHEN_" + combatMessageContext.getType().name() + "_" + combatMessageContext.getSubtype().name())) {
            return messageCode + "_WHEN_" + combatMessageContext.getType().name() + "_" + combatMessageContext.getSubtype().name();
        }

        return messageCode;
    }
}
