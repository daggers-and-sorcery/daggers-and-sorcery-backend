package com.morethanheroic.swords.skill.lockpicking.service;

import com.morethanheroic.math.PercentageCalculator;
import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.service.attempt.extension.AttributeAttemptCalculatorExtension;
import com.morethanheroic.swords.attribute.service.attempt.extension.domain.PostAttemptHookData;
import com.morethanheroic.swords.attribute.service.attempt.extension.domain.PreAttemptHookData;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.definition.cache.ItemDefinitionCache;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.lockpicking.service.domain.LockpickingAttributeAttemptCalculatorExtensionResult;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class LockpickingAttributeAttemptCalculatorExtension
        implements AttributeAttemptCalculatorExtension<LockpickingAttributeAttemptCalculatorExtensionResult> {

    private static final int LOCKPICK_ITEM_ID = 111;
    private static final int CHANCE_TO_LOST_ON_FAIL = 60;

    private final InventoryEntityFactory inventoryEntityFactory;
    private final SkillEntityFactory skillEntityFactory;
    private final PercentageCalculator percentageCalculator;

    private final ItemDefinition lockpick;

    public LockpickingAttributeAttemptCalculatorExtension(final InventoryEntityFactory inventoryEntityFactory, final PercentageCalculator percentageCalculator, final ItemDefinitionCache itemDefinitionCache, final SkillEntityFactory skillEntityFactory) {
        this.inventoryEntityFactory = inventoryEntityFactory;
        this.skillEntityFactory = skillEntityFactory;
        this.percentageCalculator = percentageCalculator;

        lockpick = itemDefinitionCache.getDefinition(LOCKPICK_ITEM_ID);
    }

    @Override
    public boolean checkRequirements(final LockpickingAttributeAttemptCalculatorExtensionResult lockpickingAttributeProbeCalculatorExtensionResult, final UserEntity userEntity) {
        if (inventoryEntityFactory.getEntity(userEntity.getId()).hasItem(lockpick)) {
            lockpickingAttributeProbeCalculatorExtensionResult.setHadLockpick(true);

            return true;
        }

        return false;
    }

    @Override
    public void preProbeHook(final LockpickingAttributeAttemptCalculatorExtensionResult lockpickingAttributeProbeCalculatorExtensionResult, final PreAttemptHookData preAttemptHookData) {
    }

    @Override
    public void postProbeHook(final LockpickingAttributeAttemptCalculatorExtensionResult lockpickingAttributeProbeCalculatorExtensionResult, final PostAttemptHookData postAttemptHookData) {
        if (shouldCalculateLockpickLoss(postAttemptHookData)) {
            calculateLockpickLoss(postAttemptHookData.getUserEntity(), lockpickingAttributeProbeCalculatorExtensionResult);
        }

        final int experienceReward = calculateExperienceReward(postAttemptHookData.getTargetToHit(), postAttemptHookData.isSuccessfulProbe());

        lockpickingAttributeProbeCalculatorExtensionResult.setExperienceReward(experienceReward);
        skillEntityFactory.getEntity(postAttemptHookData.getUserEntity().getId()).increaseExperience(SkillType.LOCKPICKING, experienceReward);
    }

    @Override
    public Attribute supportedAttribute() {
        return SkillAttribute.LOCKPICKING;
    }

    private boolean shouldCalculateLockpickLoss(final PostAttemptHookData postAttemptHookData) {
        return !postAttemptHookData.isSuccessfulProbe();
    }

    private void calculateLockpickLoss(final UserEntity userEntity, final LockpickingAttributeAttemptCalculatorExtensionResult lockpickingAttributeAttemptCalculatorExtensionResult) {
        final boolean isLostLockpick = percentageCalculator.calculatePercentageHit(CHANCE_TO_LOST_ON_FAIL);

        lockpickingAttributeAttemptCalculatorExtensionResult.setLostLockpick(isLostLockpick);

        if (isLostLockpick) {
            inventoryEntityFactory.getEntity(userEntity.getId()).removeItem(lockpick, 1);
        }
    }

    private int calculateExperienceReward(final int target, final boolean isSuccessful) {
        if (isSuccessful) {
            return target * 20;
        } else {
            return target * 10;
        }
    }
}
