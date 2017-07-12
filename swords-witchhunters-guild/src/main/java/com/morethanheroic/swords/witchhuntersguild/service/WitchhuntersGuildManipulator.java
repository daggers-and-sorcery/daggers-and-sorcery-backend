package com.morethanheroic.swords.witchhuntersguild.service;

import com.morethanheroic.swords.metadata.service.MetadataEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.witchhuntersguild.service.definition.cache.WitchhuntersGuildJobDefinitionCache;
import com.morethanheroic.swords.witchhuntersguild.service.quest.WitchhuntersGuildQuestUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Provide a way to easily manipulate the user's data related to the witchhunter's guild.
 */
@Service
@RequiredArgsConstructor
public class WitchhuntersGuildManipulator {

    private static final String WITCHHUNTERS_GUILD_UNLOCKED = "WITCHHUNTERS_GUILD_UNLOCKED";
    private static final String WITCHHUNTERS_GUILD_UNLOCKED_VALUE = "UNLOCKED";

    private static final int FIRST_WITCHHUNTER_GUILD_QUEST = 1;

    private final MetadataEntityFactory metadataEntityFactory;
    private final WitchhuntersGuildQuestUpdater witchhuntersGuildQuestUpdater;
    private final WitchhuntersGuildJobDefinitionCache witchhuntersGuildJobDefinitionCache;

    /**
     * Unlock the witchhunter's guild for the user.
     *
     * @param userEntity the user to open the guild for
     */
    public void unlockWitchhuntersGuild(final UserEntity userEntity) {
        metadataEntityFactory.getTextEntity(userEntity, WITCHHUNTERS_GUILD_UNLOCKED).setValue(WITCHHUNTERS_GUILD_UNLOCKED_VALUE);

        witchhuntersGuildQuestUpdater.assignQuest(userEntity, witchhuntersGuildJobDefinitionCache.getDefinition(FIRST_WITCHHUNTER_GUILD_QUEST));
    }
}
