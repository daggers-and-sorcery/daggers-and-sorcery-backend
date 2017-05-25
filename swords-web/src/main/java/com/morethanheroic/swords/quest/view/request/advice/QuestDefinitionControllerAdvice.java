package com.morethanheroic.swords.quest.view.request.advice;

import com.morethanheroic.swords.quest.domain.definition.QuestDefinition;
import com.morethanheroic.swords.quest.service.definition.cache.QuestDefinitionCache;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;

@ControllerAdvice
@RequiredArgsConstructor
public class QuestDefinitionControllerAdvice {

    private final QuestDefinitionCache questDefinitionCache;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(QuestDefinition.class, new PropertyEditorSupport() {

            @Override
            public void setAsText(final String questId) {
                if (!StringUtils.isNumeric(questId)) {
                    throw new IllegalArgumentException("The provided quest id must be numeric!");
                }

                setValue(questDefinitionCache.getDefinition(Integer.parseInt(questId)));
            }
        });
    }
}
