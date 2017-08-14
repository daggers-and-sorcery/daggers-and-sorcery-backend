package com.morethanheroic.swords.character.view.response.service.cost;

import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.character.view.response.service.cost.domain.CastingCostPartialResponse;
import com.morethanheroic.swords.character.view.response.service.cost.domain.CastingCostResponseBuilderConfiguration;
import com.morethanheroic.swords.character.view.response.service.cost.domain.ItemCastingCostPartialResponse;
import com.morethanheroic.swords.character.view.response.service.cost.domain.ManaCastingCostPartialResponse;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.definition.cache.ItemDefinitionCache;
import com.morethanheroic.swords.spell.domain.CostType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CastingCostPartialResponseBuilder implements PartialResponseCollectionBuilder<CastingCostResponseBuilderConfiguration> {

    private final ItemDefinitionCache itemDefinitionCache;
    private final InventoryEntityFactory inventoryEntityFactory;

    @Override
    public Collection<? extends CastingCostPartialResponse> build(final CastingCostResponseBuilderConfiguration castingCostResponseBuilderConfiguration) {
        return castingCostResponseBuilderConfiguration.getSpellDefinition().getSpellCosts().stream()
                .map(spellCost -> {
                            if (spellCost.getType() == CostType.ITEM) {
                                final ItemDefinition itemDefinition = itemDefinitionCache.getDefinition(spellCost.getId());

                                return ItemCastingCostPartialResponse.builder()
                                        .amount(spellCost.getAmount())
                                        .itemName(itemDefinition.getName())
                                        .existingAmount(inventoryEntityFactory.getEntity(castingCostResponseBuilderConfiguration.getUserEntity()).getItemAmount(itemDefinition))
                                        .build();
                            } else {
                                return ManaCastingCostPartialResponse.builder()
                                        .amount(spellCost.getAmount())
                                        .build();
                            }
                        }
                )
                .collect(Collectors.toList());
    }
}
