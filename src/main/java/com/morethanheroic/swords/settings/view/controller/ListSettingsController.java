package com.morethanheroic.swords.settings.view.controller;

import com.morethanheroic.swords.common.response.Response;
import com.morethanheroic.swords.settings.repository.domain.CombatSettingsMapper;
import com.morethanheroic.swords.settings.repository.domain.SettingsMapper;
import com.morethanheroic.swords.settings.service.SettingsListResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListSettingsController {

    @Autowired
    private SettingsListResponseBuilder settingsListResponseBuilder;

    @Autowired
    private CombatSettingsMapper combatSettingsMapper;

    @Autowired
    private SettingsMapper settingsMapper;

    @RequestMapping(value = "/combat/settings/list", method = RequestMethod.GET)
    public Response getSettings(UserEntity userEntity) {
        return settingsListResponseBuilder.build(userEntity, combatSettingsMapper.getAll(userEntity.getId()), settingsMapper.getSettings(userEntity.getId()));
    }
}
