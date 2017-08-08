package com.morethanheroic.swords.combat.domain.effect.entry.spell;

import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectDefinition;
import com.morethanheroic.swords.combat.entity.domain.UserCombatEntity;
import com.morethanheroic.swords.inventory.domain.IdentificationType;
import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.inventory.service.UnidentifiedItemIdCalculator;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.definition.cache.ItemDefinitionCache;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LesserIdentifySpellEffectDefinition extends CombatEffectDefinition {

    private static final int ALTERATION_EXPERIENCE_REWARD = 150;

    private final InventoryEntityFactory inventoryEntityFactory;
    private final SkillEntityFactory skillEntityFactory;
    private final UnidentifiedItemIdCalculator unidentifiedItemIdCalculator;
    private final ItemDefinitionCache itemDefinitionCache;

    @Override
    public void apply(CombatEffectApplyingContext effectApplyingContext, CombatEffectDataHolder combatEffectDataHolder) {
        final UserEntity userEntity = ((UserCombatEntity) effectApplyingContext.getDestination().getCombatEntity()).getUserEntity();
        final InventoryEntity inventoryEntity = inventoryEntityFactory.getEntity(userEntity.getId());

        final ItemDefinition realItem = itemDefinitionCache.getDefinition(unidentifiedItemIdCalculator.getRealItemId(combatEffectDataHolder.getSessionEntity(), Integer.parseInt((String) combatEffectDataHolder.getParameters().get("itemId"))));

        if (inventoryEntity.hasItem(realItem, IdentificationType.UNIDENTIFIED)) {
            inventoryEntity.removeItem(realItem, 1, IdentificationType.UNIDENTIFIED);
            inventoryEntity.addItem(realItem, 1, IdentificationType.IDENTIFIED);
        }

        skillEntityFactory.getEntity(userEntity.getId()).increaseExperience(SkillType.ALTERATION, ALTERATION_EXPERIENCE_REWARD);
    }

    @Override
    public String getId() {
        return "lesser_identify";
    }
}
