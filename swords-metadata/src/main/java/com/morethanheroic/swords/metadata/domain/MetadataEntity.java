package com.morethanheroic.swords.metadata.domain;

import com.morethanheroic.swords.metadata.repository.dao.MetadataDatabaseEntity;
import com.morethanheroic.swords.metadata.repository.repository.MetadataMapper;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class MetadataEntity {

    private final UserEntity userEntity;
    private final MetadataDefinition metadataDefinition;
    private final MetadataMapper metadataMapper;
    private final MetadataDatabaseEntity metadataDatabaseEntity;

    public MetadataEntity(UserEntity userEntity, MetadataDefinition metadataDefinition, MetadataMapper metadataMapper) {
        this.userEntity = userEntity;
        this.metadataDefinition = metadataDefinition;
        this.metadataMapper = metadataMapper;

        metadataDatabaseEntity = metadataMapper.getMetadata(userEntity.getId(), metadataDefinition.getId());
    }

    public int getId() {
        return metadataDefinition.getId();
    }

    public String getName() {
        return metadataDefinition.getName();
    }

    public String getValue() {
        if (metadataDatabaseEntity == null) {
            return metadataDefinition.getValueDefinition(0).getName();
        }

        return metadataDefinition.getValueDefinition(metadataDatabaseEntity.getMetaValue()).getName();
    }

    public void setValue(String value) {
        final int valueId = metadataDefinition.getValueDefinition(value).getId();

        metadataMapper.setMetadata(userEntity.getId(), metadataDefinition.getId(), valueId);
    }
}
