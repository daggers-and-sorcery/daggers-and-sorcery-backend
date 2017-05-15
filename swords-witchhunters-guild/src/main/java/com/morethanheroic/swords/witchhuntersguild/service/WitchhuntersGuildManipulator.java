package com.morethanheroic.swords.witchhuntersguild.service;

import org.springframework.stereotype.Service;

import com.morethanheroic.swords.metadata.service.MetadataEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.witchhuntersguild.service.definition.cache.WitchhuntersGuildJobDefinitionCache;

import lombok.RequiredArgsConstructor;

/**
 * Provide a way to easily manipulate the user's data related to the witchhunter's guild.
 */
@Service
@RequiredArgsConstructor
public class WitchhuntersGuildManipulator {

    private static final String WITCHUNTERS_GUILD_UNLOCKED = "WITCHUNTERS_GUILD_UNLOCKED";
    private static final String WITCHUNTERS_GUILD_UNLOCKED_VALUE = "UNLOCKED";

    private static final int FIRST_WITCHHUNTER_GUILD_QUEST = 1;

    private final MetadataEntityFactory metadataEntityFactory;
    private final WitchhuntersGuildQuestUpdater witchhuntersGuildQuestUpdater;
    private final WitchhuntersGuildJobDefinitionCache witchhuntersGuildJobDefinitionCache;

    /**
     * Unlock the witchhunter's guild for the user.
     *
     * @param userEntity the user to open the guild for
     */
    public void unlockWitchhuntersGuildForUser(final UserEntity userEntity) {
        metadataEntityFactory.getTextEntity(userEntity, WITCHUNTERS_GUILD_UNLOCKED).setValue(WITCHUNTERS_GUILD_UNLOCKED_VALUE);
        witchhuntersGuildQuestUpdater.assignQuest(userEntity, witchhuntersGuildJobDefinitionCache.getDefinition(FIRST_WITCHHUNTER_GUILD_QUEST));
    }
}
