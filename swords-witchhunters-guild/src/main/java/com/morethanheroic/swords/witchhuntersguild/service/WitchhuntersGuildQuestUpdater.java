package com.morethanheroic.swords.witchhuntersguild.service;

import org.springframework.stereotype.Service;

import com.morethanheroic.swords.metadata.service.MetadataEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.witchhuntersguild.domain.definition.WitchhuntersGuildJobDefinition;
import com.morethanheroic.swords.witchhuntersguild.domain.definition.requirement.WitchhuntersGuildJobKillRequirement;
import com.morethanheroic.swords.witchhuntersguild.domain.definition.requirement.WitchhuntersGuildJobRequirement;

import lombok.RequiredArgsConstructor;

/**
 * Update the quest of the user in the witchhunter's guild.
 */
@Service
@RequiredArgsConstructor
public class WitchhuntersGuildQuestUpdater {

    private final MetadataEntityFactory metadataEntityFactory;

    /**
     * Assign the witchhunters guild quest to the user. This method will bypass any check about the user's actual quest and will override that quest if the
     * user has any already running.
     *
     * @param userEntity the user to assign the quest to
     * @param witchhuntersGuildJobDefinition the quest to assign
     */
    public void assignQuest(final UserEntity userEntity, final WitchhuntersGuildJobDefinition witchhuntersGuildJobDefinition) {
        metadataEntityFactory.getNumericEntity(userEntity, "WITCHHUNTERS_GUILD_JOB").setValue(witchhuntersGuildJobDefinition.getId());

        for (int i = 1; i <= 2; i++) {
            metadataEntityFactory.getNumericEntity(userEntity, "WITCHHUNTERS_GUILD_KILL_TARGET_" + i + "_MONSTER_ID").setValue(-1);
            metadataEntityFactory.getNumericEntity(userEntity, "WITCHHUNTERS_GUILD_KILL_TARGET_" + i + "_MONSTER_COUNT").setValue(-1);
        }

        int lastUsedKillCountId = 1;
        for (WitchhuntersGuildJobRequirement witchhuntersGuildJobRequirement : witchhuntersGuildJobDefinition.getRequirements()) {
            if (witchhuntersGuildJobRequirement instanceof WitchhuntersGuildJobKillRequirement) {
                final WitchhuntersGuildJobKillRequirement witchhuntersGuildJobKillRequirement = ((WitchhuntersGuildJobKillRequirement) witchhuntersGuildJobRequirement);

                metadataEntityFactory.getNumericEntity(userEntity, "WITCHHUNTERS_GUILD_KILL_TARGET_" + lastUsedKillCountId + "_MONSTER_ID").setValue(witchhuntersGuildJobKillRequirement.getMonster().getId());
                metadataEntityFactory.getNumericEntity(userEntity, "WITCHHUNTERS_GUILD_KILL_TARGET_" + lastUsedKillCountId + "_MONSTER_COUNT").setValue(0);

                lastUsedKillCountId++;
            }
        }
    }
}
