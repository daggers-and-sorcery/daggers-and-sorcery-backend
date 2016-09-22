package com.morethanheroic.swords.settings.view.response.service;

import com.morethanheroic.swords.settings.repository.dao.SettingsDatabaseEntity;
import com.morethanheroic.swords.response.domain.CharacterRefreshResponse;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SettingsListResponseBuilder {

    private final ResponseFactory responseFactory;

    public CharacterRefreshResponse build(UserEntity userEntity, SettingsDatabaseEntity setting) {
        CharacterRefreshResponse response = responseFactory.newResponse(userEntity);

        response.setData("otherSettings", buildOtherSettings(setting));

        return response;
    }

    private HashMap<String, Object> buildOtherSettings(SettingsDatabaseEntity setting) {
        HashMap<String, Object> otherSettings = new HashMap<>();

        otherSettings.put("scavengingEnabled", setting.isScavengingEnabled());

        return otherSettings;
    }
}
