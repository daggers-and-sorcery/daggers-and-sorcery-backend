package com.morethanheroic.swords.journal.view.editor;

import com.morethanheroic.swords.journal.model.JournalType;

import java.beans.PropertyEditorSupport;

public class JournalTypeEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(final String text) {
        setValue(JournalType.valueOf(text.toUpperCase()));
    }
}
