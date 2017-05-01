package com.morethanheroic.swords.statuseffect.service;

import com.morethanheroic.entity.service.factory.EntityListFactory;
import com.morethanheroic.swords.statuseffect.domain.StatusEffectEntity;
import com.morethanheroic.swords.statuseffect.repository.domain.StatusEffectMapper;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatusEffectEntityFactory implements EntityListFactory<StatusEffectEntity, UserEntity> {

    private final StatusEffectMapper statusEffectMapper;
    private final StatusEffectEntityTransformer statusEffectEntityTransformer;

    @Override
    public List<StatusEffectEntity> getEntity(UserEntity userEntity) {
        return statusEffectMapper.getEffects(userEntity.getId()).stream()
                .map(statusEffectEntityTransformer::transform)
                .collect(Collectors.toList());
    }
}
