package com.morethanheroic.swords.combat.service.calc;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.journal.model.JournalType;
import com.morethanheroic.swords.journal.service.JournalManager;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class CombatInitializer {

    private final JournalManager journalManager;
    private final CombatMessageBuilder combatMessageBuilder;

    public void initialize(final UserEntity userEntity, final MonsterDefinition monsterDefinition) {
        journalManager.createJournalEntry(userEntity, JournalType.MONSTER, monsterDefinition.getId());
    }

    @Deprecated
    public void initialize(Combat combat, CombatResult combatResult) {
        journalManager.createJournalEntry(combat.getUserCombatEntity().getUserEntity(), JournalType.MONSTER, combat.getMonsterCombatEntity().getMonsterDefinition().getId());

        combatResult.addMessage(combatMessageBuilder.buildFightInitialisationMessage(combat.getMonsterCombatEntity().getMonsterDefinition().getName()));
    }
}
