package com.morethanheroic.swords.combatsettings.view.editor;

import com.morethanheroic.swords.combatsettings.model.SettingType;

import java.beans.PropertyEditorSupport;

public class SettingTypeEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(final String text) {
        setValue(SettingType.valueOf(text.toUpperCase()));
    }
}
