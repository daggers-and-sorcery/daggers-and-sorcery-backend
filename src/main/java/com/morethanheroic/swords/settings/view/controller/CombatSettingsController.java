package com.morethanheroic.swords.settings.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.morethanheroic.swords.journal.model.JournalType;
import com.morethanheroic.swords.journal.service.JournalManager;
import com.morethanheroic.swords.response.domain.CharacterRefreshResponse;
import com.morethanheroic.swords.settings.model.SettingType;
import com.morethanheroic.swords.settings.repository.domain.SettingsMapper;
import com.morethanheroic.swords.settings.service.SaveOtherSettingsResponseBuilder;
import com.morethanheroic.swords.settings.service.SpecificMonstersResponseBuilder;
import com.morethanheroic.swords.settings.service.UsableItemsResponseBuilder;
import com.morethanheroic.swords.settings.service.UsableSpellsResponseBuilder;
import com.morethanheroic.swords.settings.view.request.SaveOtherSettingsRequest;
import com.morethanheroic.swords.user.domain.UserEntity;

//TODO: Refactor this, move the controllers into separate files
@RestController
public class CombatSettingsController {

    @Autowired
    private UsableItemsResponseBuilder usableItemsResponseBuilder;

    @Autowired
    private UsableSpellsResponseBuilder usableSpellsResponseBuilder;

    @Autowired
    private SpecificMonstersResponseBuilder specificMonstersResponseBuilder;

    @Autowired
    private JournalManager journalManager;

    @Autowired
    private SaveOtherSettingsResponseBuilder saveOtherSettingsResponseBuilder;

    @Autowired
    private SettingsMapper settingsMapper;

    @Autowired
    private InventoryEntityFactory inventoryEntityFactory;

    //TODO: move these to a new spot for the new combat system
    //TODO: Create two separate controller, one for spells and one for items because this method does too much...
    @RequestMapping(value = "/combat/settings/usable/{type}", method = RequestMethod.GET)
    public Response usableItems(UserEntity userEntity, @PathVariable SettingType type) {
        if (type == SettingType.ITEM) {
            return usableItemsResponseBuilder.build(userEntity, inventoryEntityFactory.getEntity(userEntity.getId()).getItems());
        } else if (type == SettingType.SPELL) {
            return usableSpellsResponseBuilder.build(userEntity);
        } else {
            throw new IllegalArgumentException("Setting type is not handled!");
        }
    }

    @RequestMapping(value = "/combat/settings/specific_monsters", method = RequestMethod.GET)
    public CharacterRefreshResponse specificMonster(UserEntity userEntity) {
        return specificMonstersResponseBuilder.build(userEntity, journalManager.getJournalEntryListByType(userEntity, JournalType.MONSTER));
    }

    @RequestMapping(value = "/combat/settings/other/save", method = RequestMethod.POST)
    public CharacterRefreshResponse saveOtherSettings(UserEntity userEntity, @RequestBody SaveOtherSettingsRequest saveOtherSettingsRequest) {
        settingsMapper.saveScavengingEnabled(userEntity.getId(), saveOtherSettingsRequest.isScavengingEnabled());

        return saveOtherSettingsResponseBuilder.build(userEntity, "Settings successfully saved!");
    }
}
