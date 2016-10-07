package com.morethanheroic.swords.explore.view.controller;

import com.morethanheroic.swords.explore.service.event.ExplorationEventLocation;
import com.morethanheroic.swords.explore.view.editor.ExplorationEventLocationTypeEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class ExplorationExploreControllerAdvice {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(ExplorationEventLocation.class, new ExplorationEventLocationTypeEditor());
    }
}
