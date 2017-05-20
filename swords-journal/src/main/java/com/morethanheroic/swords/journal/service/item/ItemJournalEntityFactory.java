package com.morethanheroic.swords.journal.service.item;

import com.google.common.collect.ImmutableList;
import com.morethanheroic.entity.service.factory.EntityFactory;
import com.morethanheroic.entity.service.factory.EntityListFactory;
import com.morethanheroic.swords.journal.domain.JournalType;
import com.morethanheroic.swords.journal.domain.entity.ItemJournalEntity;
import com.morethanheroic.swords.journal.repository.domain.JournalMapper;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;

/**
 * An implementation for {@link EntityFactory} to create {@link ItemJournalEntity}s.
 */
@Service
@RequiredArgsConstructor
public class ItemJournalEntityFactory implements EntityListFactory<ItemJournalEntity, UserEntity> {

    private final JournalMapper journalMapper;
    private final ItemJournalEntityTransformer itemJournalEntityTransformer;

    @Override
    public List<ItemJournalEntity> getEntity(final UserEntity userEntity) {
        return journalMapper.getJournalEntryListByType(userEntity.getId(), JournalType.ITEM).stream()
                .map(itemJournalEntityTransformer::transform)
                .collect(collectingAndThen(Collectors.toList(), ImmutableList::copyOf));
    }
}
