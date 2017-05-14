package com.morethanheroic.swords.witchhuntersguild.service;

import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.witchhuntersguild.domain.WitchhuntersGuildEntity;
import com.morethanheroic.swords.witchhuntersguild.domain.definition.WitchhuntersGuildJobDefinition;
import com.morethanheroic.swords.witchhuntersguild.domain.definition.requirement.WitchhuntersGuildJobItemRequirement;
import com.morethanheroic.swords.witchhuntersguild.domain.definition.requirement.WitchhuntersGuildJobKillRequirement;
import com.morethanheroic.swords.witchhuntersguild.domain.definition.requirement.WitchhuntersGuildJobRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WitchhuntersGuildHandInQuestCalculator {

    private final WitchhuntersGuildEntityFactory witchhuntersGuildEntityFactory;
    private final InventoryEntityFactory inventoryEntityFactory;

    public boolean handInQuest(final UserEntity userEntity) {
        final WitchhuntersGuildEntity witchhuntersGuildEntity = witchhuntersGuildEntityFactory.getEntity(userEntity);

        final boolean allRequirementsMet = checkIfAllRequirementsMet(userEntity, witchhuntersGuildEntity.getJob());

        if (allRequirementsMet) {
            //TODO: Handin logic, remove the items and refresh the killcount!
        }

        return allRequirementsMet;
    }

    private boolean checkIfAllRequirementsMet(final UserEntity userEntity, final WitchhuntersGuildJobDefinition witchhuntersGuildJobDefinition) {
        return witchhuntersGuildJobDefinition.getRequirements().stream()
                .allMatch(witchhuntersGuildJobRequirement -> checkIfRequirementMet(userEntity, witchhuntersGuildJobRequirement));
    }

    private boolean checkIfRequirementMet(final UserEntity userEntity, final WitchhuntersGuildJobRequirement witchhuntersGuildJobRequirement) {
        //TODO: Move all of these into separate classes!
        if (witchhuntersGuildJobRequirement instanceof WitchhuntersGuildJobItemRequirement) {
            final WitchhuntersGuildJobItemRequirement witchhuntersGuildJobItemRequirement = (WitchhuntersGuildJobItemRequirement) witchhuntersGuildJobRequirement;
            final InventoryEntity inventoryEntity = inventoryEntityFactory.getEntity(userEntity);

            return inventoryEntity.hasItemAmount(witchhuntersGuildJobItemRequirement.getItem(), witchhuntersGuildJobItemRequirement.getAmount());
        } else if (witchhuntersGuildJobRequirement instanceof WitchhuntersGuildJobKillRequirement) {
            //TODO

            return true;
        } else {
            throw new IllegalStateException("Unknown WitchhuntersGuildJobRequirement type: " + witchhuntersGuildJobRequirement + "!");
        }
    }
}
