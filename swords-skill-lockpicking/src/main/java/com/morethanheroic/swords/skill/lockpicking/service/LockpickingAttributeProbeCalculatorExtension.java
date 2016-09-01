package com.morethanheroic.swords.skill.lockpicking.service;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.service.probe.extension.AttributeProbeCalculatorExtension;
import com.morethanheroic.swords.attribute.service.probe.extension.domain.PostProbeHookData;
import com.morethanheroic.swords.attribute.service.probe.extension.domain.PreProbeHookData;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.lockpicking.service.domain.LockpickingAttributeProbeCalculatorExtensionResult;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class LockpickingAttributeProbeCalculatorExtension implements AttributeProbeCalculatorExtension<LockpickingAttributeProbeCalculatorExtensionResult> {

    private static final int LOCKPICK_ITEM_ID = 111;

    private final InventoryEntityFactory inventoryEntityFactory;
    private final SkillEntityFactory skillEntityFactory;
    private final LockpickLossChanceCalculator lockpickLossChanceCalculator;

    private final ItemDefinition lockpick;

    public LockpickingAttributeProbeCalculatorExtension(final InventoryEntityFactory inventoryEntityFactory, final LockpickLossChanceCalculator lockpickLossChanceCalculator, final ItemDefinitionCache itemDefinitionCache, final SkillEntityFactory skillEntityFactory) {
        this.inventoryEntityFactory = inventoryEntityFactory;
        this.lockpickLossChanceCalculator = lockpickLossChanceCalculator;
        this.skillEntityFactory = skillEntityFactory;

        lockpick = itemDefinitionCache.getDefinition(LOCKPICK_ITEM_ID);
    }

    @Override
    public boolean checkRequirements(final LockpickingAttributeProbeCalculatorExtensionResult lockpickingAttributeProbeCalculatorExtensionResult, final UserEntity userEntity) {
        if (inventoryEntityFactory.getEntity(userEntity.getId()).hasItem(lockpick)) {
            lockpickingAttributeProbeCalculatorExtensionResult.setHadLockpick(true);

            return true;
        }

        return false;
    }

    @Override
    public void preProbeHook(final LockpickingAttributeProbeCalculatorExtensionResult lockpickingAttributeProbeCalculatorExtensionResult, final PreProbeHookData preProbeHookData) {
    }

    @Override
    public void postProbeHook(final LockpickingAttributeProbeCalculatorExtensionResult lockpickingAttributeProbeCalculatorExtensionResult, final PostProbeHookData postProbeHookData) {
        final boolean isLostLockpick = lockpickLossChanceCalculator.isLoss(postProbeHookData.isSuccessfulProbe());
        lockpickingAttributeProbeCalculatorExtensionResult.setLostLockpick(isLostLockpick);

        if (isLostLockpick) {
            inventoryEntityFactory.getEntity(postProbeHookData.getUserEntity().getId()).removeItem(lockpick, 1);
        }

        final int experienceReward = calculateExperienceReward(postProbeHookData.getTargetToHit(), postProbeHookData.isSuccessfulProbe());

        lockpickingAttributeProbeCalculatorExtensionResult.setExperienceReward(experienceReward);
        skillEntityFactory.getSkillEntity(postProbeHookData.getUserEntity()).increaseExperience(SkillType.LOCKPICKING, experienceReward);
    }

    @Override
    public Attribute supportedAttribute() {
        return SkillAttribute.LOCKPICKING;
    }

    private int calculateExperienceReward(final int target, final boolean isSuccessful) {
        if(isSuccessful) {
            return target * 20;
        } else {
            return target * 10;
        }
    }
}
