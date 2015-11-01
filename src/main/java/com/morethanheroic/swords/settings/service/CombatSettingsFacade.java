package com.morethanheroic.swords.settings.service;

import com.morethanheroic.swords.settings.repository.dao.CombatSettingsDatabaseEntity;
import com.morethanheroic.swords.settings.repository.domain.CombatSettingsMapper;
import com.morethanheroic.swords.settings.service.domain.CombatSettingsEntity;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CombatSettingsFacade {

    private final CombatSettingsMapper combatSettingsMapper;
    private final CombatSettingsConverter combatSettingsConverter;

    @Autowired
    public CombatSettingsFacade(CombatSettingsMapper combatSettingsMapper, CombatSettingsConverter combatSettingsConverter) {
        this.combatSettingsMapper = combatSettingsMapper;
        this.combatSettingsConverter = combatSettingsConverter;
    }

    public List<CombatSettingsEntity> getAllCombatSettings(UserEntity userEntity) {
        List<CombatSettingsDatabaseEntity> combatSettingsDatabaseEntityList = combatSettingsMapper.getAll(userEntity.getId());

        ArrayList<CombatSettingsEntity> result = new ArrayList<>();

        for(CombatSettingsDatabaseEntity combatSettingsDatabaseEntity : combatSettingsDatabaseEntityList) {
            result.add(combatSettingsConverter.convertCombatSettingsDatabaseEntity(combatSettingsDatabaseEntity));
        }

        return result;
    }
}
