package com.morethanheroic.swords.profile.response.service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.attribute.service.calc.type.SkillTypeCalculator;
import com.morethanheroic.swords.equipment.EquipmentEntityFactory;
import com.morethanheroic.swords.equipment.domain.EquipmentEntity;
import com.morethanheroic.swords.equipment.domain.EquipmentSlot;
import com.morethanheroic.swords.inventory.domain.InventoryItem;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.inventory.service.UnidentifiedItemIdCalculator;
import com.morethanheroic.swords.inventory.service.sorter.InventoryItemTypeSorter;
import com.morethanheroic.swords.item.domain.ItemType;
import com.morethanheroic.swords.item.service.definition.cache.ItemDefinitionCache;
import com.morethanheroic.swords.profile.response.service.attribute.AttributeValuePartialResponseBuilder;
import com.morethanheroic.swords.profile.response.service.inventory.InventoryPartialResponseBuilder;
import com.morethanheroic.swords.profile.response.service.inventory.domain.configuration.InventoryPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.profile.response.service.skill.SkillPartialResponseBuilder;
import com.morethanheroic.swords.profile.response.service.skill.domain.SkillPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.profile.response.service.special.SpecialPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.profile.response.service.statuseffect.StatusEffectPartialResponseCollectionBuilder;
import com.morethanheroic.swords.profile.response.service.statuseffect.domain.configuration.StatusEffectResponseBuilderConfiguration;
import com.morethanheroic.swords.profile.service.response.item.ProfileIdentifiedItemEntryResponseBuilder;
import com.morethanheroic.swords.profile.service.response.item.ProfileUnidentifiedItemEntryResponseBuilder;
import com.morethanheroic.swords.profile.service.response.special.SpecialPartialResponseBuilder;
import com.morethanheroic.swords.race.service.RaceDefinitionCache;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.skill.domain.SkillGroup;
import com.morethanheroic.swords.spell.repository.domain.SpellMapper;
import com.morethanheroic.swords.spell.service.cache.SpellDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.vampire.service.VampireCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProfileInfoResponseBuilder implements ResponseBuilder<ProfileInfoResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final RaceDefinitionCache raceDefinitionCache;
    private final AttributeValuePartialResponseBuilder attributeValuePartialResponseBuilder;
    private final SkillPartialResponseBuilder skillPartialResponseBuilder;
    private final SkillTypeCalculator skillTypeCalculator;
    private final InventoryPartialResponseBuilder inventoryPartialResponseBuilder;
    private final InventoryItemTypeSorter inventoryItemTypeSorter;
    private final InventoryEntityFactory inventoryEntityFactory;
    private final SpecialPartialResponseBuilder specialPartialResponseBuilder;
    private final VampireCalculator vampireCalculator;
    private final StatusEffectPartialResponseCollectionBuilder statusEffectPartialResponseCollectionBuilder;

    public Response build(ProfileInfoResponseBuilderConfiguration profileInfoResponseBuilderConfiguration) {
        final UserEntity userEntity = profileInfoResponseBuilderConfiguration.getUserEntity();

        final Response response = responseFactory.newResponse(userEntity);

        response.setData("attribute", attributeValuePartialResponseBuilder.build(profileInfoResponseBuilderConfiguration));
        response.setData("skill", skillPartialResponseBuilder.build(
                SkillPartialResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .tradeSkills(skillTypeCalculator.getSkillsByGroup(SkillGroup.TRADE))
                        .combatSkills(skillTypeCalculator.getSkillsByGroup(SkillGroup.COMBAT))
                        .magicSkills(skillTypeCalculator.getSkillsByGroup(SkillGroup.MAGIC))
                        .shadowSkills(skillTypeCalculator.getSkillsByGroup(SkillGroup.SHADOW))
                        .build()
        ));
        response.setData("username", userEntity.getUsername());
        response.setData("race", raceDefinitionCache.getDefinition(userEntity.getRace()).getName());
        response.setData("registrationDate", userEntity.getRegistrationDate().getEpochSecond() * 1000);
        response.setData("lastLoginDate", userEntity.getLastLoginDate().getEpochSecond() * 1000);
        response.setData("inventory", inventoryPartialResponseBuilder.build(
                InventoryPartialResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .sessionEntity(profileInfoResponseBuilderConfiguration.getSessionEntity())
                        .inventoryItems(getSortedItems(userEntity))
                        .build()
                )
        );
        response.setData("special", specialPartialResponseBuilder.build(
                SpecialPartialResponseBuilderConfiguration.builder()
                        .isVampire(vampireCalculator.isVampire(userEntity))
                        .build()
        ));
        response.setData("statusEffect", statusEffectPartialResponseCollectionBuilder.build(
            StatusEffectResponseBuilderConfiguration.builder()
                .userEntity(userEntity)
                .build()
        ));

        return response;
    }

    private Map<ItemType, List<InventoryItem>> getSortedItems(final UserEntity userEntity) {
        return inventoryItemTypeSorter.sortByType(inventoryEntityFactory.getEntity(userEntity).getItems());
    }
}
