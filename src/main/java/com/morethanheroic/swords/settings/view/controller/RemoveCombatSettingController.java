package com.morethanheroic.swords.settings.view.controller;

import com.morethanheroic.swords.settings.repository.dao.CombatSettingsDatabaseEntity;
import com.morethanheroic.swords.settings.repository.domain.CombatSettingsMapper;
import com.morethanheroic.swords.settings.view.request.RemoveCombatSettingRequest;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RemoveCombatSettingController {

    @Autowired
    private CombatSettingsMapper combatSettingsMapper;

    @RequestMapping(value = "/combat/settings/remove", method = RequestMethod.POST)
    public void removeSetting(UserEntity userEntity, @RequestBody RemoveCombatSettingRequest removeCombatSettingRequest) {
        CombatSettingsDatabaseEntity settingsToRemove = combatSettingsMapper.get(removeCombatSettingRequest.getId());

        if(settingsToRemove != null && settingsToRemove.getUserId() == userEntity.getId()) {
            combatSettingsMapper.remove(settingsToRemove.getId());
        }
    }
}
