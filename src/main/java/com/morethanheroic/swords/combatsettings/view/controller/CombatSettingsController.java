package com.morethanheroic.swords.combatsettings.view.controller;

import com.morethanheroic.swords.combatsettings.model.SettingType;
import com.morethanheroic.swords.combatsettings.repository.dao.CombatSettingsDatabaseEntity;
import com.morethanheroic.swords.combatsettings.repository.domain.CombatSettingsMapper;
import com.morethanheroic.swords.combatsettings.service.SpecificMonstersResponseBuilder;
import com.morethanheroic.swords.combatsettings.service.UsableItemsResponseBuilder;
import com.morethanheroic.swords.combatsettings.service.UsableSpellsResponseBuilder;
import com.morethanheroic.swords.combatsettings.view.request.CombatSettingsRequest;
import com.morethanheroic.swords.common.response.Response;
import com.morethanheroic.swords.journal.model.JournalType;
import com.morethanheroic.swords.journal.service.JournalManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CombatSettingsController {

    private final CombatSettingsMapper combatSettingsMapper;
    private final UsableItemsResponseBuilder usableItemsResponseBuilder;
    private final UsableSpellsResponseBuilder usableSpellsResponseBuilder;
    private final SpecificMonstersResponseBuilder specificMonstersResponseBuilder;
    private final JournalManager journalManager;

    @Autowired
    public CombatSettingsController(CombatSettingsMapper combatSettingsMapper, UsableItemsResponseBuilder usableItemsResponseBuilder, UsableSpellsResponseBuilder usableSpellsResponseBuilder, SpecificMonstersResponseBuilder specificMonstersResponseBuilder, JournalManager journalManager) {
        this.combatSettingsMapper = combatSettingsMapper;
        this.usableItemsResponseBuilder = usableItemsResponseBuilder;
        this.usableSpellsResponseBuilder = usableSpellsResponseBuilder;
        this.specificMonstersResponseBuilder = specificMonstersResponseBuilder;
        this.journalManager = journalManager;
    }

    @RequestMapping(value = "/combat/settings/remove", method = RequestMethod.POST)
    public void removeSettings(UserEntity userEntity, @RequestParam int id) {
        CombatSettingsDatabaseEntity settingsToRemove = combatSettingsMapper.get(id);

        if(settingsToRemove != null && settingsToRemove.getUserId() == userEntity.getId()) {
            combatSettingsMapper.remove(id);
        }
    }

    @RequestMapping(value = "/combat/settings/insert", method = RequestMethod.POST)
    public void insertSettings(UserEntity userEntity, @RequestBody CombatSettingsRequest combatSettingsRequest) {
        combatSettingsMapper.save(new CombatSettingsDatabaseEntity(userEntity.getId(),combatSettingsRequest.getType(),combatSettingsRequest.getUse(), combatSettingsRequest.getTrigger(), combatSettingsRequest.getTarget()));
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
}
