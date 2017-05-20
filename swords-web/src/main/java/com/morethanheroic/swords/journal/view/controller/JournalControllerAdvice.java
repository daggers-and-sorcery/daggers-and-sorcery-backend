package com.morethanheroic.swords.journal.view.controller;

import com.morethanheroic.swords.journal.domain.JournalType;
import com.morethanheroic.swords.journal.view.editor.JournalTypeEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class JournalControllerAdvice {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(JournalType.class, new JournalTypeEditor());
    }
}
