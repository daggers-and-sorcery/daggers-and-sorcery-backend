package com.morethanheroic.swords.metadata.domain.entity;

import com.morethanheroic.swords.metadata.domain.definition.MetadataDefinition;
import com.morethanheroic.swords.metadata.repository.dao.MetadataDatabaseEntity;
import com.morethanheroic.swords.metadata.repository.repository.MetadataMapper;
import com.morethanheroic.swords.user.domain.UserEntity;

public class TextMetadataEntity implements MetadataEntity<String> {

    private final UserEntity userEntity;
    private final MetadataDefinition metadataDefinition;
    private final MetadataMapper metadataMapper;
    private final MetadataDatabaseEntity metadataDatabaseEntity;

    public TextMetadataEntity(UserEntity userEntity, MetadataDefinition metadataDefinition, MetadataMapper metadataMapper) {
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
    public String getValue() {
        if (metadataDatabaseEntity == null) {
            return metadataDefinition.getValueDefinition(0).getName();
        }

        return metadataDefinition.getValueDefinition(metadataDatabaseEntity.getMetaValue()).getName();
    }

    @Override
    public void setValue(String value) {
        final int valueId = metadataDefinition.getValueDefinition(value).getId();

        metadataMapper.setMetadata(userEntity.getId(), metadataDefinition.getId(), valueId);
    }

    @Override
    public boolean isValue(String value) {
        return getValue().equals(value);
    }
}
