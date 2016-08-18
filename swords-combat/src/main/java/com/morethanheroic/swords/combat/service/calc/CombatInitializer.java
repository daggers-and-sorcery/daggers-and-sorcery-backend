package com.morethanheroic.swords.combat.service.calc;

import com.morethanheroic.swords.journal.model.JournalType;
import com.morethanheroic.swords.journal.service.JournalManager;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CombatInitializer {

    private final JournalManager journalManager;

    public void initialize(final UserEntity userEntity, final MonsterDefinition monsterDefinition) {
        journalManager.createJournalEntry(userEntity, JournalType.MONSTER, monsterDefinition.getId());
    }
}
