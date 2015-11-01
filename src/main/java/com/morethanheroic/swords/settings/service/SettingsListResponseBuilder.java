package com.morethanheroic.swords.settings.service;

import com.morethanheroic.swords.settings.model.SettingType;
import com.morethanheroic.swords.settings.model.TriggerType;
import com.morethanheroic.swords.settings.repository.dao.CombatSettingsDatabaseEntity;
import com.morethanheroic.swords.settings.repository.dao.Settings;
import com.morethanheroic.swords.common.response.Response;
import com.morethanheroic.swords.common.response.ResponseFactory;
import com.morethanheroic.swords.item.service.ItemDefinitionManager;
import com.morethanheroic.swords.monster.service.MonsterDefinitionCache;
import com.morethanheroic.swords.spell.service.SpellDefinitionManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SettingsListResponseBuilder {

    private final ResponseFactory responseFactory;
    private final MonsterDefinitionCache monsterDefinitionCache;
    private final ItemDefinitionManager itemDefinitionManager;
    private final SpellDefinitionManager spellDefinitionManager;

    @Autowired
    public SettingsListResponseBuilder(ResponseFactory responseFactory, MonsterDefinitionCache monsterDefinitionCache, ItemDefinitionManager itemDefinitionManager, SpellDefinitionManager spellDefinitionManager) {
        this.responseFactory = responseFactory;
        this.monsterDefinitionCache = monsterDefinitionCache;
        this.itemDefinitionManager = itemDefinitionManager;
        this.spellDefinitionManager = spellDefinitionManager;
    }

    public Response build(UserEntity userEntity, List<CombatSettingsDatabaseEntity> combatSettingList, Settings setting) {
        Response response = responseFactory.newResponse(userEntity);

        response.setData("settings", buildCombatSettings(combatSettingList));
        response.setData("otherSettings", buildOtherSettings(setting));

        return response;
    }

    private ArrayList<HashMap<String, Object>> buildCombatSettings(List<CombatSettingsDatabaseEntity> combatSettingList) {
        ArrayList<HashMap<String, Object>> settingsResult = new ArrayList<>();
        for (CombatSettingsDatabaseEntity combatSettingsDatabaseEntity : combatSettingList) {
            HashMap<String, Object> settingsData = new HashMap<>();

            SettingType type = combatSettingsDatabaseEntity.getType();
            if (type == SettingType.ITEM) {
                settingsData.put("use", itemDefinitionManager.getItemDefinition(combatSettingsDatabaseEntity.getSettingsId()).getName());
            } else {
                settingsData.put("use", spellDefinitionManager.getSpellDefinition(combatSettingsDatabaseEntity.getSettingsId()).getName());
            }
            settingsData.put("type", type.name().toLowerCase());

            TriggerType trigger = combatSettingsDatabaseEntity.getTrigger();
            if (trigger == TriggerType.MONSTER) {
                settingsData.put("target", monsterDefinitionCache.getMonsterDefinition(combatSettingsDatabaseEntity.getTarget()).getName());
            } else {
                settingsData.put("target", combatSettingsDatabaseEntity.getTarget());
            }
            settingsData.put("trigger", trigger.name().toLowerCase());
            settingsData.put("id", combatSettingsDatabaseEntity.getId());

            settingsResult.add(settingsData);
        }

        return settingsResult;
    }

    private HashMap<String, Object> buildOtherSettings(Settings setting) {
        HashMap<String, Object> otherSettings = new HashMap<>();

        otherSettings.put("scavengingEnabled", setting.isScavengingEnabled());

        return otherSettings;
    }
}
