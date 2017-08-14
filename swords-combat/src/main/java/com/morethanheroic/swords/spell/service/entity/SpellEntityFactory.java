package com.morethanheroic.swords.spell.service.entity;

import com.morethanheroic.entity.service.factory.EntityListFactory;
import com.morethanheroic.swords.spell.domain.SpellEntity;
import com.morethanheroic.swords.spell.repository.domain.SpellMapper;
import com.morethanheroic.swords.spell.service.cache.SpellDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpellEntityFactory implements EntityListFactory<SpellEntity, UserEntity> {

    private final SpellMapper spellMapper;
    private final SpellDefinitionCache spellDefinitionCache;

    @Override
    public List<SpellEntity> getEntity(final UserEntity userEntity) {
        return spellMapper.getAllSpellsForUser(userEntity.getId()).stream()
                .map(spellDatabaseEntity -> spellDefinitionCache.getSpellDefinition(spellDatabaseEntity.getSpellId()))
                .map(spellDefinition ->
                        SpellEntity.builder()
                                .spellDefinition(spellDefinition)
                                .build()
                )
                .collect(Collectors.toList());
    }
}
