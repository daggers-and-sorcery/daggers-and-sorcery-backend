package com.morethanheroic.swords.combatsettings.view.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CombatSettingsController {

    @RequestMapping(value = "/combat/settings/remove", method = RequestMethod.POST)
    public void removeSettings() {
    }

    @RequestMapping(value = "/combat/settings/insert", method = RequestMethod.POST)
    public void insertSettings() {
    }

    @RequestMapping(value = "/combat/settings/modify", method = RequestMethod.POST)
    public void modifyCombat() {
    }
}
