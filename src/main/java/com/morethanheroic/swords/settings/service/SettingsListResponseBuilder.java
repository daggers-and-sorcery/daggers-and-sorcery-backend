package com.morethanheroic.swords.settings.service;

import com.morethanheroic.swords.settings.model.SettingType;
import com.morethanheroic.swords.settings.model.TriggerType;
import com.morethanheroic.swords.settings.repository.dao.CombatSettingsDatabaseEntity;
import com.morethanheroic.swords.settings.repository.dao.SettingsDatabaseEntity;
import com.morethanheroic.swords.response.domain.Response;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.monster.service.cache.MonsterDefinitionCache;
import com.morethanheroic.swords.spell.service.cache.SpellDefinitionCache;
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
    private final ItemDefinitionCache itemDefinitionCache;
    private final SpellDefinitionCache spellDefinitionCache;

    @Autowired
    public SettingsListResponseBuilder(ResponseFactory responseFactory, MonsterDefinitionCache monsterDefinitionCache, ItemDefinitionCache itemDefinitionCache, SpellDefinitionCache spellDefinitionCache) {
        this.responseFactory = responseFactory;
        this.monsterDefinitionCache = monsterDefinitionCache;
        this.itemDefinitionCache = itemDefinitionCache;
        this.spellDefinitionCache = spellDefinitionCache;
    }

    public Response build(UserEntity userEntity, List<CombatSettingsDatabaseEntity> combatSettingList, SettingsDatabaseEntity setting) {
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
                settingsData.put("use", itemDefinitionCache.getDefinition(combatSettingsDatabaseEntity.getSettingsId()).getName());
            } else {
                settingsData.put("use", spellDefinitionCache.getSpellDefinition(combatSettingsDatabaseEntity.getSettingsId()).getName());
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

    private HashMap<String, Object> buildOtherSettings(SettingsDatabaseEntity setting) {
        HashMap<String, Object> otherSettings = new HashMap<>();

        otherSettings.put("scavengingEnabled", setting.isScavengingEnabled());

        return otherSettings;
    }
}
