package com.morethanheroic.swords.explore.view.controller;

import com.morethanheroic.swords.zone.domain.ExplorationZone;
import com.morethanheroic.swords.zone.view.editor.ExplorationZoneLocationTypeEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class ExplorationExploreControllerAdvice {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(ExplorationZone.class, new ExplorationZoneLocationTypeEditor());
    }
}
