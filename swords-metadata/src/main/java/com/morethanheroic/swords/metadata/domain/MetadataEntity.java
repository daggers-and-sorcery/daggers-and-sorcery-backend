package com.morethanheroic.swords.metadata.domain;

import com.morethanheroic.swords.metadata.repository.dao.MetadataDatabaseEntity;
import com.morethanheroic.swords.metadata.repository.repository.MetadataMapper;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
public class MetadataEntity {

    @Autowired
    private MetadataMapper metadataMapper;

    private final UserEntity userEntity;
    private final MetadataDefinition metadataDefinition;

    private MetadataDatabaseEntity metadataDatabaseEntity;

    @PostConstruct
    public void initialize() {
        metadataDatabaseEntity = metadataMapper.getMetadata(userEntity.getId(), metadataDefinition.getId());
    }

    public int getId() {
        return metadataDatabaseEntity.getMetaId();
    }

    public String getName() {
        return "";
    }

    public String getValue() {
        return "";
    }
}
