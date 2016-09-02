package com.morethanheroic.swords.settings.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.settings.repository.domain.SettingsMapper;
import com.morethanheroic.swords.settings.view.response.service.SaveOtherSettingsResponseBuilder;
import com.morethanheroic.swords.settings.view.request.SaveOtherSettingsRequest;
import com.morethanheroic.swords.user.domain.UserEntity;

@RestController
public class CombatSettingsController {

    @Autowired
    private SaveOtherSettingsResponseBuilder saveOtherSettingsResponseBuilder;

    @Autowired
    private SettingsMapper settingsMapper;

    @RequestMapping(value = "/combat/settings/other/save", method = RequestMethod.POST)
    public Response saveOtherSettings(UserEntity userEntity, @RequestBody SaveOtherSettingsRequest saveOtherSettingsRequest) {
        settingsMapper.saveScavengingEnabled(userEntity.getId(), saveOtherSettingsRequest.isScavengingEnabled());

        return saveOtherSettingsResponseBuilder.build(userEntity, "Settings successfully saved!");
    }
}
