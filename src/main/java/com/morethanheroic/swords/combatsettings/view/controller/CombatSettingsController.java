package com.morethanheroic.swords.combatsettings.view.controller;

import com.morethanheroic.swords.combatsettings.service.UsableItemsResponseBuilder;
import com.morethanheroic.swords.common.response.Response;
import com.morethanheroic.swords.journal.model.JournalType;
import com.morethanheroic.swords.journal.repository.dao.JournalDatabaseEntity;
import com.morethanheroic.swords.journal.service.JournalManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CombatSettingsController {

    private UsableItemsResponseBuilder usableItemsResponseBuilder;
    private JournalManager journalManager;

    @Autowired
    public CombatSettingsController(UsableItemsResponseBuilder usableItemsResponseBuilder, JournalManager journalManager) {
        this.usableItemsResponseBuilder = usableItemsResponseBuilder;
        this.journalManager = journalManager;
    }

    @RequestMapping(value = "/combat/settings/remove", method = RequestMethod.POST)
    public void removeSettings() {
    }

    @RequestMapping(value = "/combat/settings/insert", method = RequestMethod.POST)
    public void insertSettings() {
    }

    @RequestMapping(value = "/combat/settings/modify", method = RequestMethod.POST)
    public void modifyCombat() {
    }

    @RequestMapping(value = "/combat/settings/usable/item", method = RequestMethod.GET)
    public Response usableItems(UserEntity userEntity) {
        return usableItemsResponseBuilder.build(userEntity, journalManager.getJournalEntryListByType(userEntity, JournalType.ITEM));
    }
}
