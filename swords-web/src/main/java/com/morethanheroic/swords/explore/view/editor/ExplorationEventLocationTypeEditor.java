package com.morethanheroic.swords.explore.view.editor;

import com.morethanheroic.swords.explore.service.event.ExplorationEventLocation;

import java.beans.PropertyEditorSupport;
import java.util.Locale;

public class ExplorationEventLocationTypeEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(final String text) {
        if (text.equals("null")) {
            setValue(null);
            
            return;
        }

        setValue(ExplorationEventLocation.valueOf(text.toUpperCase(Locale.US)));
    }
}
