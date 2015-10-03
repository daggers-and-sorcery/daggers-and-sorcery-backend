package com.morethanheroic.swords.spell.service.domain;

import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.service.CombatEffectApplierService;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UseSpellService {

    private final CombatEffectApplierService combatEffectApplierService;

    public UseSpellService(CombatEffectApplierService combatEffectApplierService) {
        this.combatEffectApplierService = combatEffectApplierService;
    }

    public boolean canUseSpell(UserEntity userEntity, SpellDefinition item) {
        return false;
    }

    public void useSpell(UserCombatEntity combatEntity, SpellDefinition spell) {
        if (canUseSpell(combatEntity.getUserEntity(), spell)) {
            applySpell(combatEntity, spell);
        }
    }

    private void applySpell(CombatEntity userCombatEntity, SpellDefinition spell) {
        combatEffectApplierService.applyEffects(userCombatEntity, spell.getCombatEffects());
    }
}
