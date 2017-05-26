package com.morethanheroic.swords.journal.view.response.item.domain;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemJournalListResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final UserEntity userEntity;
}
