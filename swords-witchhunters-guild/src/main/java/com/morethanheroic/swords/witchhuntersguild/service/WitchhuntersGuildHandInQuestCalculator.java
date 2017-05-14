package com.morethanheroic.swords.witchhuntersguild.service;

import com.morethanheroic.swords.inventory.domain.InventoryEntity;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.metadata.service.MetadataEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.witchhuntersguild.domain.WitchhuntersGuildEntity;
import com.morethanheroic.swords.witchhuntersguild.domain.definition.WitchhuntersGuildJobDefinition;
import com.morethanheroic.swords.witchhuntersguild.domain.definition.requirement.WitchhuntersGuildJobItemRequirement;
import com.morethanheroic.swords.witchhuntersguild.domain.definition.requirement.WitchhuntersGuildJobKillRequirement;
import com.morethanheroic.swords.witchhuntersguild.domain.definition.requirement.WitchhuntersGuildJobRequirement;
import com.morethanheroic.swords.witchhuntersguild.service.definition.cache.WitchhuntersGuildJobDefinitionCache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WitchhuntersGuildHandInQuestCalculator {

    private final WitchhuntersGuildEntityFactory witchhuntersGuildEntityFactory;
    private final InventoryEntityFactory inventoryEntityFactory;
    private final MetadataEntityFactory metadataEntityFactory;
    private final WitchhuntersGuildJobDefinitionCache witchhuntersGuildJobDefinitionCache;

    @Transactional
    public boolean handInQuest(final UserEntity userEntity) {
        final WitchhuntersGuildEntity witchhuntersGuildEntity = witchhuntersGuildEntityFactory.getEntity(userEntity);

        final boolean allRequirementsMet = checkIfAllRequirementsMet(userEntity, witchhuntersGuildEntity.getJob());

        if (allRequirementsMet) {
            removeRequirements(userEntity, witchhuntersGuildEntity.getJob());
        }

        updateToNextJob(userEntity, witchhuntersGuildEntity);

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
            final WitchhuntersGuildJobKillRequirement witchhuntersGuildJobKillRequirement = (WitchhuntersGuildJobKillRequirement) witchhuntersGuildJobRequirement;

            for (int i = 1; i <= 2; i++) {
                final int targetMonster = metadataEntityFactory.getNumericEntity(userEntity, "WITCHHUNTERS_GUILD_KILL_TARGET_" + i + "_MONSTER_ID").getValue();

                if (targetMonster != 0 && targetMonster == witchhuntersGuildJobKillRequirement.getMonster().getId()) {
                    final int targetMonsterCount = metadataEntityFactory.getNumericEntity(userEntity, "WITCHHUNTERS_GUILD_KILL_TARGET_" + i + "_MONSTER_COUNT").getValue();

                    return targetMonsterCount >= witchhuntersGuildJobKillRequirement.getAmount();
                }
            }

            return false;
        } else {
            throw new IllegalStateException("Unknown WitchhuntersGuildJobRequirement type: " + witchhuntersGuildJobRequirement + "!");
        }
    }

    private void removeRequirements(final UserEntity userEntity, final WitchhuntersGuildJobDefinition witchhuntersGuildJobDefinition) {
        witchhuntersGuildJobDefinition.getRequirements()
                .forEach(witchhuntersGuildJobRequirement -> {
                    if (witchhuntersGuildJobRequirement instanceof WitchhuntersGuildJobItemRequirement) {
                        final WitchhuntersGuildJobItemRequirement witchhuntersGuildJobItemRequirement = (WitchhuntersGuildJobItemRequirement) witchhuntersGuildJobRequirement;
                        final InventoryEntity inventoryEntity = inventoryEntityFactory.getEntity(userEntity);

                        inventoryEntity.removeItem(witchhuntersGuildJobItemRequirement.getItem(), witchhuntersGuildJobItemRequirement.getAmount());
                    } else if (witchhuntersGuildJobRequirement instanceof WitchhuntersGuildJobKillRequirement) {
                        for (int i = 1; i <= 2; i++) {
                            metadataEntityFactory.getNumericEntity(userEntity, "WITCHHUNTERS_GUILD_KILL_TARGET_" + i + "_MONSTER_ID").setValue(0);
                            metadataEntityFactory.getNumericEntity(userEntity, "WITCHHUNTERS_GUILD_KILL_TARGET_" + i + "_MONSTER_COUNT").setValue(0);
                        }
                    } else {
                        throw new IllegalStateException("Unknown WitchhuntersGuildJobRequirement type: " + witchhuntersGuildJobRequirement + "!");
                    }
                });
    }

    private void updateToNextJob(final UserEntity userEntity, final WitchhuntersGuildEntity witchhuntersGuildEntity) {
        final WitchhuntersGuildJobDefinition nextJob = witchhuntersGuildJobDefinitionCache.getDefinition(witchhuntersGuildEntity.getJob().getId() + 1);

        metadataEntityFactory.getNumericEntity(userEntity, "WITCHHUNTERS_GUILD_JOB").setValue(nextJob.getId());

        for (int i = 1; i <= 2; i++) {
            metadataEntityFactory.getNumericEntity(userEntity, "WITCHHUNTERS_GUILD_KILL_TARGET_" + i + "_MONSTER_ID").setValue(-1);
            metadataEntityFactory.getNumericEntity(userEntity, "WITCHHUNTERS_GUILD_KILL_TARGET_" + i + "_MONSTER_COUNT").setValue(-1);
        }

        int lastUsedKillCountId = 1;
        for (WitchhuntersGuildJobRequirement witchhuntersGuildJobRequirement : nextJob.getRequirements()) {
            if (witchhuntersGuildJobRequirement instanceof WitchhuntersGuildJobKillRequirement) {
                final WitchhuntersGuildJobKillRequirement witchhuntersGuildJobKillRequirement = ((WitchhuntersGuildJobKillRequirement) witchhuntersGuildJobRequirement);

                metadataEntityFactory.getNumericEntity(userEntity, "WITCHHUNTERS_GUILD_KILL_TARGET_" + lastUsedKillCountId + "_MONSTER_ID").setValue(witchhuntersGuildJobKillRequirement.getMonster().getId());
                metadataEntityFactory.getNumericEntity(userEntity, "WITCHHUNTERS_GUILD_KILL_TARGET_" + lastUsedKillCountId + "_MONSTER_COUNT").setValue(0);

                lastUsedKillCountId++;
            }
        }
    }
}
