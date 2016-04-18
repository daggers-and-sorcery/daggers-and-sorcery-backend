package com.morethanheroic.swords.combat.service.calc;

import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.Winner;
import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.combat.service.adder.DropAdder;
import com.morethanheroic.swords.combat.service.adder.XpAdder;
import com.morethanheroic.swords.combat.service.calc.turn.TurnCalculatorFactory;
import com.morethanheroic.swords.journal.model.JournalType;
import com.morethanheroic.swords.journal.service.JournalManager;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.scavenging.domain.ScavengingResult;
import com.morethanheroic.swords.scavenging.domain.ScavengingResultEntity;
import com.morethanheroic.swords.scavenging.service.ScavengingFacade;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.repository.domain.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CombatCalculator {

    private static final String UNIDENTIFIED_ITEM_NAME = "Unidentified item";

    private final CombatMessageBuilder combatMessageBuilder;
    private final TurnCalculatorFactory turnCalculatorFactory;
    private final JournalManager journalManager;
    private final UserMapper userMapper;

    @Autowired
    private ScavengingFacade scavengingFacade;

    @Autowired
    private DropAdder dropAdder;

    @Autowired
    private XpAdder xpAdder;

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    @Autowired
    public CombatCalculator(TurnCalculatorFactory turnCalculatorFactory, CombatMessageBuilder combatMessageBuilder, JournalManager journalManager, UserMapper userMapper) {
        this.turnCalculatorFactory = turnCalculatorFactory;
        this.combatMessageBuilder = combatMessageBuilder;
        this.journalManager = journalManager;
        this.userMapper = userMapper;
    }

    public CombatResult doFight(UserEntity userEntity, MonsterDefinition monsterDefinition) {
        CombatResult result = new CombatResult();
        Combat combat = new Combat(userEntity, monsterDefinition, globalAttributeCalculator);

        startFight(result, combat);
        calculateFight(result, combat);
        endFight(result, combat);

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

    private void endFight(CombatResult result, Combat combat) {
        if (result.getWinner() == Winner.PLAYER) {
            UserEntity userEntity = combat.getUserCombatEntity().getUserEntity();
            MonsterDefinition monster = combat.getMonsterCombatEntity().getMonsterDefinition();

            dropAdder.addDropsToUserFromMonsterDefinition(result, userEntity, monster);

            ScavengingResult scavengingResult = scavengingFacade.handleScavenging(userEntity, monster);

            if (scavengingResult.isSuccessfulScavenge()) {
                for (ScavengingResultEntity scavengingResultEntity : scavengingResult.getScavengingResultList()) {
                    if (scavengingResultEntity.isIdentified()) {
                        result.addMessage(combatMessageBuilder.buildScavengeItemAwardMessage(scavengingResultEntity.getItem().getName(), scavengingResultEntity.getAmount()));
                    } else {
                        result.addMessage(combatMessageBuilder.buildScavengeItemAwardMessage(UNIDENTIFIED_ITEM_NAME, scavengingResultEntity.getAmount()));
                    }
                }

                if(scavengingResult.getScavengingXp() > 0) {
                    result.addMessage(combatMessageBuilder.buildScavengeXpAwardMessage(scavengingResult.getScavengingXp()));
                }
            }

            xpAdder.addXpToUserFromMonsterDefinition(result, userEntity);

            //TODO: Move user mapper outside, this class shouldn't know about user mapper at all, it should know more about user facade
            userMapper.updateBasicCombatStats(userEntity.getId(), combat.getUserCombatEntity().getActualHealth(), combat.getUserCombatEntity().getActualMana(), userEntity.getMovementPoints() - 1);
        }
    }
}
