package com.morethanheroic.swords.journal.service.monster;

import com.google.common.collect.ImmutableList;
import com.morethanheroic.entity.service.factory.EntityListFactory;
import com.morethanheroic.swords.journal.domain.JournalType;
import com.morethanheroic.swords.journal.domain.entity.MonsterJournalEntity;
import com.morethanheroic.swords.journal.repository.domain.JournalMapper;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;

@Service
@RequiredArgsConstructor
public class MonsterJournalEntityFactory implements EntityListFactory<MonsterJournalEntity, UserEntity> {

    private final JournalMapper journalMapper;
    private final MonsterJournalEntityTransformer monsterJournalEntityTransformer;

    @Override
    public List<MonsterJournalEntity> getEntity(UserEntity userEntity) {
        return journalMapper.getJournalEntryListByType(userEntity.getId(), JournalType.MONSTER).stream()
                .map(monsterJournalEntityTransformer::transform)
                .collect(collectingAndThen(Collectors.toList(), ImmutableList::copyOf));
    }
}
