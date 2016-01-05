package com.morethanheroic.swords.settings.service;

import com.morethanheroic.swords.settings.model.SettingsEntity;
import com.morethanheroic.swords.settings.repository.domain.SettingsMapper;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingsManager {

    @Autowired
    private SettingsMapper settingsMapper;

    public SettingsEntity getSettings(UserEntity userEntity) {
        return new SettingsEntity(settingsMapper.getSettings(userEntity.getId()));
    }

    public void createSettingsForUser(UserEntity userEntity) {
        settingsMapper.insert(userEntity.getId());
    }
}
