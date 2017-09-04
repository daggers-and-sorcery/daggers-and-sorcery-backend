package com.morethanheroic.swords.skill.view.request.advice;

import java.beans.PropertyEditorSupport;
import java.util.Locale;

import com.morethanheroic.swords.skill.domain.SkillType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class SkillTypeControllerAdvice {

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(SkillType.class, new PropertyEditorSupport() {

            @Override
            public void setAsText(final String rawSkillType) {
                setValue(SkillType.valueOf(rawSkillType.toUpperCase(Locale.ENGLISH).replaceAll(" ", "_")));
            }
        });
    }
}
