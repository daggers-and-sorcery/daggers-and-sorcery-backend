package com.morethanheroic.swords.ladder.view.response.service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.ladder.domain.LadderEntry;
import com.morethanheroic.swords.ladder.view.response.domain.LadderInfoPartialResponse;
import com.morethanheroic.swords.ladder.view.response.domain.configuration.LadderResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class LadderInfoPartialResponseBuilder implements PartialResponseCollectionBuilder<LadderResponseBuilderConfiguration> {

    @Override
    public Collection<? extends PartialResponse> build(LadderResponseBuilderConfiguration ladderResponseBuilderConfiguration) {
        final List<LadderInfoPartialResponse> result = new ArrayList<>();
        for (LadderEntry ladderEntry : ladderResponseBuilderConfiguration.getLadderEntries()) {
            result.add(LadderInfoPartialResponse.builder()
                    .username(ladderEntry.getUsername())
                    .level(ladderEntry.getLevel())
                    .xp(ladderEntry.getXp())
                    .isMe(ladderEntry.isMe())
                    .build()
            );
        }

        return result;
    }
}
