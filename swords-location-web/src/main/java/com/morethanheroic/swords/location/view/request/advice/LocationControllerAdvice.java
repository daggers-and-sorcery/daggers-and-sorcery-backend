package com.morethanheroic.swords.location.view.request.advice;

import java.beans.PropertyEditorSupport;
import java.util.Locale;

import com.morethanheroic.swords.location.domain.Location;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class LocationControllerAdvice {

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(Location.class, new PropertyEditorSupport() {

            @Override
            public void setAsText(final String rawSkillType) {
                setValue(Location.valueOf(rawSkillType.toUpperCase(Locale.ENGLISH).replaceAll(" ", "_")));
            }
        });
    }
}
