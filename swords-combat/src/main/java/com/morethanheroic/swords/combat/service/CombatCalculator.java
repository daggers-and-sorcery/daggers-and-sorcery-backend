package com.morethanheroic.swords.combat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.domain.step.CombatStep;
import com.morethanheroic.swords.combat.repository.domain.CombatExperienceMapper;
import com.morethanheroic.swords.combat.repository.domain.CombatMapper;
import com.morethanheroic.swords.combat.service.calc.AttackCalculatorProvider;
import com.morethanheroic.swords.combat.service.calc.AttackTypeCalculator;
import com.morethanheroic.swords.combat.service.calc.CombatInitializer;
import com.morethanheroic.swords.combat.service.calc.CombatTerminator;
import com.morethanheroic.swords.combat.service.calc.attack.AttackType;
import com.morethanheroic.swords.combat.service.calc.initialisation.InitialisationCalculator;
import com.morethanheroic.swords.combat.service.context.CombatContextFactory;
import com.morethanheroic.swords.combat.service.item.UseItemService;
import com.morethanheroic.swords.equipment.service.EquipmentFacade;
import com.morethanheroic.swords.monster.service.cache.MonsterDefinitionCache;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.spell.service.UseSpellService;
import com.morethanheroic.swords.user.domain.UserEntity;

@Service
public class CombatCalculator {

    @Autowired
    private CombatInitializer combatInitializer;

    @Autowired
    private InitialisationCalculator initialisationCalculator;

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    @Autowired
    private CombatMessageBuilder combatMessageBuilder;

    @Autowired
    private AttackTypeCalculator attackTypeCalculator;

    @Autowired
    private EquipmentFacade equipmentFacade;

    @Autowired
    private CombatTerminator combatTerminator;

    @Autowired
    private CombatMapper combatMapper;

    @Autowired
    private MonsterDefinitionCache monsterDefinitionCache;

    @Autowired
    private MonsterCombatEntityFactory monsterCombatEntityFactory;

    @Autowired
    private CombatMessageFactory combatMessageFactory;

    @Autowired
    private CombatExperienceMapper combatExperienceMapper;

    @Autowired
    private AttackCalculatorProvider attackCalculatorProvider;

    @Autowired
    private UseSpellService useSpellService;

    @Autowired
    private UseItemService useItemService;

    @Autowired
    private SavedCombatEntityFactory savedCombatEntityFactory;

    @Autowired
    private CombatContextFactory combatContextFactory;

    @Transactional
    public boolean isCombatRunning(final UserEntity userEntity) {
        return combatMapper.getRunningCombat(userEntity.getId()) != null;
    }

    //TODO: move away
    @Transactional
    public void addCombatExperience(final UserEntity userEntity, final SkillType skillType, final int amount) {
        combatExperienceMapper.addExperience(userEntity.getId(), skillType, amount);
    }

    private List<CombatStep> playerAttack(final CombatContext combatContext) {
        return attackCalculatorProvider.getAttackCalculatorForAttackType(calculateUserAttackType(combatContext.getUser().getUserEntity()))
                .calculateAttack(combatContext.getUser(), combatContext.getOpponent(), combatContext);
    }

    private List<CombatStep> monsterAttack(final CombatContext combatContext) {
        return attackCalculatorProvider.getAttackCalculatorForAttackType(combatContext.getOpponent().getAttackType())
                .calculateAttack(combatContext.getOpponent(), combatContext.getUser(), combatContext);
    }

    private AttackType calculateUserAttackType(UserEntity userEntity) {
        return attackTypeCalculator.calculateAttackType(equipmentFacade.getEquipment(userEntity));
    }
}
