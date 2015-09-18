package com.morethanheroic.swords.combatsettings.view.controller;

import javax.validation.Valid;

import com.morethanheroic.swords.combatsettings.model.SettingType;
import com.morethanheroic.swords.combatsettings.service.SpecificMonstersResponseBuilder;
import com.morethanheroic.swords.combatsettings.service.UsableItemsResponseBuilder;
import com.morethanheroic.swords.combatsettings.service.UsableSpellsResponseBuilder;
import com.morethanheroic.swords.combatsettings.view.request.CombatSettingsRequest;
import com.morethanheroic.swords.common.response.Response;
import com.morethanheroic.swords.journal.model.JournalType;
import com.morethanheroic.swords.journal.service.JournalManager;
import com.morethanheroic.swords.user.domain.UserEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CombatSettingsController {

    private final UsableItemsResponseBuilder usableItemsResponseBuilder;
    private final UsableSpellsResponseBuilder usableSpellsResponseBuilder;
    private final SpecificMonstersResponseBuilder specificMonstersResponseBuilder;
    private final JournalManager journalManager;

    @Autowired
    public CombatSettingsController(UsableItemsResponseBuilder usableItemsResponseBuilder, UsableSpellsResponseBuilder usableSpellsResponseBuilder,
            SpecificMonstersResponseBuilder specificMonstersResponseBuilder, JournalManager journalManager) {
        this.usableItemsResponseBuilder = usableItemsResponseBuilder;
        this.usableSpellsResponseBuilder = usableSpellsResponseBuilder;
        this.specificMonstersResponseBuilder = specificMonstersResponseBuilder;
        this.journalManager = journalManager;
    }

    @RequestMapping(value = "/combat/settings/remove", method = RequestMethod.POST)
    public void removeSettings() {
    }

    @RequestMapping(value = "/combat/settings/insert", method = RequestMethod.POST)
    public void insertSettings(UserEntity userEntity, @RequestBody CombatSettingsRequest combatSettingsRequest) {
        System.out.println(combatSettingsRequest.getTarget() + " - " + combatSettingsRequest.getUse() + " - " + combatSettingsRequest.getTrigger()
                + " - " + combatSettingsRequest.getType());
    }

    @RequestMapping(value = "/combat/settings/modify", method = RequestMethod.POST)
    public void modifyCombat() {
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
