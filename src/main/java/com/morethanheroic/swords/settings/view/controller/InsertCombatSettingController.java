package com.morethanheroic.swords.settings.view.controller;

import com.morethanheroic.swords.response.domain.Response;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.settings.model.SettingType;
import com.morethanheroic.swords.settings.repository.dao.CombatSettingsDatabaseEntity;
import com.morethanheroic.swords.settings.repository.domain.CombatSettingsMapper;
import com.morethanheroic.swords.settings.service.InsertSettingsResponseBuilder;
import com.morethanheroic.swords.settings.view.request.InsertCombatSettingRequest;
import com.morethanheroic.swords.spell.service.cache.SpellDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InsertCombatSettingController {

    @Autowired
    private ItemDefinitionCache itemDefinitionCache;

    @Autowired
    private InsertSettingsResponseBuilder insertSettingsResponseBuilder;

    @Autowired
    private SpellDefinitionCache spellDefinitionCache;

    @Autowired
    private CombatSettingsMapper combatSettingsMapper;

    @RequestMapping(value = "/combat/settings/insert", method = RequestMethod.POST)
    public Response insertSetting(UserEntity userEntity, @RequestBody InsertCombatSettingRequest insertCombatSettingRequest) {
        if(insertCombatSettingRequest.getType() == SettingType.ITEM && !itemDefinitionCache.getDefinition(insertCombatSettingRequest.getUse()).isUsable()) {
            return insertSettingsResponseBuilder.build(userEntity, "Invalid, non-usable item selected!");
        }

        if(insertCombatSettingRequest.getType() == SettingType.SPELL && !spellDefinitionCache.getSpellDefinition(insertCombatSettingRequest.getUse()).isCombatSpell()) {
            return insertSettingsResponseBuilder.build(userEntity, "Invalid, non-combat spell selected!");
        }

        combatSettingsMapper.save(new CombatSettingsDatabaseEntity(userEntity.getId(), insertCombatSettingRequest.getType(), insertCombatSettingRequest.getUse(), insertCombatSettingRequest.getTrigger(), insertCombatSettingRequest.getTarget()));

        return insertSettingsResponseBuilder.build(userEntity, "Settings successfully saved!");
    }
}
