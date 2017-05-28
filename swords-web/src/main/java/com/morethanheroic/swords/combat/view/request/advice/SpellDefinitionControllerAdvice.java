package com.morethanheroic.swords.combat.view.request.advice;

import com.morethanheroic.swords.quest.domain.definition.QuestDefinition;
import com.morethanheroic.swords.spell.service.cache.SpellDefinitionCache;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;

@ControllerAdvice
@RequiredArgsConstructor
public class SpellDefinitionControllerAdvice {

    private final SpellDefinitionCache spellDefinitionCache;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(QuestDefinition.class, new PropertyEditorSupport() {

            @Override
            public void setAsText(final String spellId) {
                if (!StringUtils.isNumeric(spellId)) {
                    throw new IllegalArgumentException("The provided quest id must be numeric!");
                }

                setValue(spellDefinitionCache.getSpellDefinition(Integer.parseInt(spellId)));
            }
        });
    }
}
