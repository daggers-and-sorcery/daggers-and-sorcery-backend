package com.morethanheroic.swords.character.view.response.service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.character.view.response.service.cost.CastingCostPartialResponseBuilder;
import com.morethanheroic.swords.character.view.response.service.cost.domain.CastingCostResponseBuilderConfiguration;
import com.morethanheroic.swords.character.view.response.service.domain.CharacterSpellsResponseBuilderConfiguration;
import com.morethanheroic.swords.character.view.response.service.domain.SpellPartialResponse;
import com.morethanheroic.swords.spell.domain.SpellDefinition;
import com.morethanheroic.swords.spell.service.entity.SpellEntityFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CharacterSpellsSpellPartialResponseBuilder implements PartialResponseCollectionBuilder<CharacterSpellsResponseBuilderConfiguration> {

    private final SpellEntityFactory spellEntityFactory;
    private final CastingCostPartialResponseBuilder castingCostPartialResponseBuilder;

    @Override
    public Collection<? extends PartialResponse> build(final CharacterSpellsResponseBuilderConfiguration characterSpellsResponseBuilderConfiguration) {
        return spellEntityFactory.getEntity(characterSpellsResponseBuilderConfiguration.getUserEntity()).stream()
                .map(spellEntity -> {
                            final SpellDefinition spellDefinition = spellEntity.getSpellDefinition();

                            return SpellPartialResponse.builder()
                                    .id(spellDefinition.getId())
                                    .name(spellDefinition.getName())
                                    .description(spellDefinition.getDescription())
                                    .type(spellDefinition.getType().getName())
                                    .combatSpell(spellDefinition.isCombatSpell())
                                    .openPage(spellDefinition.isOpenPage())
                                    .castingCost(
                                            castingCostPartialResponseBuilder.build(
                                                    CastingCostResponseBuilderConfiguration.builder()
                                                            .userEntity(characterSpellsResponseBuilderConfiguration.getUserEntity())
                                                            .spellDefinition(spellDefinition)
                                                            .build()
                                            )
                                    )
                                    .build();
                        }
                )
                .collect(Collectors.toList());
    }
}
