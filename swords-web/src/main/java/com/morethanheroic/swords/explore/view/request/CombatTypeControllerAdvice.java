package com.morethanheroic.swords.explore.view.request;

import com.morethanheroic.swords.combat.domain.CombatType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Locale;

@ControllerAdvice
@RequiredArgsConstructor
public class CombatTypeControllerAdvice {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(CombatType.class, new PropertyEditorSupport() {

            @Override
            public void setAsText(final String combatType) {
                setValue(CombatType.valueOf(combatType.toUpperCase(Locale.ENGLISH)));
            }
        });
    }
}
