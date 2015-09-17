package com.morethanheroic.swords.combatsettings.view.controller;

import com.morethanheroic.swords.combatsettings.model.SettingType;
import com.morethanheroic.swords.combatsettings.view.editor.SettingTypeEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class CombatSettingsControllerAdvice {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(SettingType.class, new SettingTypeEditor());
    }
}
