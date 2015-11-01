package com.morethanheroic.swords.combat.service.calc;

import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.Winner;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.adder.DropAdder;
import com.morethanheroic.swords.combat.service.adder.ScavengingAdder;
import com.morethanheroic.swords.combat.service.adder.XpAdder;
import com.morethanheroic.swords.combat.service.calc.turn.TurnCalculatorFactory;
import com.morethanheroic.swords.journal.model.JournalType;
import com.morethanheroic.swords.journal.service.JournalManager;
import com.morethanheroic.swords.map.repository.domain.MapObjectDatabaseEntity;
import com.morethanheroic.swords.map.service.MapManager;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CombatCalculator {

    private final CombatMessageBuilder combatMessageBuilder;
    private final MapManager mapManager;
    private final TurnCalculatorFactory turnCalculatorFactory;
    private final JournalManager journalManager;
    private final UserMapper userMapper;

    @Autowired
    private DropAdder dropAdder;

    @Autowired
    private ScavengingAdder scavengingAdder;

    @Autowired
    private XpAdder xpAdder;

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    @Autowired
    public CombatCalculator(TurnCalculatorFactory turnCalculatorFactory, CombatMessageBuilder combatMessageBuilder, MapManager mapManager, JournalManager journalManager, UserMapper userMapper) {
        this.turnCalculatorFactory = turnCalculatorFactory;
        this.combatMessageBuilder = combatMessageBuilder;
        this.mapManager = mapManager;
        this.journalManager = journalManager;
        this.userMapper = userMapper;
    }

    public CombatResult doFight(UserEntity userEntity, MonsterDefinition monsterDefinition, MapObjectDatabaseEntity spawn) {
        CombatResult result = new CombatResult();
        Combat combat = new Combat(userEntity, monsterDefinition, globalAttributeCalculator);

        startFight(result, combat);
        calculateFight(result, combat);
        endFight(result, combat, spawn);

        return result;
    }

    private void startFight(CombatResult result, Combat combat) {
        journalManager.createJournalEntry(combat.getUserCombatEntity().getUserEntity(), JournalType.MONSTER, combat.getMonsterCombatEntity().getMonsterDefinition().getId());

        result.addMessage(combatMessageBuilder.buildFightInitialisationMessage(combat.getMonsterCombatEntity().getMonsterDefinition().getName()));
    }

    private void calculateFight(CombatResult result, Combat combat) {
        while (combat.getUserCombatEntity().getActualHealth() > 0 && combat.getMonsterCombatEntity().getActualHealth() > 0) {
            turnCalculatorFactory.getTurnCalculator(combat.getTurn()).takeTurn(result, combat);
        }
    }

    private void endFight(CombatResult result, Combat combat, MapObjectDatabaseEntity spawn) {
        if (result.getWinner() == Winner.PLAYER) {
            UserEntity user = combat.getUserCombatEntity().getUserEntity();
            MonsterDefinition monster = combat.getMonsterCombatEntity().getMonsterDefinition();

            dropAdder.addDropsToUserFromMonsterDefinition(result, user, monster);
            scavengingAdder.addScavengingDropsToUserFromMonsterDefinition(result, user, monster);

            xpAdder.addXpToUserFromMonsterDefinition(result, user);

            mapManager.getMap(user.getMapId()).removeSpawn(spawn.getId());

            userMapper.updateBasicCombatStats(combat.getUserCombatEntity().getUserEntity().getId(), combat.getUserCombatEntity().getActualHealth(), combat.getUserCombatEntity().getActualMana(), combat.getUserCombatEntity().getUserEntity().getMovement() - 1);
        }
    }
}
