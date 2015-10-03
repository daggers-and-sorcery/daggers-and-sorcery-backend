package com.morethanheroic.swords.combatsettings.service;

import com.morethanheroic.swords.combatsettings.model.SettingType;
import com.morethanheroic.swords.combatsettings.model.TriggerType;
import com.morethanheroic.swords.combatsettings.repository.dao.CombatSettingsDatabaseEntity;
import com.morethanheroic.swords.common.response.Response;
import com.morethanheroic.swords.common.response.ResponseFactory;
import com.morethanheroic.swords.item.service.ItemDefinitionManager;
import com.morethanheroic.swords.monster.service.MonsterDefinitionManager;
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
    private final MonsterDefinitionManager monsterDefinitionManager;
    private final ItemDefinitionManager itemDefinitionManager;
    private final SpellDefinitionManager spellDefinitionManager;

    @Autowired
    public SettingsListResponseBuilder(ResponseFactory responseFactory, MonsterDefinitionManager monsterDefinitionManager, ItemDefinitionManager itemDefinitionManager, SpellDefinitionManager spellDefinitionManager) {
        this.responseFactory = responseFactory;
        this.monsterDefinitionManager = monsterDefinitionManager;
        this.itemDefinitionManager = itemDefinitionManager;
        this.spellDefinitionManager = spellDefinitionManager;
    }

    public Response build(UserEntity userEntity, List<CombatSettingsDatabaseEntity> combatSettingList) {
        Response response = responseFactory.newResponse(userEntity);

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
                settingsData.put("target", monsterDefinitionManager.getMonsterDefinition(combatSettingsDatabaseEntity.getTarget()).getName());
            } else {
                settingsData.put("target", combatSettingsDatabaseEntity.getTarget());
            }
            settingsData.put("trigger", trigger.name().toLowerCase());
            settingsData.put("id", combatSettingsDatabaseEntity.getId());

            settingsResult.add(settingsData);
        }

        response.setData("settings", settingsResult);

        return response;
    }
}
