package com.morethanheroic.swords.combat.service;

import com.morethanheroic.localization.MessageResolver;
import com.morethanheroic.swords.combat.domain.CombatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CombatMessageFactory {

    private final MessageResolver messageResolver;

    public CombatMessage newMessage(final String icon, final String messageCode, final Object... data) {
        final CombatMessage combatMessage = newMessage(messageCode, data);

        combatMessage.addData("icon", icon);

        return combatMessage;
    }

    private CombatMessage newMessage(final String messageCode, final Object... data) {
        final CombatMessage combatMessage = new CombatMessage();

        combatMessage.addData("message", messageResolver.resolveMessage(messageCode, data));

        return combatMessage;
    }
}
