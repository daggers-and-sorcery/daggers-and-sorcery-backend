package com.morethanheroic.swords.metadata.domain;

import com.morethanheroic.swords.metadata.repository.dao.MetadataDatabaseEntity;
import com.morethanheroic.swords.metadata.repository.repository.MetadataMapper;
import com.morethanheroic.swords.user.domain.UserEntity;

public class NumericMetadataEntity implements MetadataEntity<Integer> {

    private final UserEntity userEntity;
    private final MetadataDefinition metadataDefinition;
    private final MetadataMapper metadataMapper;
    private final MetadataDatabaseEntity metadataDatabaseEntity;

    public NumericMetadataEntity(UserEntity userEntity, MetadataDefinition metadataDefinition, MetadataMapper metadataMapper) {
        this.userEntity = userEntity;
        this.metadataDefinition = metadataDefinition;
        this.metadataMapper = metadataMapper;

        metadataDatabaseEntity = metadataMapper.getMetadata(userEntity.getId(), metadataDefinition.getId());
    }

    @Override
    public int getId() {
        return metadataDefinition.getId();
    }

    @Override
    public String getName() {
        return metadataDefinition.getName();
    }

    @Override
    public Integer getValue() {
        if (metadataDatabaseEntity == null) {
            return 0;
        }

        return metadataDatabaseEntity.getMetaValue();
    }

    @Override
    public void setValue(Integer value) {
        metadataMapper.setMetadata(userEntity.getId(), metadataDefinition.getId(), value);
    }
}
