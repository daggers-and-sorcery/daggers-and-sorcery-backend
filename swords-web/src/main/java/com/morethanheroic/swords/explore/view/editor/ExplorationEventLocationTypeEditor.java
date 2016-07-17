package com.morethanheroic.swords.explore.view.editor;

import com.morethanheroic.swords.explore.service.event.ExplorationEventLocationType;

import java.beans.PropertyEditorSupport;

public class ExplorationEventLocationTypeEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(final String text) {
        if (text.equals("null")) {
            setValue(null);
            
            return;
        }

        setValue(ExplorationEventLocationType.valueOf(text.toUpperCase()));
    }
}
