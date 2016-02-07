package com.morethanheroic.swords.settings.service;

import com.morethanheroic.swords.settings.model.SettingsEntity;
import com.morethanheroic.swords.settings.repository.domain.SettingsMapper;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SettingsFacade {

    @NonNull
    private final SettingsMapper settingsMapper;

    public SettingsEntity getSettings(UserEntity userEntity) {
        return new SettingsEntity(settingsMapper.getSettings(userEntity.getId()));
    }
}
