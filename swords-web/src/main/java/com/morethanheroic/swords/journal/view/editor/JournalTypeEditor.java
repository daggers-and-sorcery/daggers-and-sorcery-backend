package com.morethanheroic.swords.journal.view.editor;

import com.morethanheroic.swords.journal.domain.JournalType;

import java.beans.PropertyEditorSupport;
import java.util.Locale;

public class JournalTypeEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(final String text) {
        setValue(JournalType.valueOf(text.toUpperCase(Locale.US)));
    }
}
