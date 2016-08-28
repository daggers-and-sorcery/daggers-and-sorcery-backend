package com.morethanheroic.swords.skill.lockpicking.service;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.service.probe.extension.AttributeProbeCalculatorExtension;
import com.morethanheroic.swords.attribute.service.probe.extension.domain.PostProbeHookData;
import com.morethanheroic.swords.attribute.service.probe.extension.domain.PreProbeHookData;
import com.morethanheroic.swords.attribute.service.probe.extension.domain.RequirementsData;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import org.springframework.stereotype.Service;

@Service
public class LockpickingAttributeProbeCalculatorExtension implements AttributeProbeCalculatorExtension {

    private static final int LOCKPICK_ITEM_ID = 111;

    private final InventoryEntityFactory inventoryEntityFactory;

    private final ItemDefinition lockpick;

    public LockpickingAttributeProbeCalculatorExtension(final InventoryEntityFactory inventoryEntityFactory, final ItemDefinitionCache itemDefinitionCache) {
        this.inventoryEntityFactory = inventoryEntityFactory;

        lockpick = itemDefinitionCache.getDefinition(LOCKPICK_ITEM_ID);
    }

    @Override
    public boolean checkRequirements(RequirementsData requirementsData) {
        return inventoryEntityFactory.getEntity(requirementsData.getUserEntity().getId()).hasItem(lockpick);
    }

    @Override
    public void preProbeHook(final PreProbeHookData preProbeHookData) {
    }

    @Override
    public void postProbeHook(final PostProbeHookData postProbeHookData) {
        if (postProbeHookData.isSuccessfulProbe()) {
            //Lockpick removal chance 30%
        } else {
            //Lockpick removal chance 60%
        }
        //Pass if it was success or not
        //TODO: remove a lockpick if needed
    }

    @Override
    public Attribute supportedAttribute() {
        return SkillAttribute.LOCKPICKING;
    }
}
