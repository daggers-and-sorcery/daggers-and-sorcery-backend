package com.morethanheroic.swords.skill.lockpicking.service;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.service.attempt.extension.AttributeAttemptCalculatorExtension;
import com.morethanheroic.swords.attribute.service.attempt.extension.domain.PostAttemptHookData;
import com.morethanheroic.swords.attribute.service.attempt.extension.domain.PreAttemptHookData;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.skill.lockpicking.service.domain.LockpickingAttributeAttemptCalculatorExtensionResult;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class LockpickingAttributeAttemptCalculatorExtension
    implements AttributeAttemptCalculatorExtension<LockpickingAttributeAttemptCalculatorExtensionResult> {

    private static final int LOCKPICK_ITEM_ID = 111;

    private final InventoryEntityFactory inventoryEntityFactory;
    private final LockpickLossChanceCalculator lockpickLossChanceCalculator;

    private final ItemDefinition lockpick;

    public LockpickingAttributeAttemptCalculatorExtension(final InventoryEntityFactory inventoryEntityFactory, final LockpickLossChanceCalculator lockpickLossChanceCalculator, final ItemDefinitionCache itemDefinitionCache) {
        this.inventoryEntityFactory = inventoryEntityFactory;
        this.lockpickLossChanceCalculator = lockpickLossChanceCalculator;

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
        lockpickingAttributeProbeCalculatorExtensionResult.setLostLockpick(lockpickLossChanceCalculator.isLoss(postAttemptHookData.isSuccessfulProbe()));

        inventoryEntityFactory.getEntity(postAttemptHookData.getUserEntity().getId()).removeItem(lockpick, 1);
    }

    @Override
    public Attribute supportedAttribute() {
        return SkillAttribute.LOCKPICKING;
    }
}
