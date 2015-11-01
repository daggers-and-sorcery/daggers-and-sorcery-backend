package com.morethanheroic.swords.combatsettings.view.controller;

import com.morethanheroic.swords.combatsettings.model.SettingType;
import com.morethanheroic.swords.combatsettings.repository.dao.CombatSettingsDatabaseEntity;
import com.morethanheroic.swords.combatsettings.repository.domain.CombatSettingsMapper;
import com.morethanheroic.swords.combatsettings.repository.domain.SettingsMapper;
import com.morethanheroic.swords.combatsettings.service.*;
import com.morethanheroic.swords.combatsettings.view.request.InsertCombatSettingRequest;
import com.morethanheroic.swords.combatsettings.view.request.RemoveCombatSettingRequest;
import com.morethanheroic.swords.combatsettings.view.request.SaveOtherSettingsRequest;
import com.morethanheroic.swords.common.response.Response;
import com.morethanheroic.swords.item.service.ItemDefinitionManager;
import com.morethanheroic.swords.journal.model.JournalType;
import com.morethanheroic.swords.journal.service.JournalManager;
import com.morethanheroic.swords.spell.service.SpellDefinitionManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//TODO: Refactor this, move the controllers into separate files
@RestController
public class CombatSettingsController {

    private final CombatSettingsMapper combatSettingsMapper;
    private final UsableItemsResponseBuilder usableItemsResponseBuilder;
    private final UsableSpellsResponseBuilder usableSpellsResponseBuilder;
    private final SpecificMonstersResponseBuilder specificMonstersResponseBuilder;
    private final SettingsListResponseBuilder settingsListResponseBuilder;
    private final ItemDefinitionManager itemDefinitionManager;
    private final InsertSettingsResponseBuilder insertSettingsResponseBuilder;
    private final JournalManager journalManager;
    private final SpellDefinitionManager spellDefinitionManager;

    @Autowired
    private SaveOtherSettingsResponseBuilder saveOtherSettingsResponseBuilder;

    @Autowired
    private SettingsMapper settingsMapper;

    @Autowired
    public CombatSettingsController(CombatSettingsMapper combatSettingsMapper, SettingsListResponseBuilder settingsListResponseBuilder, UsableItemsResponseBuilder usableItemsResponseBuilder, UsableSpellsResponseBuilder usableSpellsResponseBuilder, SpecificMonstersResponseBuilder specificMonstersResponseBuilder, ItemDefinitionManager itemDefinitionManager, InsertSettingsResponseBuilder insertSettingsResponseBuilder, JournalManager journalManager, SpellDefinitionManager spellDefinitionManager) {
        this.combatSettingsMapper = combatSettingsMapper;
        this.usableItemsResponseBuilder = usableItemsResponseBuilder;
        this.usableSpellsResponseBuilder = usableSpellsResponseBuilder;
        this.specificMonstersResponseBuilder = specificMonstersResponseBuilder;
        this.settingsListResponseBuilder = settingsListResponseBuilder;
        this.insertSettingsResponseBuilder = insertSettingsResponseBuilder;
        this.itemDefinitionManager = itemDefinitionManager;
        this.journalManager = journalManager;
        this.spellDefinitionManager = spellDefinitionManager;
    }

    @RequestMapping(value = "/combat/settings/remove", method = RequestMethod.POST)
    public void removeSettings(UserEntity userEntity, @RequestBody RemoveCombatSettingRequest removeCombatSettingRequest) {
        CombatSettingsDatabaseEntity settingsToRemove = combatSettingsMapper.get(removeCombatSettingRequest.getId());

        if(settingsToRemove != null && settingsToRemove.getUserId() == userEntity.getId()) {
            combatSettingsMapper.remove(settingsToRemove.getId());
        }
    }

    @RequestMapping(value = "/combat/settings/list", method = RequestMethod.GET)
    public Response getSettings(UserEntity userEntity) {
        return settingsListResponseBuilder.build(userEntity, combatSettingsMapper.getAll(userEntity.getId()), settingsMapper.getSettings(userEntity.getId()));
    }

    @RequestMapping(value = "/combat/settings/insert", method = RequestMethod.POST)
    public Response insertSettings(UserEntity userEntity, @RequestBody InsertCombatSettingRequest insertCombatSettingRequest) {
        if(insertCombatSettingRequest.getType() == SettingType.ITEM && !itemDefinitionManager.getItemDefinition(insertCombatSettingRequest.getUse()).isUsable()) {
            return insertSettingsResponseBuilder.build(userEntity, "Invalid, non-usable item selected!");
        }

        if(insertCombatSettingRequest.getType() == SettingType.SPELL && !spellDefinitionManager.getSpellDefinition(insertCombatSettingRequest.getUse()).isCombatSpell()) {
            return insertSettingsResponseBuilder.build(userEntity, "Invalid, non-combat spell selected!");
        }

        combatSettingsMapper.save(new CombatSettingsDatabaseEntity(userEntity.getId(), insertCombatSettingRequest.getType(), insertCombatSettingRequest.getUse(), insertCombatSettingRequest.getTrigger(), insertCombatSettingRequest.getTarget()));

        return insertSettingsResponseBuilder.build(userEntity, "Settings successfully saved!");
    }

    @RequestMapping(value = "/combat/settings/usable/{type}", method = RequestMethod.GET)
    public Response usableItems(UserEntity userEntity, @PathVariable SettingType type) {
        if (type == SettingType.ITEM) {
            return usableItemsResponseBuilder.build(userEntity, journalManager.getJournalEntryListByType(userEntity, JournalType.ITEM));
        } else if (type == SettingType.SPELL) {
            return usableSpellsResponseBuilder.build(userEntity);
        } else {
            throw new IllegalArgumentException("Setting type is not handled!");
        }
    }

    @RequestMapping(value = "/combat/settings/specific_monsters", method = RequestMethod.GET)
    public Response specificMonster(UserEntity userEntity) {
        return specificMonstersResponseBuilder.build(userEntity, journalManager.getJournalEntryListByType(userEntity, JournalType.MONSTER));
    }

    @RequestMapping(value = "/combat/settings/other/save", method = RequestMethod.POST)
    public Response saveOtherSettings(UserEntity userEntity, @RequestBody SaveOtherSettingsRequest saveOtherSettingsRequest) {
        settingsMapper.saveScavengingEnabled(userEntity.getId(), saveOtherSettingsRequest.isScavengingEnabled());

        return saveOtherSettingsResponseBuilder.build(userEntity, "Settings successfully saved!");
    }
}
